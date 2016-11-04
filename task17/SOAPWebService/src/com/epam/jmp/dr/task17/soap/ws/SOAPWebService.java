package com.epam.jmp.dr.task17.soap.ws;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.epam.jmp.dr.task17.entities.Task;
import com.epam.jmp.dr.task17.entities.User;

@WebService
public interface SOAPWebService {

	/**
	 * 
	 * @param user
	 * @return
	 */
	@WebMethod
	public boolean updateUser(User user);

	/**
	 * 
	 * @param userId
	 * @param task
	 * @return
	 */
	@WebMethod
	public boolean updateTask(int userId, Task task);

	/**
	 * 
	 * @param id
	 * @return
	 */
	@WebMethod
	public boolean deleteUser(int id);

	/**
	 * 
	 * @param userId
	 * @param taskId
	 * @return
	 */
	@WebMethod
	public boolean deleteTask(int userId, int taskId);

	/**
	 * 
	 * @param name
	 * @param surname
	 * @param email
	 */
	@WebMethod
	public void addUser(String name, String surname, String email);

	/**
	 * 
	 * @param userId
	 * @param taskName
	 * @param taskDescription
	 * @param deadLine
	 * @return
	 */
	@WebMethod
	public boolean addTask(int userId, String taskName, String taskDescription, Date deadLine);

	/**
	 * 
	 * @return
	 */
	@WebMethod
	public List<User> getAllUsers();

	/**
	 * 
	 * @param id
	 * @return
	 */
	@WebMethod
	public User getUser(int id);

	/**
	 * 
	 * @param userId
	 * @return
	 */
	@WebMethod
	public List<Task> getTasks(int userId);

	/**
	 * 
	 * @param taskId
	 * @param userId
	 * @return
	 */
	@WebMethod
	public Task getTask(int taskId, int userId);

}
