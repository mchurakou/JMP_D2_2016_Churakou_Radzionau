package com.epam.jmp.dr.task17.rest.storage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.epam.jmp.dr.task17.rest.entities.Task;
import com.epam.jmp.dr.task17.rest.entities.User;

public class StorageHelper {

	private List<User> users;

	private int nextUserId = 1;

	private static StorageHelper instance;

	private StorageHelper() {
		/*users = new ArrayList<User>();

		User u = new User();

		u.setId(getNextUserId());
		u.setName("Elvis");
		u.setSurname("Presley");
		u.setMail("elvis@mail.com");

		Task t = new Task();
		t.setId(u.getNextTaskId());
		t.setName("task 1");
		t.setDescription("task 1 description");
		t.setCreationDate(new Date());
		try {
			t.setDeadLine(new SimpleDateFormat("yyyy-MM-dd").parse("2050-03-12"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		u.getTasks().add(t);

		t = new Task();
		t.setId(u.getNextTaskId());
		t.setName("task 2");
		t.setDescription("task 2 description");
		t.setCreationDate(new Date());
		try {
			t.setDeadLine(new SimpleDateFormat("yyyy-MM-dd").parse("2053-03-12"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		u.getTasks().add(t);

		users.add(u);*/

	}

	public static StorageHelper getInstance() {
		if (instance == null) {
			instance = new StorageHelper();
		}
		return instance;
	}

	public boolean updateUser(User user) {
		for (User u : users) {
			if (u.getId() == user.getId()) {
				u.setMail(user.getMail());
				u.setName(user.getName());
				u.setSurname(user.getSurname());
				return true;
			}
		}
		return false;
	}

	public boolean updateTask(int userId, Task task) {
		for (User u : users) {
			if (u.getId() == userId) {
				for (Task t : u.getTasks()) {
					if (t.getId() == task.getId()) {
						t.setCreationDate(task.getCreationDate());
						t.setDeadLine(task.getDeadLine());
						t.setDescription(task.getDescription());
						t.setName(task.getName());
					}
				}
			}
		}
		return false;
	}

	public boolean deleteUser(int id) {
		Iterator<User> i = users.iterator();
		while (i.hasNext()) {
			User u = i.next();
			if (u.getId() == id) {
				i.remove();
				return true;
			}
		}
		return false;
	}

	public boolean deleteTask(int userId, int taskId) {
		Iterator<User> ui = users.iterator();
		while (ui.hasNext()) {
			User u = ui.next();
			if (u.getId() == userId) {
				Iterator<Task> ti = u.getTasks().iterator();
				while (ti.hasNext()) {
					Task t = ti.next();
					if (t.getId() == taskId) {
						ti.remove();
						return true;
					}
				}
			}
		}
		return false;
	}

	public void addUser(String name, String surname, String email) {
		User u = new User();
		u.setId(getNextUserId());
		u.setName(name);
		u.setSurname(surname);
		u.setMail(email);
		users.add(u);
	}

	public boolean addTask(int userId, String taskName, String taskDescription, Date deadLine) {
		/*for (User u : users) {
			if (u.getId() == userId) {
				Task t = new Task();
				t.setId(u.getNextTaskId());
				t.setCreationDate(new Date());
				t.setDeadLine(deadLine);
				t.setName(taskName);
				t.setDescription(taskDescription);
				u.getTasks().add(t);
				return true;
			}
		}*/
		return false;
	}

	public List<User> getAllUsers() {
		return users;
	}

	public User getUser(int id) {
		for (User u : users) {
			if (u.getId() == id) {
				return u;
			}
		}
		return null;
	}

	public List<Task> getTasks(int userId) {
		for (User u : users) {
			if (u.getId() == userId) {
				return u.getTasks();
			}
		}
		return null;
	}

	public Task getTask(int taskId, int userId) {
		for (User u : users) {
			if (u.getId() == userId) {
				for (Task t : u.getTasks()) {
					if (t.getId() == taskId) {
						return t;
					}
				}
			}
		}
		return null;
	}

	public int getNextUserId() {
		return nextUserId++;
	}

}
