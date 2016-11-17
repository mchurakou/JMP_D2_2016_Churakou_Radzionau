package com.epam.jmp.dr.task11.ablog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.jmp.dr.task11.ablog.entities.Image;
import com.epam.jmp.dr.task11.ablog.entities.Post;
import com.epam.jmp.dr.task11.ablog.repository.ImageRepository;
import com.google.common.collect.Lists;

@Repository
@Transactional
@Service("imageService")
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageRepository repo;
	
	public void setRepo(ImageRepository repo) {
		this.repo = repo;
	}

	public Image getImage(Integer id) {
		return repo.findOne(id);
	}

	public List<Image> getPostImages(Post post) {
		return Lists.newArrayList(repo.findByPost(post));
	}

}
