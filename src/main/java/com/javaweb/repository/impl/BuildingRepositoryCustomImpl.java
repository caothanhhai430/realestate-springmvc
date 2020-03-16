package com.javaweb.repository.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.javaweb.Helper.MapToSqlSearch;
import com.javaweb.Helper.PageToSqlSearch;
import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.builder.SqlBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentArea;
import com.javaweb.paging.Pageable;
import com.javaweb.repository.BuildingRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class BuildingRepositoryCustomImpl extends SimpleRepository<BuildingEntity> implements BuildingRepositoryCustom{

	@PersistenceContext
	private EntityManager em;
	

	public List<BuildingEntity> findAll(Map<String,Object> properties,
			Pageable pageable,BuildingSearchBuilder builder){

		String s = getSpecialSQL(builder);
		List<BuildingEntity> results = (List<BuildingEntity>) em.createQuery(
				"select building from BuildingEntity building inner join building.staffList staff where " +
						"1=1 AND staff.id=8 "  + getSpecialSQL(builder))
				.getResultList();

		return results;
		
	}
	
	private String getSpecialSQL(BuildingSearchBuilder builder) {
		StringBuilder sql = new StringBuilder();
		String prefix = "building.";
		if(builder.getRentCostFrom()!=null) {
			sql.append(" AND " + prefix + "rentCost >="+builder.getRentCostFrom());
		}
		if(builder.getRentCostTo()!=null) {
			sql.append(" AND " + prefix + "rentCost <="+builder.getRentCostTo());
		}
		
		if(builder.getRentAreaFrom()!=null || builder.getRentAreaTo()!=null) {
			sql.append(" AND EXISTS (SELECT ra FROM RentArea ra WHERE ra.building=" + prefix + "id");

			if(builder.getRentAreaFrom()!=null){
				sql.append(" AND ra.value >="+builder.getRentAreaFrom());
			}
			if(builder.getRentAreaTo()!=null){
				sql.append(" AND ra.value <="+builder.getRentAreaTo());
			}
			sql.append(")");
		}
		if(builder.getBuildingType()!=null && builder.getBuildingType().length>0) {
			
			sql.append(" AND (");
			String s = Arrays.stream(builder.getBuildingType())
			.map(e->"type LIKE '%"+e+"%'").collect(Collectors.joining(" OR "));
			sql.append(s+")");
			
	}
		return sql.toString();
	}
}
