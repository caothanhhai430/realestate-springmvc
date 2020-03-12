package com.javaweb.controller.admin;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.javaweb.dto.BuildingDTO;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.paging.impl.PageRequest;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.service.IBuildingService;
import com.javaweb.utils.EnumUtils;

@Controller
public class BuildingController {

	@Autowired
	private IBuildingService service;
	
	@Autowired
	private BuildingRepository repository;
	
	@GetMapping("/admin/building")
	public ModelAndView list(@ModelAttribute("buildingRequest") BuildingDTO buildingRequest,
			@ModelAttribute("PageRequest") PageRequest pageRequest) {
		
		ModelAndView modelView = new ModelAndView("building/buildinglist");
		
		Map<String,String> districtsMap = EnumUtils.getDistricts();
		Map<String,String> buildingTypesMap = EnumUtils.getBuildingTypes();
		
		List<BuildingDTO> results = service.findAll();

//		Iterable<BuildingEntity> data =   repository.findAll();
//		List<BuildingEntity> results = StreamSupport.stream(data.spliterator(),false).collect(Collectors.toList());
		
		modelView.addObject("buildingList", results);
		modelView.addObject("districtsMap", districtsMap);
		modelView.addObject("buildingTypesMap", buildingTypesMap);	
		return modelView;
	}
}
