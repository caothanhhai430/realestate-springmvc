package com.javaweb.repository.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.javaweb.builder.SqlBuilder;
import com.javaweb.dto.AssignmentStaffDTO;
import com.javaweb.entity.AssignmentStaffEntity;

public class AssignmentStaffRepository extends SimpleRepository<AssignmentStaffEntity>{
	public Long update(AssignmentStaffDTO dto) {
		if(dto.getBuildingId().length==1) {
			SqlBuilder sqlBuilder = new SqlBuilder()
					.setTableName(getTableName())
					.addWhere("AND buildingid="+dto.getBuildingId()[0]);
			List<AssignmentStaffEntity> results =  findAll(sqlBuilder.build());
			List<Integer> mapStaffId = results.stream().
					map(e -> e.getStaffId()).collect(Collectors.toList());
			List<Integer> listStaffId =  Arrays.stream(dto.getStaffId())
								.collect(Collectors.toList());
										
			results.removeIf(e-> listStaffId.contains(e.getStaffId()));

			listStaffId.removeIf(e -> mapStaffId.contains(e));
			
			long[] arrIdDelete = results.stream()
					.mapToLong(e-> e.getId().longValue()).toArray();
			
			delete(arrIdDelete);
			
			AssignmentStaffEntity entity = new AssignmentStaffEntity();
			entity.setBuildingId(dto.getBuildingId()[0]);
			listStaffId.stream().forEach(e-> {
				entity.setStaffId(e);
				save(entity);
			});
			return 0L;
		}
		
		return -1L;		
	}
}
