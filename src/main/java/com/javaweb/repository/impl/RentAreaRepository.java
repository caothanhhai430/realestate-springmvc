package com.javaweb.repository.impl;

import java.util.List;

import com.javaweb.builder.SqlBuilder;
import com.javaweb.entity.RentArea;

public class RentAreaRepository extends SimpleRepository<RentArea>{
	public List<RentArea> findByBuildingId(int id){
		
		SqlBuilder sqlBuilder = new SqlBuilder()
				.setTableName(getTableName())
				.addWhere("AND buildingid="+ id);
	
		String SQL = sqlBuilder.build();
		
		List<RentArea> resutls =  findAll(SQL);
		return resutls;
	}
}
