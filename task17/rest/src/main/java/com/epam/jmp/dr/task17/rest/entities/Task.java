package com.epam.jmp.dr.task17.rest.entities;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Task {
	
	private int id;
	
	private String name;
	
	private String description;
	
	private Date creationDate;
	
	private Date deadLine;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", description=" + description + ", creationDate=" + new SimpleDateFormat("yyyy-MM-dd").format(creationDate)
				+ ", deadLine=" + new SimpleDateFormat("yyyy-MM-dd").format(deadLine) + "]";
	}
	
	

}
