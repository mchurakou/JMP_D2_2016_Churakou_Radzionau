package com.epam.jmp.dr.task15.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("tasks")
public class Task {
	@Id
	private ObjectId id;

	private Date creationDate;

	private Date deadLine;

	private String name;

	private String category;

	public Task(String name, String category, Date deadLine) {
		creationDate = new Date();
		this.deadLine = deadLine;
		this.name = name;
		this.category = category;
	}

	public Task() {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String toString() {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String cDate = dt.format(creationDate);
		String eDate = dt.format(deadLine);
		return "ID: " + this.id + "; Name: " + this.name + "; Created: " + cDate + "; Deadline: " + eDate
				+ "; Category: " + this.category;
	}

}
