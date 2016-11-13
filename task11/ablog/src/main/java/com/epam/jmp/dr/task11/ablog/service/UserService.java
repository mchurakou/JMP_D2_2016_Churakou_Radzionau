package com.epam.jmp.dr.task11.ablog.service;

import java.util.List;

import com.epam.jmp.dr.task11.ablog.entities.User;

public interface UserService {
	
	List<User> findAll();
	User fingByLogin(String login);
	User findById(Integer id);
	User save(User user);
}
