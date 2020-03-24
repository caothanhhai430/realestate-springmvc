package com.javaweb.repository;

import java.util.List;
import java.util.Map;

import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.dto.CustomerDTO;
import com.javaweb.entity.CustomerEntity;
import org.springframework.data.domain.Pageable;

public interface CustomerRepositoryCustom {
	List<CustomerEntity> findAll(CustomerSearchBuilder builder, Pageable pageable);
	public long count(CustomerSearchBuilder builder);
}
