package com.epam.jmp.dr.task11.ablog.service;

import java.util.List;

import com.epam.jmp.dr.task11.ablog.entities.Post;
import com.epam.jmp.dr.task11.ablog.entities.User;

public interface PostService {
	
	List<Post> findAll();
	
	List<Post> findUserPosts(User user);
	
	Post findById(Integer id);
	
	Post save(Post post);

}
