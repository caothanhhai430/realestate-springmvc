package com.javaweb.repository.impl;

import com.javaweb.entity.TransactionEntity;
import com.javaweb.repository.TransactionRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class TransactionRepositoryCustomImpl implements TransactionRepositoryCustom{

	@PersistenceContext
	private EntityManager em;



	public List<TransactionEntity> findAll(long customerId,long type, Pageable pageable){
		String qlString = "select transaction from TransactionEntity transaction" +
				" where transaction.customer="+customerId+" and transaction.type="+type +
				" order by transaction.createdDate desc ";

		Query query = em.createQuery(qlString);
		if(pageable!=null){
			query.setFirstResult((pageable.getPageNumber()-1)*pageable.getPageSize());
			query.setMaxResults(pageable.getPageSize());
		}
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
