package com.epam.jmp.dr.task11.ablog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epam.jmp.dr.task11.ablog.entities.User;
import com.epam.jmp.dr.task11.ablog.entities.UserType;
import com.epam.jmp.dr.task11.ablog.service.PostService;
import com.epam.jmp.dr.task11.ablog.service.TestService;
import com.epam.jmp.dr.task11.ablog.service.UserService;

@RequestMapping("/")
@Controller
public class HomePageController {
	
	@Autowired
	private TestService service;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String helloworld(Model model)
	{
		model.addAttribute("helloBodyMsg", service.getString());
		model.addAttribute("headerMsg", "Header message");
		model.addAttribute("footerMsg", "Footer message");
		return "hello";
	}
	
	@RequestMapping(value = "/tc", method = RequestMethod.GET)
	public String testUserCreate(Model model)
	{
		User u = new User();
		u.setLogin("admin");
		u.setEmail("test@email.com");
		u.setName("name");
		u.setPassword("passw");
		u.setUserType(UserType.ADMIN);
		userService.save(u);
		return "hello";
	}
	
	@RequestMapping(value = "/f", method = RequestMethod.GET)
	public String getUser(Model model)
	{
		model.addAttribute("user", userService.fingByLogin("admin"));
		return "hello";
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	

}
