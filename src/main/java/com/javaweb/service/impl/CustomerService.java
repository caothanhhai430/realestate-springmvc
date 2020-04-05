package com.javaweb.service.impl;

import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.converter.DTOConverter;
import com.javaweb.dto.CustomerDTO;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService implements ICustomerService{

	@Autowired
	CustomerRepository repository;

	@Override
	public List<CustomerDTO> findAll(CustomerDTO dto, Pageable pageable) {
		CustomerSearchBuilder builder = DTOConverter.toModel(dto,CustomerSearchBuilder.Builder.class).build();
		List<CustomerEntity> results =   repository.findAll(builder,pageable);
		return results.stream()
				.map(item-> {
					CustomerDTO temp = (CustomerDTO)DTOConverter.toModel(item,CustomerDTO.class);

					return temp;
				}).collect(Collectors.toList());
	}
	
	public List<CustomerDTO> findAll(){
		List<CustomerEntity> results =   repository.findAll();
		return results.stream()
				.map(item-> {
					CustomerDTO temp = (CustomerDTO)DTOConverter.toModel(item,CustomerDTO.class);

					return temp;
				}).collect(Collectors.toList());
	}

	@Override
	public CustomerDTO findById(long id) {
		CustomerEntity entity = repository.findById(id).get();
		CustomerDTO dto = DTOConverter.toModel(entity, CustomerDTO.class);

		return dto;
	}

	@Transactional
	@Override
	public Long update(CustomerDTO customer) {
		CustomerEntity entity = DTOConverter.toModel(customer, CustomerEntity.class);
		CustomerEntity existEntity = repository.findById(customer.getId()).get();
		entity.setStaffList(existEntity.getStaffList());
		entity.setTransactionList(existEntity.getTransactionList());
		entity.setCreatedBy(existEntity.getCreatedBy());
		entity.setCreatedDate(existEntity.getCreatedDate());
		repository.save(entity);
		return entity.getId();
	}

	@Override
	@Transactional
	public boolean delete(Long id) {
		try{
			delete(Arrays.asList(id));
			return true;
		}catch (Exception e){
			return false;
		}
	}

	@Override
	@Transactional
	public boolean delete(List<Long> ids) {
		try{
			List<CustomerEntity> list = repository.findAllById(ids);
			repository.deleteAll(list);
			return true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public long count(CustomerDTO dto) {
		CustomerSearchBuilder builder = DTOConverter.toModel(dto,CustomerSearchBuilder.Builder.class).build();
		return repository.count(builder);
	}

	@Override
	@Transactional
	public Long save(CustomerDTO customer) {
		CustomerEntity entity = DTOConverter.toModel(customer, CustomerEntity.class);
		repository.save(entity);
		return entity.getId();
	}

}
