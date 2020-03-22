package com.javaweb.repository;

import java.util.List;
import java.util.Map;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.dto.BuildingDTO;
import com.javaweb.entity.BuildingEntity;
import org.springframework.data.domain.Pageable;

public interface BuildingRepositoryCustom {
	List<BuildingEntity> findAll(Map<String,Object> properties,
								 Pageable pageable, BuildingSearchBuilder builder);
	List<BuildingEntity> findAll(BuildingDTO dto, Pageable pageabdtole);
	public long count(BuildingDTO dto);
}
