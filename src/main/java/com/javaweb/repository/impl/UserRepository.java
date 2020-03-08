package com.javaweb.repository.impl;

import java.util.List;
import java.util.Map;

import com.javaweb.Helper.MapToSqlSearch;
import com.javaweb.Helper.PageToSqlSearch;
import com.javaweb.builder.SqlBuilder;
import com.javaweb.dto.UserDTO;
import com.javaweb.entity.UserEntity;
import com.javaweb.paging.Pageable;

public class UserRepository extends SimpleRepository<UserEntity>{
	public List<UserEntity> findAll(Map<String,Object> properties,
			Pageable pageable,UserDTO user){
		
		String where = MapToSqlSearch.toSql(properties);
		String limit = PageToSqlSearch.toSql(pageable);
		
		SqlBuilder sqlBuilder = new SqlBuilder()
				.setTableName(getTableName());
		if(user!=null && user.getBuildingId()!=null) {
			sqlBuilder.setJoin("INNER").setTableName2("assignmentstaff")
			.setOn("A.id=B.staffid")
			.addWhere("AND buildingid="+user.getBuildingId());
		}
		String SQL = sqlBuilder.setLimit(limit).build();
		
		List<UserEntity> resutls =  findAll(SQL);
		return resutls;
	}
}
