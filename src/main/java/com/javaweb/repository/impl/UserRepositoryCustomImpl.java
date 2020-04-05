package com.javaweb.repository.impl;

import com.javaweb.Helper.MapToSqlSearch;
import com.javaweb.Helper.ObjectToMap;
import com.javaweb.builder.UserSearchBuilder;
import com.javaweb.entity.UserEntity;
import com.javaweb.repository.UserRepositoryCustom;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepositoryCustomImpl  implements UserRepositoryCustom {

	@PersistenceContext
	private EntityManager em;


	@Override
	public List<Tuple> findStaffByBuildingId(Long id) {
		Query query = em.createNativeQuery("select u.id,u.fullname,IF(buildingid is not null,'checked','')" +
				" as checked from user u left join (select * from assignmentstaff where buildingid=?1) assign " +
				" on u.id=assign.staffid  " +
				"inner join (select * from user_role ur inner join role r on ur.role_id=r.id" +
				"  where type=0) role on u.id=role.user_id "+
				" where u.status = 1 order by u.id",Tuple.class);
		query.setParameter(1,id);

		List<Tuple> results = query.getResultList();
		return results;
	}
	@Override
	public List<Tuple> findStaffByCustomerId(Long id) {
		Query query = em.createNativeQuery("select u.id,u.fullname,IF(customerid is not null,'checked','')" +
				" as checked from user u left join (select * from staff_customer where customerid=?1) assign" +
				" on u.id=assign.staffid " +
				" inner join (select * from user_role ur inner join role r " +
				"on ur.role_id=r.id  where type=0) role on u.id=role.user_id" +
				" where u.status = 1 order by id",Tuple.class);
		query.setParameter(1,id);
		List<Tuple> results = query.getResultList();
		return results;
	}
	public List<UserEntity> findAll(UserSearchBuilder builder, Pageable pageable){
		String qlString = "select u from UserEntity u " + buildQuery(builder,"u");
		if(pageable==null){
			pageable = PageRequest.of(1,10);
		}

		Query query = em.createQuery(qlString);
		query.setFirstResult((pageable.getPageNumber()-1)*pageable.getPageSize());
		query.setMaxResults(pageable.getPageSize());
		List<UserEntity> results = (List<UserEntity>) query.getResultList();

		return results;

	}

	@Override
	public long count(UserSearchBuilder builder) {
		String qlString = "select count(u) from UserEntity u " + buildQuery(builder,"u");
		Long count = (Long) em.createQuery(qlString).getSingleResult();
		return count.longValue();
	}

	@Override
	public List<Tuple> findAllActiveStaff() {
		Query query = em.createNativeQuery("select u.id,u.fullname from user u	inner join " +
				"(select * from user_role ur inner join role r on ur.role_id=r.id  " +
				"where type=0) role on u.id=role.user_id where u.status=1 order by id",Tuple.class);
				List<Tuple> results = query.getResultList();
		return results;
	}

	private String buildQuery(UserSearchBuilder builder,String prefix){
		Map<String,Object> map = ObjectToMap.toMap(builder);
		String where = MapToSqlSearch.toSql(map,prefix);

		String qlString =  " where 1=1 ";
		if(!where.isEmpty()){
			qlString = qlString+   where;
		}
		return qlString;
	}

}
