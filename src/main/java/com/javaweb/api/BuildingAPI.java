package com.javaweb.api;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.dto.BuildingDTO;
import com.javaweb.paging.impl.PageRequest;
import com.javaweb.service.IBuildingService;

@RestController
@RequestMapping("/api-server/building")
public class BuildingAPI{
	@Autowired
	IBuildingService service;
	
	@GetMapping
	public List<BuildingDTO> list(@ModelAttribute PageRequest pageRequest,@ModelAttribute BuildingDTO buildRequest){
		List<BuildingDTO> results = service.findAll(buildRequest,pageRequest);
		return results;
		
	}
	
	@PostMapping
	public BuildingDTO newBuilding(@RequestBody BuildingDTO building) {
		building.setCreatedBy("admin");
		building.setModifiedBy("admin");
		building.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		building.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		
		Long id = service.save(building);
		
		BuildingDTO result = service.findById(id);
		return result;
	}
	
	@PutMapping
	protected BuildingDTO updateBuilding(@RequestBody BuildingDTO building) {
		
		building.setModifiedBy("admin");
		building.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		
		service.update(building);
		
		BuildingDTO resp = service.findById(building.getId());
		return resp;
	}
//
//	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		super.doDelete(req, resp);
//	}

}
