package com.epam.jmp.dr.task17.rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.epam.jmp.dr.task17.rest.entities.Task;
import com.epam.jmp.dr.task17.rest.entities.User;
import com.epam.jmp.dr.task17.rest.storage.StorageHelper;

@RestController
public class Controller {

	@RequestMapping(value = "/users", method = RequestMethod.PUT, headers = "Accept=application/json")
	public boolean updateUser(@RequestBody User user) {

		return StorageHelper.getInstance().updateUser(user);
	}

	@RequestMapping(value = "/tasks/user/{userId}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public boolean updateTask(@PathVariable int userId, @RequestBody Task task) {

		return StorageHelper.getInstance().updateTask(userId, task);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public boolean deleteUser(@PathVariable int id) {

		return StorageHelper.getInstance().deleteUser(id);
	}

	@RequestMapping(value = "/task/{taskId}/user/{userId}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public boolean deleteTask(@PathVariable int userId, @PathVariable int taskId) {

		return StorageHelper.getInstance().deleteTask(userId, taskId);
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST, headers = "Accept=application/json")
	public void addUser(@RequestBody User user) {

		StorageHelper.getInstance().addUser(user.getName(), user.getSurname(), user.getMail());
	}

	@RequestMapping(value = "/tasks/user/{userId}", method = RequestMethod.POST, headers = "Accept=application/json")
	public boolean addTask(@PathVariable int userId, @RequestBody Task task) {

		return StorageHelper.getInstance().addTask(userId, task.getName(), task.getDescription(), task.getDeadLine());
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<User> getAllUsers() {

		return StorageHelper.getInstance().getAllUsers();
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public User getUser(@PathVariable int id) {

		return StorageHelper.getInstance().getUser(id);
	}

	@RequestMapping(value = "/task/user/{userId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Task> getTasks(@PathVariable int userId) {

		return StorageHelper.getInstance().getTasks(userId);
	}

	@RequestMapping(value = "/task/{taskId}/user/{userId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Task getTask(@PathVariable int taskId, @PathVariable int userId) {

		return StorageHelper.getInstance().getTask(taskId, userId);
	}

}
