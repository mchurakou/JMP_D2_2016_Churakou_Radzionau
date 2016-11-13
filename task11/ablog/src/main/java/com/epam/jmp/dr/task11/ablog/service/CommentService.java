package com.epam.jmp.dr.task11.ablog.service;

import java.util.List;

import com.epam.jmp.dr.task11.ablog.entities.Comment;
import com.epam.jmp.dr.task11.ablog.entities.Post;
import com.epam.jmp.dr.task11.ablog.entities.User;

public interface CommentService {
	
	List<Comment> findUserComments(User user);
	
	List<Comment> findPostComments(Post post);

}
