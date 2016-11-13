package com.epam.jmp.dr.task11.ablog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.jmp.dr.task11.ablog.entities.User;
import com.epam.jmp.dr.task11.ablog.repository.UserRepository;
import com.google.common.collect.Lists;

@Repository
@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository repo;

	public void setRepo(UserRepository repo) {
		this.repo = repo;
	}

	@Transactional(readOnly=true)
	public List<User> findAll() {
		return Lists.newArrayList(repo.findAll());
	}

	@Transactional(readOnly=true)
	public User findById(Integer id) {
		return repo.findOne(id);
	}

	public User save(User user) {
		return repo.save(user);
	}

	public User fingByLogin(String login) {
		List<User> result = Lists.newArrayList(repo.findByLogin(login));
		if(!result.isEmpty())
		{
			return result.get(0);
		}
		return null;
	}
	
	

}
