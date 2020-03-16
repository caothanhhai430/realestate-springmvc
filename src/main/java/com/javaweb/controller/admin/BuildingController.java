package com.javaweb.controller.admin;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.javaweb.dto.BuildingDTO;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.paging.impl.PageRequest;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.service.IBuildingService;
import com.javaweb.utils.EnumUtils;

import javax.validation.Valid;

@Controller
public class BuildingController {

	@Autowired
	private IBuildingService service;
	
	@Autowired
	private BuildingRepository repository;

	
	@GetMapping("/admin/building")
	public ModelAndView list(@Valid @ModelAttribute("buildingRequest") BuildingDTO buildingRequest,
							 @ModelAttribute("PageRequest") PageRequest pageRequest, BindingResult result) {

		ModelAndView modelView = new ModelAndView("building/buildinglist");

		if (result.hasErrors()) {
			return modelView;
		}

		Map<String,String> districtsMap = EnumUtils.getDistricts();
		Map<String,String> buildingTypesMap = EnumUtils.getBuildingTypes();
		
		List<BuildingDTO> results = service.findAll(buildingRequest,pageRequest);



		modelView.addObject("buildingRequest", buildingRequest);
		modelView.addObject("buildingList", results);
		modelView.addObject("districtsMap", districtsMap);
		modelView.addObject("buildingTypesMap", buildingTypesMap);
		return modelView;
	}
}
