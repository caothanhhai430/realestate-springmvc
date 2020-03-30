package com.javaweb.repository.impl;

import com.javaweb.Helper.MapToSqlSearch;
import com.javaweb.Helper.ObjectToMap;
import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.BuildingRepositoryCustom;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryCustomImpl  implements BuildingRepositoryCustom{

	@PersistenceContext
	private EntityManager em;



	public List<BuildingEntity> findAll(BuildingSearchBuilder builder, Pageable pageable){
		String qlString = "select building from BuildingEntity building " + buildQuery(builder);
		if(pageable==null){
			pageable = PageRequest.of(1,10);
		}

		Query query = em.createQuery(qlString);
		query.setFirstResult((pageable.getPageNumber()-1)*pageable.getPageSize());
		query.setMaxResults(pageable.getPageSize());
		List<BuildingEntity> results = (List<BuildingEntity>) query.getResultList();

		return results;
		
	}

	@Override
	public long count(BuildingSearchBuilder builder) {
		String qlString = "select count(building) from BuildingEntity building " + buildQuery(builder);
		Long count = (Long) em.createQuery(qlString).getSingleResult();
		return count.longValue();
	}

	private String buildQuery(BuildingSearchBuilder builder){
		String specialSQL = getSpecialQLString(builder);
		BuildingSearchBuilder singleFieldBuilder = new BuildingSearchBuilder.Builder()
				.setName(builder.getName())
				.setBuildingArea(builder.getBuildingArea())
				.setNumberOfBasement(builder.getNumberOfBasement())
				.setDistrict(builder.getDistrict())
				.setWard(builder.getWard())
				.setStreet(builder.getStreet())
				.build();
		Map<String,Object> map = ObjectToMap.toMap(singleFieldBuilder);
		String where = MapToSqlSearch.toSql(map);

		String qlString = "";
		if(builder.getStaffId()!=null) qlString += " inner join building.staffList staff";
		qlString +=  "  where 1=1 ";
		if(!where.isEmpty() || !specialSQL.isEmpty()){
			qlString = qlString+   where + " " + specialSQL;
		}
		if(builder.getStaffId()!=null) qlString = qlString + " AND staffid="+builder.getStaffId();
		return qlString;
	}

	private String getSpecialQLString(BuildingSearchBuilder builder) {
		StringBuilder sql = new StringBuilder();
		String prefix = "building.";
		if(builder.getRentCostFrom()!=null) {
			sql.append(" AND " + prefix + "rentCost >="+builder.getRentCostFrom());
		}
		if(builder.getRentCostTo()!=null) {
			sql.append(" AND " + prefix + "rentCost <="+builder.getRentCostTo());
		}
		
			if(builder.getRentAreaFrom()!=null || builder.getRentAreaTo()!=null) {
			sql.append(" AND EXISTS (SELECT ra FROM RentAreaEntity ra WHERE ra.building=" + prefix + "id");

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
