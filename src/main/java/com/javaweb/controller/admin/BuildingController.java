package com.javaweb.controller.admin;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.javaweb.dto.UserDTO;
import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.javaweb.dto.BuildingDTO;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.service.IBuildingService;
import com.javaweb.utils.EnumUtils;

import javax.validation.Valid;

@Controller
public class BuildingController {

	@Autowired
	private IBuildingService service;

	@Autowired
	private IUserService userService;
	
	@GetMapping("/admin/building")
	public ModelAndView list(@Valid @ModelAttribute("buildingRequest") BuildingDTO buildingRequest,
							  BindingResult result) {

		ModelAndView modelView = new ModelAndView("building/buildinglist");

		if (result.hasErrors()) {
			return modelView;
		}

		Map<String,String> districtsMap = EnumUtils.getDistricts();
		Map<String,String> buildingTypesMap = EnumUtils.getBuildingTypes();

		Map<Long,String> staffMap = userService.findAll();


		modelView.addObject("staffMap",staffMap);
		modelView.addObject("buildingRequest", buildingRequest);
		modelView.addObject("districtsMap", districtsMap);
		modelView.addObject("buildingTypesMap", buildingTypesMap);
		return modelView;
	}
}
