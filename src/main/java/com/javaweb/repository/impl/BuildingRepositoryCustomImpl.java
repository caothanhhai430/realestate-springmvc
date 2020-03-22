package com.javaweb.repository.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.javaweb.Helper.MapToSqlSearch;
import com.javaweb.Helper.ObjectToMap;
import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.dto.BuildingDTO;
import com.javaweb.entity.BuildingEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class BuildingRepositoryCustomImpl  implements BuildingRepositoryCustom{

	@PersistenceContext
	private EntityManager em;


	@Override
	public List<BuildingEntity> findAll(Map<String, Object> properties, Pageable pageable, BuildingSearchBuilder builder) {
		return null;
	}

	public List<BuildingEntity> findAll(BuildingDTO dto, Pageable pageable){

		String specialSQL = getSpecialSQL(dto);
		BuildingSearchBuilder builder = new BuildingSearchBuilder.Builder()
				.setName(dto.getName())
				.setBuildingArea(dto.getBuildingArea())
				.setNumberOfBasement(dto.getNumberOfBasement())
				.setDistrict(dto.getDistrict())
				.setWard(dto.getWard())
				.setStreet(dto.getStreet())
				.build();
		Map<String,Object> map = ObjectToMap.toMap(builder);
		String where = MapToSqlSearch.toSql(map);

		Query query = em.createQuery("select building from BuildingEntity building  where 1=1 " +
				where + " " +
				getSpecialSQL(dto));

		if(pageable==null){
			pageable = PageRequest.of(1,10);
		}

		query.setFirstResult((pageable.getPageNumber()-1)*pageable.getPageSize());
		query.setMaxResults(pageable.getPageSize());
		List<BuildingEntity> results = (List<BuildingEntity>) query.getResultList();

		return results;
		
	}

	@Override
	public long count(BuildingDTO dto) {
		String specialSQL = getSpecialSQL(dto);
		BuildingSearchBuilder builder = new BuildingSearchBuilder.Builder()
				.setName(dto.getName())
				.setBuildingArea(dto.getBuildingArea())
				.setNumberOfBasement(dto.getNumberOfBasement())
				.setDistrict(dto.getDistrict())
				.setWard(dto.getWard())
				.setStreet(dto.getStreet())
				.build();
		Map<String,Object> map = ObjectToMap.toMap(builder);
		String where = MapToSqlSearch.toSql(map);

		Query query = em.createQuery("select count(building) from BuildingEntity building  where 1=1 " +
				where + " " + specialSQL);
		Long count = (Long) query.getSingleResult();
		return count.longValue();
	}

	private  String getSingleFieldSQL(BuildingDTO dto){
		StringBuilder sql = new StringBuilder();


		return  sql.toString();
	}


	private String getSpecialSQL(BuildingDTO dto) {
		StringBuilder sql = new StringBuilder();
		String prefix = "building.";
		if(dto.getRentCostFrom()!=null) {
			sql.append(" AND " + prefix + "rentCost >="+dto.getRentCostFrom());
		}
		if(dto.getRentCostTo()!=null) {
			sql.append(" AND " + prefix + "rentCost <="+dto.getRentCostTo());
		}
		
		if(dto.getRentAreaFrom()!=null || dto.getRentAreaTo()!=null) {
			sql.append(" AND EXISTS (SELECT ra FROM RentAreaEntity ra WHERE ra.building=" + prefix + "id");

			if(dto.getRentAreaFrom()!=null){
				sql.append(" AND ra.value >="+dto.getRentAreaFrom());
			}
			if(dto.getRentAreaTo()!=null){
				sql.append(" AND ra.value <="+dto.getRentAreaTo());
			}
			sql.append(")");
		}
		if(dto.getBuildingType()!=null && dto.getBuildingType().length>0) {
			
			sql.append(" AND (");
			String s = Arrays.stream(dto.getBuildingType())
			.map(e->"type LIKE '%"+e+"%'").collect(Collectors.joining(" OR "));
			sql.append(s+")");
			
	}
		return sql.toString();
	}
}
