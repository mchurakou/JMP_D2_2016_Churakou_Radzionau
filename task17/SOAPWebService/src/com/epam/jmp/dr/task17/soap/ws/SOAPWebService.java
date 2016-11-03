package com.epam.jmp.dr.task17.soap.ws;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.epam.jmp.dr.task17.entities.Task;
import com.epam.jmp.dr.task17.entities.User;

@WebService
public interface SOAPWebService {
	
	@WebMethod
	public boolean updateUser(User user);
	
	@WebMethod
	public boolean updateTask(int userId, Task task);
	
	@WebMethod
	public boolean deleteUser(int id);
	
	@WebMethod
	public boolean deleteTask(int userId, int taskId);
	
	@WebMethod
	public void addUser(String name, String surname, String email);
	
	@WebMethod
	public boolean addTask(int userId, String taskName, String taskDescription, Date deadLine);
	
	@WebMethod
	public List<User> getAllUsers();
	
	@WebMethod
	public User getUser(int id);
	
	@WebMethod
	public List<Task> getTasks(int userId);
	
	@WebMethod
	public Task getTask(int taskId, int userId);
	
	
}
