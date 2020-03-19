package com.javaweb.repository.impl;

import java.util.List;

import com.javaweb.repository.UserRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public class UserRepositoryCustomImpl  implements UserRepositoryCustom {

	@PersistenceContext
	private EntityManager em;


	@Override
	public List<Tuple> findStaffByBuildingId(Long id) {
		Query query = em.createNativeQuery("select u.id,u.username,IF(buildingid is not null,'checked','') " +
				"as checked from user u left join assignmentstaff assign on u.id=assign.staffid " +
				"where buildingid="+ id + " or buildingid is null order by id",Tuple.class);
		List<Tuple> results = query.getResultList();
		return results;
	}
}
