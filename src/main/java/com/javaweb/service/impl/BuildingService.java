package com.javaweb.service.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.DTOConverter;
import com.javaweb.dto.BuildingDTO;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BuildingService implements IBuildingService{

	@Autowired
	BuildingRepository repository;

	@Override
	public List<BuildingDTO> findAll(BuildingDTO dto, Pageable pageable) {
		BuildingSearchBuilder builder = DTOConverter.toModel(dto,BuildingSearchBuilder.Builder.class).build();
		List<BuildingEntity> results =   repository.findAll(builder,pageable);
		return results.stream()
				.map(item-> {
					BuildingDTO temp = (BuildingDTO)DTOConverter.toModel(item,BuildingDTO.class);

					return temp;
				}).collect(Collectors.toList());
	}
	
	public List<BuildingDTO> findAll(){
		List<BuildingEntity> results =   repository.findAll();
		return results.stream()
				.map(item-> {
					BuildingDTO temp = (BuildingDTO)DTOConverter.toModel(item,BuildingDTO.class);

					return temp;
				}).collect(Collectors.toList());
	}

	@Override
	public BuildingDTO findById(long id) {
		BuildingEntity entity = repository.findById(id).get();
		BuildingDTO dto = DTOConverter.toModel(entity, BuildingDTO.class);

		return dto;
	}

	@Transactional
	public Long update(BuildingDTO building) {
		BuildingEntity oldBuildingEntity = repository.findById(building.getId()).get();
		BuildingEntity entity = DTOConverter.toModel(building, BuildingEntity.class);
		entity.setType(building.getBuildingTypeString());
		entity.setStaffList(oldBuildingEntity.getStaffList());
		entity.setId(oldBuildingEntity.getId());
		entity.setCreatedBy(oldBuildingEntity.getCreatedBy());
		entity.setCreatedDate(oldBuildingEntity.getCreatedDate());


		String[] s = building.getRentArea().replaceAll("\\s+", "").split(",");
		Set<RentAreaEntity> rentAreaEntityList = new HashSet<>();
		if(building.getRentArea()!=null && !building.getRentArea().isEmpty()) {
			Arrays.stream(s).map(e -> Integer.parseInt(e)).forEach(e->{
				RentAreaEntity rentAreaEntity = new RentAreaEntity(e);
				rentAreaEntity.setBuilding(entity);
				rentAreaEntityList.add(rentAreaEntity);
			});
		}
		entity.setRentAreaList(rentAreaEntityList);
		repository.save(entity);
		return entity.getId();
	}

	@Transactional
	@Override
	public boolean delete(Long id) {
		try{
			delete(Arrays.asList(id));
			return true;
		}catch (Exception e){
			return false;
		}
	}

	@Transactional
	@Override
	public boolean delete(List<Long> ids) {
		try{
			List<BuildingEntity> list = repository.findAllById(ids);
			repository.deleteAll(list);
			return true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	@Override
	public long count(BuildingDTO dto) {
		BuildingSearchBuilder builder = DTOConverter.toModel(dto,BuildingSearchBuilder.Builder.class).build();
		return repository.count(builder);
	}

	@Transactional
	@Override
	public Long save(BuildingDTO building) {


		BuildingEntity entity = DTOConverter.toModel(building, BuildingEntity.class);
		entity.setType(building.getBuildingTypeString());
		BuildingEntity result = repository.save(entity);
		String[] s = building.getRentArea().replaceAll("\\s+", "").split(",");
		Set<RentAreaEntity> rentAreaEntityList = new HashSet<>();
		result.setRentAreaList(rentAreaEntityList);
		if(building.getRentArea()!=null && !building.getRentArea().isEmpty()) {
			Arrays.stream(s).map(e -> Integer.parseInt(e)).forEach(e->{
				RentAreaEntity rentAreaEntity = new RentAreaEntity(e);
				rentAreaEntity.setBuilding(result);
				result.getRentAreaList().add(rentAreaEntity);
			});
		}
		BuildingEntity t = repository.save(result);
		return result.getId();
	}

}
