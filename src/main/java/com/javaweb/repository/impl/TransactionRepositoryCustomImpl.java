package com.javaweb.repository.impl;

import com.javaweb.Helper.MapToSqlSearch;
import com.javaweb.Helper.ObjectToMap;
import com.javaweb.dto.TransactionDTO;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.repository.TransactionRepositoryCustom;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Repository
public class TransactionRepositoryCustomImpl implements TransactionRepositoryCustom{

	@PersistenceContext
	private EntityManager em;



	public List<TransactionEntity> findAll(long customerId, Pageable pageable){
		String qlString = "select transaction from TransactionEntity transaction where transaction.customer="+customerId;
		if(pageable==null){
			pageable = PageRequest.of(1,10);
		}

		Query query = em.createQuery(qlString);
		query.setFirstResult((pageable.getPageNumber()-1)*pageable.getPageSize());
		query.setMaxResults(pageable.getPageSize());
		List<TransactionEntity> results = (List<TransactionEntity>) query.getResultList();

		return results;
		
	}

	@Override
	public long count(long customerId) {
		String qlString = "select count(transaction) from TransactionEntity transaction where transaction.customer="+customerId;
		Long count = (Long) em.createQuery(qlString).getSingleResult();
		return count.longValue();
	}

}
