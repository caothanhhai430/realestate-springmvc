package com.javaweb.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.Helper.ObjectToMap;
import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.DTOConverter;
import com.javaweb.dto.BuildingDTO;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.StaffEntity;
import com.javaweb.paging.Pageable;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.service.IBuildingService;

@Service
public class BuildingService implements IBuildingService{

	@Autowired
	BuildingRepository repository;
	
	@Override
	public List<BuildingDTO> findAll(BuildingDTO dto, Pageable pageable) {
		BuildingSearchBuilder specialFieldBuilder = new BuildingSearchBuilder.Builder()
				.setBuildingType(dto.getBuildingType()).setAreaRentFrom(dto.getAreaRentFrom())
				.setAreaRentTo(dto.getAreaRentTo()).setCostRentFrom(dto.getCostRentFrom())
				.setCostRentTo(dto.getCostRentTo()).setStaffId(dto.getStaffId()).build();
		
		BuildingSearchBuilder singleFieldBuilder = new BuildingSearchBuilder.Builder()
				.setName(dto.getName()).setNumberOfBasement(dto.getNumberOfBasement())
				.setBuildingArea(dto.getBuildingArea()).setDistrict(dto.getDistrict())
				.setWard(dto.getWard()).setStreet(dto.getStreet()).build();
		
		Map<String,Object> properties = ObjectToMap.toMap(singleFieldBuilder);
		Iterable<BuildingEntity> data =   repository
				.findAll(properties,pageable,specialFieldBuilder);
		List<BuildingEntity> results = StreamSupport.stream(data.spliterator(),false).collect(Collectors.toList());
//		List<BuildingEntity> results= new BuildingRepository()
//				.findAll(properties,pageable,specialFieldBuilder);
		return results.stream()
				.map(item-> {
					BuildingDTO temp = (BuildingDTO)DTOConverter.toModel(item,BuildingDTO.class);
//					String tempRentArea = Arrays.stream(item.getRentAreaArr())
//								.map(e-> e.getValue().toString()).collect(Collectors.joining(","));
//					temp.setRentArea(tempRentArea);
					return temp;
				}).collect(Collectors.toList());
	}
	
	public List<BuildingDTO> findAll(){
		Iterable<BuildingEntity> data =   repository.findAll();
		List<BuildingEntity> results = StreamSupport.stream(data.spliterator(),false).collect(Collectors.toList());
		return results.stream()
				.map(item-> {
					BuildingDTO temp = (BuildingDTO)DTOConverter.toModel(item,BuildingDTO.class);
//					String tempRentArea = Arrays.stream(item.getRentAreaArr())
//								.map(e-> e.getValue().toString()).collect(Collectors.joining(","));
//					temp.setRentArea(tempRentArea);
					return temp;
				}).collect(Collectors.toList());
	}

//	@Override
//	public BuildingDTO findById(long id) {
//		BuildingEntity entity = new BuildingRepository().findById((int)id);
//		String tempRentArea = Arrays.stream(entity.getRentAreaArr())
//				.map(e-> e.getValue().toString()).collect(Collectors.joining(","));
	
//		BuildingDTO dto = DTOConverter.toModel(entity, BuildingDTO.class);
//		dto.setRentArea(tempRentArea);
//		return dto;
//	}
//
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
//	public Long update(BuildingDTO building) {
//		BuildingRepository repository = new BuildingRepository();
//		List<Integer> list = Arrays.stream(building.getRentArea().replaceAll("\\s+","")
//					.split(",")).map(e -> Integer.parseInt(e)).collect(Collectors.toList());
//	
//		List<RentArea> rentAreaFindAll = new RentAreaRepository()
//				.findByBuildingId(building.getId().intValue());
//		List<Integer> valueRentAreaList = rentAreaFindAll.stream()
//				.map(e->e.getValue()).collect(Collectors.toList());
//		
//		rentAreaFindAll.removeIf(e -> list.contains(e.getValue()));
//		long[] rentAreaNeedDelete = rentAreaFindAll.stream()
//				.mapToLong(e->e.getId().longValue()).toArray();
//		
//		RentAreaRepository rentAreaRepository = new RentAreaRepository();
//		RentArea rentArea = new RentArea();
//		rentArea.setBuildingId(building.getId().intValue());
//		list.stream().filter(e-> !valueRentAreaList.contains(e))
//			.forEach(e-> {
//				rentArea.setValue(e);
//				rentAreaRepository.save(rentArea);
//			});
//		rentAreaRepository.delete(rentAreaNeedDelete);
//		BuildingEntity entity = DTOConverter.toModel(building, BuildingEntity.class);
//		long id = repository.update(entity);
//		return id;
//	}

	@Override
	public Long save(BuildingDTO building) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long update(BuildingDTO building) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BuildingDTO findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
