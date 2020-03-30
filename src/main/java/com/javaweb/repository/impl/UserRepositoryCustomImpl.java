package com.javaweb.repository.impl;

import com.javaweb.repository.UserRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;
import java.util.List;

@Repository
public class UserRepositoryCustomImpl  implements UserRepositoryCustom {

	@PersistenceContext
	private EntityManager em;


	@Override
	public List<Tuple> findStaffByBuildingId(Long id) {
		Query query = em.createNativeQuery("select u.id,u.fullname,IF(buildingid is not null,'checked','')" +
				" as checked from user u left join (select * from assignmentstaff where buildingid=?1) assign" +
				" on u.id=assign.staffid order by id",Tuple.class);
		query.setParameter(1,id);

		List<Tuple> results = query.getResultList();
		return results;
	}
	@Override
	public List<Tuple> findStaffByCustomerId(Long id) {
		Query query = em.createNativeQuery("select u.id,u.fullname,IF(customerid is not null,'checked','')" +
				" as checked from user u left join (select * from staff_customer where customerid=?1) assign" +
				" on u.id=assign.staffid order by id",Tuple.class);
		query.setParameter(1,id);
		List<Tuple> results = query.getResultList();
		return results;
	}
}
