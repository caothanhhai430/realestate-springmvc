package com.javaweb.service.impl;

import com.javaweb.builder.UserSearchBuilder;
import com.javaweb.converter.DTOConverter;
import com.javaweb.dto.UserDTO;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.RoleEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IUserService;
import com.javaweb.utils.Constant;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService{

	@Autowired
	private UserRepository repository;

	@Autowired
	private BuildingRepository buildingRepository;
	@Autowired
	private CustomerRepository customerRepository;
	
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

	@Override
	public Long save(UserDTO user) {
		UserEntity entity = DTOConverter.toModel(user,UserEntity.class);
		return repository.save(entity).getId();
	}

	@Override
	public Long update(UserDTO user) {
		UserEntity entity = repository.findById(user.getId()).get();
		if(StringUtils.isNotBlank(user.getFullname())) entity.setFullname(user.getFullname());
		if(user.getStatus()!=null) entity.setStatus(user.getStatus());
		if(StringUtils.isNotBlank(user.getAvatar()))entity.setAvatar(user.getAvatar());
		if(StringUtils.isNotBlank(user.getPhone())) entity.setPhone(user.getPhone());
		if(StringUtils.isNotBlank(user.getPassword())) entity.setPhone(user.getPassword());

		RoleEntity role = new RoleEntity();
		HashSet<RoleEntity> roleSet = new HashSet<>();
		if(user.getRole()!=null) {
			role.setType(user.getRole());
			roleSet.add(role);
			entity.setRoleList(roleSet);
		}
		repository.save(entity);
		return user.getId();
	}

	@Override
	public void delete(List<Long> ids) {
		List<UserEntity> list = repository.findAllById(ids);
	}

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
