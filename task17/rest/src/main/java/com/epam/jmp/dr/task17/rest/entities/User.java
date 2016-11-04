package com.epam.jmp.dr.task17.rest.entities;

import java.util.ArrayList;
import java.util.List;


public class User {
	
	private int nextTaskId = 1;
	
	private int id;
	
	private String name;
	
	private String surname;
	
	private String mail;
	
	private List<Task> tasks = new ArrayList<Task>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public List<Task> getTasks() {
		return tasks;
	}
	
	public int getNextTaskId()
	{
		return nextTaskId++;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", mail=" + mail + "]";

	}

}
