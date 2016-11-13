package com.epam.jmp.dr.task11.ablog.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.epam.jmp.dr.task11.ablog.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	List<User> findByLogin(String login);
	List<User> findAllByOrderByLoginAsc();
}
