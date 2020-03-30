package com.javaweb.repository;

import com.javaweb.entity.TransactionEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionRepositoryCustom {
	List<TransactionEntity> findAll(long customerId,long type, Pageable pageable);
	long count(long customerId);
}
