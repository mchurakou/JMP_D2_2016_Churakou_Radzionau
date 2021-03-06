package com.epam.jmp.dr.task17.rest.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.epam.jmp.dr.task17.rest.entities.SuperUser;
import com.epam.jmp.dr.task17.rest.entities.Task;
import com.epam.jmp.dr.task17.rest.entities.User;
import com.epam.jmp.dr.task17.rest.storage.JPAService;
import com.epam.jmp.dr.task17.rest.storage.StorageHelper;

@RestController
public class Controller {
	
	private JPAService service = new JPAService();
	
	@RequestMapping(value = "/user", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<User> getAllUsers() {

		return service.getAllUsers();
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public User getUser(@PathVariable int id) {

		return service.getUser(id);
	}

	@RequestMapping(value = "/user", method = RequestMethod.PUT, headers = "Accept=application/json")
	public boolean updateUser(@RequestBody User user) {

		service.updateUser(user);
		return true;
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST, headers = "Accept=application/json")
	public void addUser(@RequestBody User user) {

		service.addUser(user);
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public boolean deleteUser(@PathVariable int id) {

		service.deleteUser(id);
		return true;
	}
	
	//@RequestMapping(value = "/task/user/{userId}", method = RequestMethod.GET, headers = "Accept=application/json")
	@RequestMapping(value = "/user/{userId}/task", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Task> getTasks(@PathVariable int userId) {

		return service.getAllTasks(userId);
	}

	@RequestMapping(value = "/user/{userId}/task/{taskId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Task getTask(@PathVariable int taskId, @PathVariable int userId) {

		return service.getTask(taskId);
	}
	
	//@RequestMapping(value = "/tasks/user/{userId}", method = RequestMethod.POST, headers = "Accept=application/json")
	@RequestMapping(value = "/user/{userId}/task", method = RequestMethod.POST, headers = "Accept=application/json")
	public boolean addTask(@PathVariable int userId, @RequestBody Task task) {
		task.setCreationDate(new Date());
		service.addTask(userId, task);
		return true;
	}
	
	//@RequestMapping(value = "/tasks/user/{userId}", method = RequestMethod.PUT, headers = "Accept=application/json")
	@RequestMapping(value = "/user/{userId}/task", method = RequestMethod.PUT, headers = "Accept=application/json")
	public boolean updateTask(@PathVariable int userId, @RequestBody Task task) {

		service.updateTask(task);
		return true;
	}

	
	//@RequestMapping(value = "/task/{taskId}/user/{userId}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	@RequestMapping(value = "/user/{userId}/task/{taskId}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public boolean deleteTask(@PathVariable int userId, @PathVariable int taskId) {

		service.deleteTask(userId, taskId);
		return true;
	}

	
	@RequestMapping(value = "/superuser", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<SuperUser> getAllSuperUsers() {

		return service.getAllSUsers();
	}

	@RequestMapping(value = "/superuser/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public SuperUser getSuperUser(@PathVariable int id) {

		return service.getSUser(id);
	}

	@RequestMapping(value = "/superuser", method = RequestMethod.PUT, headers = "Accept=application/json")
	public boolean updateSuperUser(@RequestBody SuperUser user) {

		service.updateSUser(user);
		return true;
	}

	@RequestMapping(value = "/superuser", method = RequestMethod.POST, headers = "Accept=application/json")
	public void addSuperUser(@RequestBody SuperUser user) {

		service.addSUser(user);
	}
	
	@RequestMapping(value = "/superuser/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public boolean deleteSuperUser(@PathVariable int id) {

		service.deleteSUser(id);
		return true;
	}
	
	

	public JPAService getService() {
		return service;
	}

	public void setService(JPAService service) {
		this.service = service;
	}

}
