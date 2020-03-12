package com.javaweb.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.repository.CrudRepository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.paging.Pageable;

public interface BuildingRepository extends CrudRepository<BuildingEntity, Long>,BuildingRepositoryCustom{
	
}

