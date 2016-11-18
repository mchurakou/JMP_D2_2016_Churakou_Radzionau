package com.epam.jmp.dr.task11.ablog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.jmp.dr.task11.ablog.entities.Post;
import com.epam.jmp.dr.task11.ablog.entities.User;
import com.epam.jmp.dr.task11.ablog.repository.PostRepository;
import com.google.common.collect.Lists;

@Repository
@Transactional
@Service("postService")
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository repo;
	
	public void setRepo(PostRepository repo) {
		this.repo = repo;
	}

	public List<Post> findAll() {
		return Lists.newArrayList(repo.findAllByOrderByCreationDateDesc());
	}

	public List<Post> findUserPosts(User user) {
		return Lists.newArrayList(repo.findByAutorOrderByCreationDateDesc(user));
	}

	public Post findById(Integer id) {
		return repo.findOne(id);
	}

	public Post save(Post post) {
		return repo.save(post);
	}

	public void delete(Integer id) {
		repo.delete(id);
		
	}

}
