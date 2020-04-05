package com.javaweb.service.impl;

import com.cloudinary.utils.ObjectUtils;
import com.javaweb.builder.UserSearchBuilder;
import com.javaweb.converter.DTOConverter;
import com.javaweb.dto.UserDTO;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.RoleEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.RoleRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IUserService;
import com.javaweb.utils.CloudinaryUtils;
import com.javaweb.utils.Constant;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Tuple;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService{

	@Autowired
	private UserRepository repository;

	@Autowired
	private BuildingRepository buildingRepository;
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private RoleRepository roleRepository;



	@Autowired
	PasswordEncoder passwordEncoder;


	@Override
	public List<UserDTO> findAll(UserDTO dto, Pageable pageable) {
		UserSearchBuilder builder = DTOConverter.toModel(dto,UserSearchBuilder.Builder.class).build();
		List<UserDTO> result = repository.findAll(builder,pageable).stream()
				.map(item-> {

					UserDTO user =	(UserDTO) DTOConverter.toModel(item, UserDTO.class);
					try{
						RoleEntity role = item.getRoleList().iterator().next();
						if(role.getType() == Constant.STAFF_ROLE)
							user.setRole(Constant.STAFF_ROLE);
						else user.setRole(Constant.ADMIN_ROLE);
					}catch (Exception e){
						user.setRole(Constant.STAFF_ROLE);
						e.printStackTrace();
					}
					user.setPassword("");
					return user;
				})
				.collect(Collectors.toList());
		return result;
	}

	@Transactional
	@Override
	public boolean assign(List<Long> staffId,  Long buildingId){
		try {
			BuildingEntity buildingEntity = buildingRepository.findById(buildingId).get();
			List<UserEntity> list = repository.findAllById(staffId);
			buildingEntity.setStaffList(new HashSet<UserEntity>(list));
			buildingRepository.save(buildingEntity);
		}catch (Exception e){
			return false;
		}
		return true;
	}

	@Transactional
	@Override
	public boolean assignCustomer(List<Long> staffId,  Long customerId){
		try {
			CustomerEntity customerEntity = customerRepository.findById(customerId).get();
			List<UserEntity> list = repository.findAllById(staffId);
			customerEntity.setStaffList(new HashSet<UserEntity>(list));
			customerRepository.save(customerEntity);
		}catch (Exception e){
			return false;
		}
		return true;
	}

	@Override
	public long count(UserDTO dto) {
		UserSearchBuilder builder = DTOConverter.toModel(dto,UserSearchBuilder.class);
		return repository.count(builder);
	}

	@Transactional
	@Override
	public long lockUser(long id) {
		UserEntity user = repository.findById(id).get();
		user.setStatus(0);
		repository.save(user);
		return id;
	}

	@Override
	public List<Tuple> findAssignmentByBuildingId(Long id) {
		List<Tuple> list = repository.findStaffByBuildingId(id);
		return list;
	}

	@Override
	public List<Tuple> findAssignmentByCustomerId(Long id) {
		List<Tuple> list = repository.findStaffByCustomerId(id);
		return list;
	}

	@Override
	public List<Map<String,Object>> findAllActiveStaff() {
		List<Tuple> list = repository.findAllActiveStaff();
		List<Map<String,Object>> mapList = new ArrayList<Map<String, Object>>() ;
		list.stream().forEach(e->{
			Map<String,Object> map = new HashMap<>();
			e.getElements().parallelStream().forEach(k->{
				map.put(k.getAlias(),e.get(k.getAlias()));
			});
			mapList.add(map);
		});
		return mapList;
	}


	@Override
	public UserDTO findById(long id) {
		UserEntity entity = repository.findById(id).get();
		UserDTO user = DTOConverter.toModel(entity,UserDTO.class);
		try{
			RoleEntity role = entity.getRoleList().iterator().next();
			if(role.getType() == Constant.STAFF_ROLE)
				user.setRole(Constant.STAFF_ROLE);
			else user.setRole(Constant.ADMIN_ROLE);
		}catch (Exception e){
			user.setRole(Constant.STAFF_ROLE);
			e.printStackTrace();
		}
		user.setPassword("");
		return user;
	}

	@Transactional
	@Override
	public Long save(UserDTO user) {
		UserEntity entity = DTOConverter.toModel(user,UserEntity.class);
		entity.setPassword(passwordEncoder.encode(user.getPassword()));
		UserEntity userEntity = repository.save(entity);
		RoleEntity roleEntity = roleRepository.findOneByType(user.getRole());
		roleEntity.getUserList().add(userEntity);
		userEntity.getRoleList().add(roleEntity);
		return repository.save(userEntity).getId();
	}

	@Transactional
	@Override
	public Long update(UserDTO user) throws IOException {
		UserEntity entity = repository.findById(user.getId()).get();
		if(StringUtils.isNotBlank(user.getFullname())) entity.setFullname(user.getFullname());
		if(user.getStatus()!=null) entity.setStatus(user.getStatus());
		if(StringUtils.isNotBlank(user.getPhone())) entity.setPhone(user.getPhone());
		if(StringUtils.isNotBlank(user.getPassword()))
			entity.setPassword(passwordEncoder.encode(user.getPassword()));

		RoleEntity role = null;
		try{
			role = entity.getRoleList().iterator().next();
			if(role.getType()!=user.getRole() && user.getRole()!=null){
				role = roleRepository.findOneByType(user.getRole());
			}
		}catch (Exception e){
			role = roleRepository.findOneByType(Constant.STAFF_ROLE);
		}finally {
			entity.getRoleList().clear();
			role.getUserList().add(entity);
			entity.getRoleList().add(role);
		}

		if(StringUtils.isNotBlank(user.getAvatar())) {
			String url = (String) CloudinaryUtils.getCloudinary().uploader().upload(user.getAvatar(), ObjectUtils.emptyMap()).get("url");
			entity.setAvatar(url);
		}
		repository.save(entity);
		return user.getId();
	}

	@Transactional
	@Override
	public void delete(List<Long> ids) {

		List<UserEntity> list = repository.findAllById(ids);
	}

	@Transactional
	@Override
	public boolean delete(long id) {
		try{
			repository.deleteById(id);
			return true;
		}catch (Exception e){
			return false;
		}
	}

	@Override
	public Map<Long,String> findAll() {
		Map<Long,String> map = new HashMap<>();
		repository.findAll().stream().forEach(e -> map.put(e.getId(),e.getFullname()));
		return map;
	}

}
