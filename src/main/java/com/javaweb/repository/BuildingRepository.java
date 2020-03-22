package com.javaweb.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.paging.Pageable;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long>,BuildingRepositoryCustom{
    @Modifying
    @Query("delete from BuildingEntity b where b.id = ?1")
    void delete(Long id);
}

