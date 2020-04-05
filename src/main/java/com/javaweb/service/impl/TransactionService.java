package com.javaweb.service.impl;

import com.javaweb.converter.DTOConverter;
import com.javaweb.dto.TransactionDTO;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService implements ITransactionService{

	@Autowired
	TransactionRepository repository;

	@Override
	public List<TransactionDTO> findAll(TransactionDTO dto, Pageable pageable) {
		List<TransactionEntity> results =   repository.findAll(dto.getCustomerId(),dto.getType(),pageable);
		return results.stream()
				.map(item-> {
					TransactionDTO temp = (TransactionDTO)DTOConverter.toModel(item,TransactionDTO.class);
					return temp;
				}).collect(Collectors.toList());
	}
	
	public List<TransactionDTO> findAll(){
		List<TransactionEntity> results =   repository.findAll();
		return results.stream()
				.map(item-> {
					TransactionDTO temp = (TransactionDTO)DTOConverter.toModel(item,TransactionDTO.class);

					return temp;
				}).collect(Collectors.toList());
	}

	@Override
	public TransactionDTO findById(long id) {
		TransactionEntity entity = repository.findById(id).get();
		TransactionDTO dto = DTOConverter.toModel(entity, TransactionDTO.class);

		return dto;
	}

	@Override
	public long count(TransactionDTO dto) {
		return repository.count(dto.getCustomerId());
	}

	@Override
	@Transactional
	public Long save(TransactionDTO transaction) {
		TransactionEntity entity = DTOConverter.toModel(transaction, TransactionEntity.class);
		repository.save(entity);
		return entity.getId();
	}
}
