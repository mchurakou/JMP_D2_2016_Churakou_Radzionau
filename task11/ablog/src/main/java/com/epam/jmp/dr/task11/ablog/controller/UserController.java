package com.epam.jmp.dr.task11.ablog.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epam.jmp.dr.task11.ablog.entities.User;
import com.epam.jmp.dr.task11.ablog.entities.UserType;
import com.epam.jmp.dr.task11.ablog.form.EditUserInfoForm;
import com.epam.jmp.dr.task11.ablog.form.LoginForm;
import com.epam.jmp.dr.task11.ablog.form.RegistrationForm;
import com.epam.jmp.dr.task11.ablog.service.UserService;

@RequestMapping("/")
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(method = RequestMethod.POST, value="/login")
	public String login(@ModelAttribute("credentials") LoginForm form, Model model, HttpServletRequest httpServletRequest)
	{
		List<String> errors = new ArrayList<String>();
		
		User u = userService.fingByLogin(form.getLogin());
		if(u == null || !u.getPassword().equals(form.getPassword()))
		{
			errors.add("User with such login or password is not exsists");
			model.addAttribute("errors", errors);
			return "loginForm";
		}
		
		httpServletRequest.getSession().setAttribute("currUser", u);
		return "redirect:/";

	}
	
	@RequestMapping(method = RequestMethod.GET, path="/login")
	public String redirectToLoginForm(Model model)
	{
		LoginForm form = new LoginForm();
		model.addAttribute("credentials", form);
		return "loginForm";
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/logout")
	public String logOut(Model model, HttpServletRequest httpServletRequest)
	{
		httpServletRequest.getSession().removeAttribute("currUser");
		return "redirect:/";
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/register")
	public String redirectToRegistrationForm(Model model)
	{
		RegistrationForm form = new RegistrationForm();
		model.addAttribute("registrationForm", form);
		return "registration";
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/register")
	public String registerUser(@ModelAttribute("registrationForm") RegistrationForm form, Model model, HttpServletRequest httpServletRequest, @RequestParam(value="user_avatar", required=false) Part avatar)
	{
		User u = new User();
		List<String> errors = new ArrayList<String>();
		boolean wasError = false;
		if(form.getLogin() == null || form.getLogin().equals(""))
		{
			errors.add("Please choose login name");
			wasError = true;
		}
		if(form.getPassword() == null || form.getPassword().equals(""))
		{
			errors.add("Password should not be empty");
			wasError = true;
		}
		if(form.getPassword() == null || !form.getPassword().equals(form.getPasswordRepeat()))
		{
			errors.add("Password not  match");
			wasError = true;
		}
		
		User exsistingUser = userService.fingByLogin(form.getLogin());
		if(exsistingUser != null)
		{
			errors.add("User with such login is already exsists");
			wasError = true;
		}
		
		if(wasError)
		{
			model.addAttribute("errors", errors);
			return "registration";
		}
		
		if(avatar != null)
		{
			byte[] avatarContent;
			try{
				InputStream inputStream = avatar.getInputStream();
				avatarContent = IOUtils.toByteArray(inputStream);
				u.setAvatar(avatarContent);
			} catch (IOException e) {
				errors.add("Error downloading avatar picture");
				model.addAttribute("errors", errors);
				return "registration";
			}
		}
		
		u.setEmail(form.getEmail());
		u.setLogin(form.getLogin());
		u.setName(form.getName());
		u.setPassword(form.getPassword());
		u.setUserType(UserType.USER);
		
		User loggedInUser = userService.save(u);
		
		httpServletRequest.getSession().setAttribute("currUser", loggedInUser);
		
		
		return "redirect:/";
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/user/edit")
	public String redirectToEditUserForm(Model model, HttpServletRequest httpServletRequest)
	{
		List<String> errors = new ArrayList<String>();
		User currUser = (User) httpServletRequest.getSession().getAttribute("currUser");
		if(currUser == null)
		{
			errors.add("Please login");
			model.addAttribute("errors", errors);
			model.addAttribute("editUserForm", new EditUserInfoForm());
			return "editUserInfo";
		}
		
		EditUserInfoForm form = new EditUserInfoForm();
		form.setEmail(currUser.getEmail());
		form.setName(currUser.getName());
		model.addAttribute("editUserForm", form);
		return "editUserInfo";
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/user/edit")
	public String editUser(@ModelAttribute("editUserForm") EditUserInfoForm form, Model model, HttpServletRequest httpServletRequest, @RequestParam(value="avatar", required=false) Part avatar)
	{
		
		List<String> errors = new ArrayList<String>();
		User currUser = (User) httpServletRequest.getSession().getAttribute("currUser");
		if(currUser == null)
		{
			errors.add("Please login");
			model.addAttribute("errors", errors);
			return "editUserInfo";
		}
		
		currUser.setName(form.getName());
		currUser.setEmail(form.getEmail());
		
		if(avatar != null)
		{
			byte[] avatarContent;
			try{
				InputStream inputStream = avatar.getInputStream();
				avatarContent = IOUtils.toByteArray(inputStream);
				currUser.setAvatar(avatarContent);
			} catch (IOException e) {
				errors.add("Error downloading avatar picture");
				model.addAttribute("errors", errors);
				return "editUserInfo";
			}
		}
		
		userService.save(currUser);
		
		return "redirect:/user/" + currUser.getLogin();
	}
	
	@RequestMapping(value = "/user/{loginName}", method = RequestMethod.GET)
	public String getUserInfo(@PathVariable String loginName, Model model)
	{
		List<String> errors = new ArrayList<String>();
		User u = userService.fingByLogin(loginName);
		if(u == null)
		{
			errors.add("Such user is not excists");
			model.addAttribute("errors", errors);
			return "userInfo";
		}
		model.addAttribute("user", u);
		return "userInfo";
	}
	
	@RequestMapping(value = "/user/{loginName}/avatar", method = RequestMethod.GET)
	@ResponseBody
	public byte[] getUserPhoto(@PathVariable String loginName)
	{
		User u = userService.fingByLogin(loginName);
		if(u != null)
		{
			return u.getAvatar();
		}
		return null;
	}
}
