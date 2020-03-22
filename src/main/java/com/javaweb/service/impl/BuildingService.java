package com.javaweb.service.impl;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.javaweb.entity.RentAreaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.javaweb.converter.DTOConverter;
import com.javaweb.dto.BuildingDTO;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.service.IBuildingService;

@Service
public class BuildingService implements IBuildingService{

	@Autowired
	BuildingRepository repository;

	@Override
	public List<BuildingDTO> findAll(BuildingDTO dto, Pageable pageable) {

		List<BuildingEntity> results =   repository.findAll(dto,pageable);
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


//	@Override
//	public Long save(BuildingDTO building) {
//		BuildingRepository repository = new BuildingRepository();
//		
//		BuildingEntity entity = DTOConverter.toModel(building, BuildingEntity.class);
//		long id = repository.save(entity);
//		String rentAreaArr[] = building.getRentArea().replaceAll("\\s+","").split(",");
//		List<Integer> list = Arrays.stream(rentAreaArr).filter(e -> StringUtils.isNotBlank(e)).map(e -> Integer.parseInt(e)).collect(Collectors.toList());
//			
//		RentAreaRepository rentAreaRepository = new RentAreaRepository();
//		RentArea rentArea = new RentArea();
//		rentArea.setBuildingId((int)id);
//		list.stream().forEach(e-> {
//				rentArea.setValue(e);
//				rentAreaRepository.save(rentArea);
//			});
//		return id;
//	}
//	
	public Long update(BuildingDTO building) {
		BuildingEntity oldBuildingEntity = repository.findById(building.getId()).get();
		BuildingEntity entity = DTOConverter.toModel(building, BuildingEntity.class);
		entity.setType(building.getBuildingTypeString());
		BuildingEntity result = repository.save(entity);

		String[] s = building.getRentArea().replaceAll("\\s+", "").split(",");
		Set<RentAreaEntity> rentAreaEntityList = new HashSet<>();
		if(building.getRentArea()!=null && !building.getRentArea().isEmpty()) {
			Arrays.stream(s).map(e -> Integer.parseInt(e)).forEach(e->{
				RentAreaEntity rentAreaEntity = new RentAreaEntity(e);
				rentAreaEntity.setBuilding(result);
				rentAreaEntityList.add(rentAreaEntity);

			});
		}
		result.setRentAreaList(rentAreaEntityList);
		repository.save(entity);
		return result.getId();
	}

	@Override
	public boolean delete(Long id) {
		try{
			repository.deleteById(id);
			return true;
		}catch (Exception e){
			return false;
		}
	}

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

	@Override
	public long count(BuildingDTO dto) {
		return repository.count(dto);
	}

	@Override
	public Long save(BuildingDTO building) {


		BuildingEntity entity = DTOConverter.toModel(building, BuildingEntity.class);
		entity.setType(building.getBuildingTypeString());
		BuildingEntity result = repository.save(entity);

		String[] s = building.getRentArea().replaceAll("\\s+", "").split(",");
		Set<RentAreaEntity> rentAreaEntityList = new HashSet<>();
		if(building.getRentArea()!=null && !building.getRentArea().isEmpty()) {
			Arrays.stream(s).map(e -> Integer.parseInt(e)).forEach(e->{
				RentAreaEntity rentAreaEntity = new RentAreaEntity(e);
				rentAreaEntity.setBuilding(result);
				rentAreaEntityList.add(rentAreaEntity);

			});
		}
		result.setRentAreaList(rentAreaEntityList);
		repository.save(entity);
		return result.getId();
	}

}
