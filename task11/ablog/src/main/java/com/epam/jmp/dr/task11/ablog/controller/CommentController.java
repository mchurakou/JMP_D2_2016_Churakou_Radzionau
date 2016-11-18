package com.epam.jmp.dr.task11.ablog.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.epam.jmp.dr.task11.ablog.entities.Comment;
import com.epam.jmp.dr.task11.ablog.entities.Post;
import com.epam.jmp.dr.task11.ablog.entities.User;
import com.epam.jmp.dr.task11.ablog.service.CommentService;
import com.epam.jmp.dr.task11.ablog.service.PostService;
import com.epam.jmp.dr.task11.ablog.service.UserService;

@Controller
public class CommentController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private UserService userService;

	public void setPostService(PostService postService) {
		this.postService = postService;
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/post/{postId}/comment/{commentId}/delete")
	public String deleteComment(@PathVariable Integer postId, @PathVariable Integer commentId, Model model, HttpServletRequest httpServletRequest)
	{
		List<String> errors = new ArrayList<String>();
		User currUser = (User) httpServletRequest.getSession().getAttribute("currUser");
		if(currUser == null)
		{
			errors.add("Please login to delete comments");
			model.addAttribute("errors", errors);
			return "error";
		}
		
		Comment comment = commentService.findComment(commentId);
		if(!comment.getAutor().getLogin().equals(currUser.getLogin()))
		{
			errors.add("You can delete only your comments");
			model.addAttribute("errors", errors);
			return "error";
		}
		
		commentService.delete(comment);
		
		return "redirect:/post/" + postId;
	}
	
	@RequestMapping(method = RequestMethod.POST, path="/post/{postId}/comment")
	public String createComment(@PathVariable Integer postId, Model model, HttpServletRequest httpServletRequest, @RequestParam(value="text", required=true) String text)
	{
		User currUser = (User) httpServletRequest.getSession().getAttribute("currUser");
		List<String> errors = new ArrayList<String>();
		if(currUser == null)
		{
			errors.add("Please login to leave comments");
			model.addAttribute("errors", errors);
			return "error";
		}
		
		Post post = postService.findById(postId);
		if(post == null)
		{
			errors.add("Target post not found");
			model.addAttribute("errors", errors);
			return "error";
		}
		
		if(text != null && !text.equals(""))
		{
			User autor = userService.fingByLogin(currUser.getLogin());
			Comment comment = new Comment();
			comment.setAutor(autor);
			comment.setDate(new Date());
			comment.setPost(post);
			comment.setText(text);
			
			
			Comment newComment = commentService.save(comment);
		}
		
		
		return "redirect:/post/" + postId;
	}

}
