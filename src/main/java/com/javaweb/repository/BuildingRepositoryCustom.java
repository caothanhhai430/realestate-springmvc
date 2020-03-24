package com.javaweb.repository;

import java.util.List;
import java.util.Map;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.dto.BuildingDTO;
import com.javaweb.entity.BuildingEntity;
import org.springframework.data.domain.Pageable;

public interface BuildingRepositoryCustom {
	List<BuildingEntity> findAll(BuildingSearchBuilder builder, Pageable pageable);
	public long count(BuildingSearchBuilder builder);
}
