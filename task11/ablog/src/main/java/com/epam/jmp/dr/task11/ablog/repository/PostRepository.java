package com.epam.jmp.dr.task11.ablog.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.epam.jmp.dr.task11.ablog.entities.Post;
import com.epam.jmp.dr.task11.ablog.entities.User;

public interface PostRepository extends CrudRepository<Post, Integer> {
	
	List<Post> findAllByOrderByCreationDateDesc();
	List<Post> findByAutorOrderByCreationDateDesc(User autor);

}
