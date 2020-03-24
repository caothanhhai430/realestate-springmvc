package com.javaweb.service;

import com.javaweb.dto.TransactionDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITransactionService {
	public List<TransactionDTO> findAll(TransactionDTO dto, Pageable pageable);
	public List<TransactionDTO> findAll();
	public TransactionDTO findById(long id);
	public Long save(TransactionDTO building);
	public long count(TransactionDTO dto);
}
