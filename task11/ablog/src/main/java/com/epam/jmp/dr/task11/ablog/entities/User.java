package com.epam.jmp.dr.task11.ablog.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "USER", uniqueConstraints = {@UniqueConstraint(columnNames = "login")})
public class User {
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	private int id;
	
	@Column(name = "login", unique = true, nullable = false)
	private String login;
	
	@Basic(optional = false)
	private String password;
	
	@Basic(optional = false)
	private String email;
	
	@Basic(optional = false)
	private String name;
	
	@Basic(optional = false)
	@Enumerated(EnumType.STRING)
	@Column(name="user_type")
	private UserType userType;
	
	@Lob
	@Basic(fetch=FetchType.LAZY)
	private byte[] avatar;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy= "autor")
	private List<Post> posts = new ArrayList<Post>();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy= "autor")
	private List<Comment> comments = new ArrayList<Comment>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

}
