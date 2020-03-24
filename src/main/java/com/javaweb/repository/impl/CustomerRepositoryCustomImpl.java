package com.javaweb.repository.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.javaweb.Helper.MapToSqlSearch;
import com.javaweb.Helper.ObjectToMap;
import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.entity.CustomerEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.javaweb.repository.CustomerRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class CustomerRepositoryCustomImpl  implements CustomerRepositoryCustom{

	@PersistenceContext
	private EntityManager em;



	public List<CustomerEntity> findAll(CustomerSearchBuilder builder, Pageable pageable){
		String qlString = "select customer from CustomerEntity customer " + buildQuery(builder);
		if(pageable==null){
			pageable = PageRequest.of(1,10);
		}

		Query query = em.createQuery(qlString);
		query.setFirstResult((pageable.getPageNumber()-1)*pageable.getPageSize());
		query.setMaxResults(pageable.getPageSize());
		List<CustomerEntity> results = (List<CustomerEntity>) query.getResultList();

		return results;
		
	}

	@Override
	public long count(CustomerSearchBuilder builder) {
		String qlString = "select count(customer) from CustomerEntity customer " + buildQuery(builder);
		Long count = (Long) em.createQuery(qlString).getSingleResult();
		return count.longValue();
	}

	private String buildQuery(CustomerSearchBuilder builder){
		String specialSQL = getSpecialQLString(builder);
		CustomerSearchBuilder singleFieldBuilder = new CustomerSearchBuilder.Builder()
				.setName(builder.getName())
				.setPhone(builder.getPhone())
				.setEmail(builder.getEmail())
				.build();
		Map<String,Object> map = ObjectToMap.toMap(singleFieldBuilder);
		String where = MapToSqlSearch.toSql(map);

		String qlString = "";
		if(builder.getStaffId()!=null) qlString += " inner join customer.staffList staff";
		qlString +=  "  where 1=1";
		if(!where.isEmpty() || !specialSQL.isEmpty()){
			qlString = qlString + " " +  where + " " + specialSQL;
		}
		if(builder.getStaffId()!=null) qlString = qlString + " AND staffid="+builder.getStaffId();
		return qlString;
	}

	private String getSpecialQLString(CustomerSearchBuilder builder) {
		StringBuilder sql = new StringBuilder();

		return sql.toString();
	}
}
