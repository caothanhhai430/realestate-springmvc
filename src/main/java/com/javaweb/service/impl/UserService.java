package com.javaweb.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.javaweb.Helper.ObjectToMap;
import com.javaweb.converter.DTOConverter;
import com.javaweb.dto.UserDTO;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.paging.Pageable;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;

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
		Map<String,Object> properties = ObjectToMap.toMap(dto);


		List<UserDTO> result = repository.findAll().stream()
				.map(item-> (UserDTO)DTOConverter.toModel(item,UserDTO.class))
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long save(UserDTO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long update(UserDTO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long[] id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<Long,String> findAll() {
		Map<Long,String> map = new HashMap<>();
		repository.findAll().stream().forEach(e -> map.put(e.getId(),e.getFullname()));
		return map;
	}

}
