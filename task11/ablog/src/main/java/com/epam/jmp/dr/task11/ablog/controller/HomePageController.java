package com.epam.jmp.dr.task11.ablog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epam.jmp.dr.task11.ablog.service.TestService;

@RequestMapping("/")
@Controller
public class HomePageController {
	
	@Autowired
	private TestService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public String helloworld(Model model)
	{
		model.addAttribute("helloBodyMsg", service.getString());
		model.addAttribute("headerMsg", "Header message");
		model.addAttribute("footerMsg", "Footer message");
		return "hello";
	}

}
