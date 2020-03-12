package com.javaweb.repository;

import java.util.List;
import java.util.Map;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.paging.Pageable;

public interface BuildingRepositoryCustom {
	List<BuildingEntity> findAll(Map<String,Object> properties,
			Pageable pageable,BuildingSearchBuilder builder);
}
