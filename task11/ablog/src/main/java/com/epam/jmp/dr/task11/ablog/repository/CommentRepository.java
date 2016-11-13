package com.epam.jmp.dr.task11.ablog.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.epam.jmp.dr.task11.ablog.entities.Comment;
import com.epam.jmp.dr.task11.ablog.entities.Post;
import com.epam.jmp.dr.task11.ablog.entities.User;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
	
	List<Comment> findByAutorOrderByDateDesc(User autor);
	
	List<Comment> findByPostOrderByDateDesc(Post post);

}
