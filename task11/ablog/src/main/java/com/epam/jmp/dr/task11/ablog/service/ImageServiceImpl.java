package com.epam.jmp.dr.task11.ablog.service;

import java.util.List;

import com.epam.jmp.dr.task11.ablog.entities.Image;
import com.epam.jmp.dr.task11.ablog.entities.Post;
import com.epam.jmp.dr.task11.ablog.repository.ImageRepository;
import com.google.common.collect.Lists;

public class ImageServiceImpl implements ImageService {

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
