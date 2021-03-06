package com.javaweb.service;

import java.util.List;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.dto.BuildingDTO;
import org.springframework.data.domain.Pageable;

public interface IBuildingService {
	public List<BuildingDTO> findAll(BuildingDTO dto, Pageable pageable);
	public List<BuildingDTO> findAll();
	public BuildingDTO findById(long id);
	public Long save(BuildingDTO building);
	public Long update(BuildingDTO building);
	public boolean delete(Long id);
	public boolean delete(List<Long> ids);
	public long count(BuildingDTO dto);
}
