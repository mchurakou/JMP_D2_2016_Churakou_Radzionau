package com.epam.jmp.dr.task11.ablog.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.epam.jmp.dr.task11.ablog.entities.Comment;
import com.epam.jmp.dr.task11.ablog.entities.Image;
import com.epam.jmp.dr.task11.ablog.entities.Post;
import com.epam.jmp.dr.task11.ablog.entities.User;
import com.epam.jmp.dr.task11.ablog.form.PostCreateForm;
import com.epam.jmp.dr.task11.ablog.service.PostService;

@RequestMapping("/post")
@Controller
public class PostController {
	
	@Autowired
	private PostService postService;

	public void setPostService(PostService postService) {
		this.postService = postService;
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/{id}/delete")
	public String deletePost(@PathVariable Integer id, Model model, HttpServletRequest httpServletRequest)
	{
		List<String> errors = new ArrayList<String>();
		User currUser = (User) httpServletRequest.getSession().getAttribute("currUser");
		if(currUser == null)
		{
			errors.add("Please login to delete posts");
			model.addAttribute("errors", errors);
			return "error";
		}
		
		Post post = postService.findById(id);
		if(!post.getAutor().getLogin().equals(currUser.getLogin()))
		{
			errors.add("You can delete only your posts");
			model.addAttribute("errors", errors);
			return "error";
		}
		
		postService.delete(id);
		
		return "redirect:/";
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/{id}")
	public String viewPost(@PathVariable Integer id, Model model) {
		Post post = postService.findById(id);
		if(post == null)
		{
			List<String> errors = new ArrayList<String>();
			errors.add("Post not found");
			model.addAttribute("errors", errors);
			return "error";
		}
		model.addAttribute("post", post);
		return "viewPost";
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/create")
	public String redirectToCreatePostForm(Model model, HttpServletRequest httpServletRequest) {
		PostCreateForm form = new PostCreateForm();
		model.addAttribute("createPostForm", form);
		User currUser = (User) httpServletRequest.getSession().getAttribute("currUser");
		if(currUser == null)
		{
			List<String> errors = new ArrayList<String>();
			errors.add("Please login to post something");
			model.addAttribute("errors", errors);
		}
		return "createPost";
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/create")
	public String createPost(@ModelAttribute("createPostForm") PostCreateForm form, Model model, HttpServletRequest httpServletRequest, @RequestParam(value="pic[]", required=false) Part[] pics)
	{
		User currUser = (User) httpServletRequest.getSession().getAttribute("currUser");
		List<String> errors = new ArrayList<String>();
		if(currUser == null)
		{
			errors.add("Please login to post something");
			model.addAttribute("errors", errors);
			return "createPost";
		}
		
		if(form.getTitle() == null || form.getTitle().equals(""))
		{
			errors.add("Please put the title to your post");
			model.addAttribute("errors", errors);
			return "createPost";
		}
		
		Post post = new Post();
		post.setText(form.getText());
		post.setTitle(form.getTitle());
		post.setCreationDate(new Date());
		post.setAutor(currUser);

		if (pics != null) {
			try {
				List<Image> pictures = new ArrayList<Image>();
				for (Part pic : pics) {
					Image img = new Image();
					if(pic != null && pic.getSize() > 0)
					{
						InputStream inputStream = pic.getInputStream();
						img.setImage(IOUtils.toByteArray(inputStream));
						img.setPost(post);
						pictures.add(img);
					}
				}
				post.setImages(pictures);
			} catch (IOException e) {
				errors.add("Error downloading image picture");
				model.addAttribute("errors", errors);
				return "createPost";
			}
		}
		
		Post newPost = postService.save(post);
		
		return "redirect:/post/" + newPost.getId();
	}

}
