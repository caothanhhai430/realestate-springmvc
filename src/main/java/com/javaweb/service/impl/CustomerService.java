package com.javaweb.service.impl;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.entity.RentAreaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.javaweb.converter.DTOConverter;
import com.javaweb.dto.CustomerDTO;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.service.ICustomerService;

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


//	@Override
//	public Long save(CustomerDTO customer) {
//		CustomerRepository repository = new CustomerRepository();
//		
//		CustomerEntity entity = DTOConverter.toModel(customer, CustomerEntity.class);
//		long id = repository.save(entity);
//		String rentAreaArr[] = customer.getRentArea().replaceAll("\\s+","").split(",");
//		List<Integer> list = Arrays.stream(rentAreaArr).filter(e -> StringUtils.isNotBlank(e)).map(e -> Integer.parseInt(e)).collect(Collectors.toList());
//
//		RentAreaRepository rentAreaRepository = new RentAreaRepository();
//		RentArea rentArea = new RentArea();
//		rentArea.setCustomerId((int)id);
//		list.stream().forEach(e-> {
//				rentArea.setValue(e);
//				rentAreaRepository.save(rentArea);
//			});
//		return id;
//	}
//
	public Long update(CustomerDTO customer) {
		CustomerEntity entity = DTOConverter.toModel(customer, CustomerEntity.class);

		repository.save(entity);
		return entity.getId();
	}

	@Override
	public boolean delete(Long id) {
		try{
			repository.deleteById(id);
			return true;
		}catch (Exception e){
			return false;
		}
	}

	@Override
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
	public Long save(CustomerDTO customer) {
		CustomerEntity entity = DTOConverter.toModel(customer, CustomerEntity.class);
		repository.save(entity);
		return entity.getId();
	}

}
