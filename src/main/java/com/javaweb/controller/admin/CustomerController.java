package com.javaweb.controller.admin;

import com.javaweb.dto.CustomerDTO;
import com.javaweb.service.ICustomerService;
import com.javaweb.service.IUserService;
import com.javaweb.utils.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class CustomerController {

	@Autowired
	private ICustomerService service;

	@Autowired
	private IUserService userService;
	
	@GetMapping("/admin/customer")
	public ModelAndView list(@Valid @ModelAttribute("customerRequest") CustomerDTO customerRequest,
							  BindingResult result) {

		ModelAndView modelView = new ModelAndView("customer/customerlist");

		if (result.hasErrors()) {
			return modelView;
		}

		Map<Long,String> staffMap = userService.findAll();

		modelView.addObject("staffMap",staffMap);
		modelView.addObject("customerRequest", customerRequest);
		return modelView;
	}
}
