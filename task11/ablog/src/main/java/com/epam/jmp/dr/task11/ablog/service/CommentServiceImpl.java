package com.epam.jmp.dr.task11.ablog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.jmp.dr.task11.ablog.entities.Comment;
import com.epam.jmp.dr.task11.ablog.entities.Post;
import com.epam.jmp.dr.task11.ablog.entities.User;
import com.epam.jmp.dr.task11.ablog.repository.CommentRepository;
import com.google.common.collect.Lists;

@Repository
@Transactional
@Service("commentService")
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository repo;
	
	public void setRepo(CommentRepository repo) {
		this.repo = repo;
	}

	public List<Comment> findUserComments(User user) {
		return Lists.newArrayList(repo.findByAutorOrderByDateDesc(user));
	}

	public List<Comment> findPostComments(Post post) {
		return Lists.newArrayList(repo.findByPostOrderByDateDesc(post));
	}

	public Comment save(Comment comment) {
		return repo.save(comment);
	}

}
