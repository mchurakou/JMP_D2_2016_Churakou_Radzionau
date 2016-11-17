package com.epam.jmp.dr.task11.ablog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epam.jmp.dr.task11.ablog.entities.Image;
import com.epam.jmp.dr.task11.ablog.service.ImageService;

@RequestMapping("/img")
@Controller
public class ImageController {
	
	@Autowired
	private ImageService imageService;

	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public byte[] getImage(@PathVariable Integer id)
	{
		Image img = imageService.getImage(id);
		if(img != null)
		{
			return img.getImage();
		}
		return null;
	}

}
