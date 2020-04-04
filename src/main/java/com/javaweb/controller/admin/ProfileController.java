package com.javaweb.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController {
    @GetMapping("/admin/profile")
    public ModelAndView view(){
        ModelAndView modelAndView = new ModelAndView("profile/index");
        return modelAndView;
    }
}
