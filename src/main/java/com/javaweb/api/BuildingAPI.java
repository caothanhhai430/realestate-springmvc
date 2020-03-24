package com.javaweb.api;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.javaweb.dto.BuildingDTO;
import com.javaweb.service.IBuildingService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api-server/building")
public class BuildingAPI{
	
	@Autowired
	IBuildingService service;
	
	@GetMapping("/list")
	public List<BuildingDTO> list(@ModelAttribute BuildingDTO buildingRequest){
		Pageable pageable = null;
		if(buildingRequest.getPage()!=null && buildingRequest.getSize()!=null)
			pageable = PageRequest.of(buildingRequest.getPage().intValue(),buildingRequest.getSize().intValue());

		List<BuildingDTO> results = service.findAll(buildingRequest,pageable);
		return results;
		
	}

	@GetMapping("/count")
	public long count(@ModelAttribute BuildingDTO buildingRequest){
		return service.count(buildingRequest);
	}


	@GetMapping
	public BuildingDTO findById(@RequestParam Long id){
		BuildingDTO result = service.findById(id);
		return result;

	}
	
	@RequestMapping(value = "",method = RequestMethod.POST,consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public BuildingDTO newBuilding(@RequestBody BuildingDTO building) {
			building.setCreatedBy("admin");
		building.setModifiedBy("admin");
		building.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		building.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		
		Long id = service.save(building);
		
		BuildingDTO result = service.findById(id);
		return result;
	}

	@RequestMapping(value = "",method = RequestMethod.PUT,consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	protected BuildingDTO updateBuilding(@RequestBody BuildingDTO building) {

		building.setCreatedBy("admin");
		building.setModifiedBy("admin");
		building.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		building.setModifiedDate(new Timestamp(System.currentTimeMillis()));

		service.update(building);
		BuildingDTO resp = service.findById(building.getId());
		return resp;
	}


	@RequestMapping(value = "",method = RequestMethod.DELETE,consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	protected boolean deleteBuilding(@RequestBody Map<String, Object> json) {
			List<Integer> tempIds = (List<Integer>) json.get("ids");
		List<Long> ids=  tempIds.stream().map(e -> (Long)e.longValue()).collect(Collectors.toList());
		return service.delete(ids);
	}


}
