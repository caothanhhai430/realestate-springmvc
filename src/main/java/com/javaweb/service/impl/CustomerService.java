package com.javaweb.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.javaweb.Helper.ObjectToMap;
import com.javaweb.builder.StaffSearchBuilder;
import com.javaweb.converter.DTOConverter;
import com.javaweb.dto.CustomerDTO;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.paging.Pageable;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {

	@Autowired
	CustomerRepository repository;

	@Override
	public List<CustomerDTO> findAll(CustomerDTO dto, Pageable pageable) {
		StaffSearchBuilder singleFieldBuilder = new StaffSearchBuilder.Builder()
				.setName(dto.getName()).setEmail(dto.getEmail())
				.setPhone(dto.getPhone()).build();
		StaffSearchBuilder specialFieldBuilder = new StaffSearchBuilder.Builder()
				.setStaffId(dto.getStaffId()).build();
		
		Map<String,Object> properties = ObjectToMap.toMap(singleFieldBuilder);
		return repository.findAll().stream()
				.map(item-> (CustomerDTO)DTOConverter.toModel(item, CustomerDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public CustomerDTO findById(long id) {
		return null;
	}


	@Override
	public Long save(CustomerDTO customer) {
		CustomerEntity entity = DTOConverter.toModel(customer, CustomerEntity.class);
		return repository.save(entity).getId();
	}

	@Override
	public Long update(CustomerDTO customer) {
		CustomerEntity entity = DTOConverter.toModel(customer, CustomerEntity.class);
		return repository.save(entity).getId();
	}

	@Override
	public void delete(long[] id) {

	}

	@Override
	public void delete(long id) {

	}


}
