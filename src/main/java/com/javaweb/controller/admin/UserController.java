package com.javaweb.controller.admin;

import com.javaweb.dto.CustomerDTO;
import com.javaweb.service.ICustomerService;
import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {

	@Autowired
	private ICustomerService service;

	@Autowired
	private IUserService userService;
	
	@GetMapping("/admin/user")
	public ModelAndView list(@Valid @ModelAttribute("userRequest") CustomerDTO customerRequest,
							  BindingResult result) {

		ModelAndView modelView = new ModelAndView("user/userlist");

		if (result.hasErrors()) {
			return modelView;
		}


		return modelView;
	}
}
