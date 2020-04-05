package com.javaweb.controller.admin;

import com.javaweb.dto.BuildingDTO;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.IUserService;
import com.javaweb.utils.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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
		List<Map<String,Object>> staffMap = userService.findAllActiveStaff();

		modelView.addObject("staffMap",staffMap);
		modelView.addObject("districtsMap", districtsMap);
		modelView.addObject("buildingTypesMap", buildingTypesMap);
		return modelView;
	}
}
