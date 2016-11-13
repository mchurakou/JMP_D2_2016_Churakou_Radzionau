package com.epam.jmp.dr.task11.ablog.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.epam.jmp.dr.task11.ablog.entities.Image;
import com.epam.jmp.dr.task11.ablog.entities.Post;

public interface ImageRepository extends CrudRepository<Image, Integer> {
	
	List<Image> findByPost(Post post);

}
