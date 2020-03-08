package com.javaweb.controller.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.javaweb.dto.BuildingDTO;
import com.javaweb.paging.impl.PageRequest;
import com.javaweb.service.IBuildingService;
import com.javaweb.utils.EnumUtils;

@Controller
public class BuildingController {

	@Autowired
	private IBuildingService service;
	
	@GetMapping("/admin/building")
	public ModelAndView list(@ModelAttribute("buildingRequest") BuildingDTO buildingRequest,
			@ModelAttribute("PageRequest") PageRequest pageRequest) {
		
		ModelAndView modelView = new ModelAndView("buildinglist");
		
		Map<String,String> districtsMap = EnumUtils.getDistricts();
		Map<String,String> buildingTypesMap = EnumUtils.getBuildingTypes();
		
		List<BuildingDTO> results = service.findAll(buildingRequest,pageRequest);
		modelView.addObject("buildingList", results);
		modelView.addObject("districtsMap", districtsMap);
		modelView.addObject("buildingTypesMap", buildingTypesMap);	
		return modelView;
	}
}
