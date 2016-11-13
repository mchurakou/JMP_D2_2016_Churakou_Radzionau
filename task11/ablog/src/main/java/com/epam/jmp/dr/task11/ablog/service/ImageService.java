package com.epam.jmp.dr.task11.ablog.service;

import java.util.List;

import com.epam.jmp.dr.task11.ablog.entities.Image;
import com.epam.jmp.dr.task11.ablog.entities.Post;

public interface ImageService {
	
	Image getImage(Integer id);
	
	List<Image> getPostImages(Post post);

}
