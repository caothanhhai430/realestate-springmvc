package com.javaweb.service;

import java.util.List;

import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.dto.CustomerDTO;
import org.springframework.data.domain.Pageable;

public interface ICustomerService {
	public List<CustomerDTO> findAll(CustomerDTO dto, Pageable pageable);
	public List<CustomerDTO> findAll();
	public CustomerDTO findById(long id);
	public Long save(CustomerDTO building);
	public Long update(CustomerDTO building);
	public boolean delete(Long id);
	public boolean delete(List<Long> ids);
	public long count(CustomerDTO dto);
}
