package com.epam.jmp.dr.task17.soap.ws;

import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import com.epam.jmp.dr.task17.entities.Task;
import com.epam.jmp.dr.task17.entities.User;
import com.epam.jmp.dr.task17.storage.StorageHelper;

@WebService(endpointInterface = "com.epam.jmp.dr.task17.soap.ws.SOAPWebService")
public class SOAPWebServiceImpl implements SOAPWebService {

	@Override
	public boolean updateUser(User user) {
		
		return StorageHelper.getInstance().updateUser(user);
	}

	@Override
	public boolean updateTask(int userId, Task task) {
		
		return StorageHelper.getInstance().updateTask(userId, task);
	}

	@Override
	public boolean deleteUser(int id) {
		
		return StorageHelper.getInstance().deleteUser(id);
	}

	@Override
	public boolean deleteTask(int userId, int taskId) {
		
		return StorageHelper.getInstance().deleteTask(userId, taskId);
	}

	@Override
	public void addUser(String name, String surname, String email) {
		
		StorageHelper.getInstance().addUser(name, surname, email);
	}

	@Override
	public boolean addTask(int userId, String taskName, String taskDescription, Date deadLine) {
		
		return StorageHelper.getInstance().addTask(userId, taskName, taskDescription, deadLine);
	}

	@Override
	public List<User> getAllUsers() {
		
		return StorageHelper.getInstance().getAllUsers();
	}

	@Override
	public User getUser(int id) {
		
		return StorageHelper.getInstance().getUser(id);
	}

	@Override
	public List<Task> getTasks(int userId) {
		
		return StorageHelper.getInstance().getTasks(userId);
	}

	@Override
	public Task getTask(int taskId, int userId) {
		
		return StorageHelper.getInstance().getTask(taskId, userId);
	}
	

}
