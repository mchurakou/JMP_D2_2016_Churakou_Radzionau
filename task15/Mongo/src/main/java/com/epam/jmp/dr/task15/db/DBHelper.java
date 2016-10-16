package com.epam.jmp.dr.task15.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import com.epam.jmp.dr.task15.task.Task;
import com.mongodb.MongoClient;

public class DBHelper {

	private static final String DB_NAME = "local";

	/**
	 * Clears datastore to avoid data duplication on the multiple runs
	 */
	public void clearDatastore() {
		final Morphia morphia = new Morphia();

		morphia.mapPackage("com.epam.jmp.dr.task15.task");

		final Datastore datastore = morphia.createDatastore(new MongoClient(), DB_NAME);
		datastore.ensureIndexes();

		final Query<Task> query = datastore.createQuery(Task.class);
		datastore.delete(query);
	}

	/**
	 * returns all tasks
	 * @return
	 */
	public List<Task> getAllTasks() {
		final Morphia morphia = new Morphia();

		morphia.mapPackage("com.epam.jmp.dr.task15.task");

		final Datastore datastore = morphia.createDatastore(new MongoClient(), DB_NAME);
		datastore.ensureIndexes();

		final Query<Task> query = datastore.createQuery(Task.class);
		List<Task> tasks = query.asList();
		return tasks;
	}

	/**
	 * adds tasks
	 * @param tasks
	 */
	public void addTasks(List<Task> tasks) {
		final Morphia morphia = new Morphia();

		morphia.mapPackage("com.epam.jmp.dr.task15.task");

		final Datastore datastore = morphia.createDatastore(new MongoClient(), DB_NAME);
		datastore.ensureIndexes();

		datastore.save(tasks);
	}

	/**
	 * returns task with specified category
	 * @param cat
	 * @return
	 */
	public List<Task> findTasksByCategory(String cat) {
		final Morphia morphia = new Morphia();

		morphia.mapPackage("com.epam.jmp.dr.task15.task");

		final Datastore datastore = morphia.createDatastore(new MongoClient(), DB_NAME);
		datastore.ensureIndexes();
		List<Task> tasks = datastore.createQuery(Task.class).field("category").equalIgnoreCase(cat).asList();
		return tasks;

	}

	/**
	 * deletes provided tasks
	 * @param tasks
	 */
	public void deleteTasks(List<Task> tasks) {
		final Morphia morphia = new Morphia();

		morphia.mapPackage("com.epam.jmp.dr.task15.task");

		final Datastore datastore = morphia.createDatastore(new MongoClient(), DB_NAME);
		datastore.ensureIndexes();

		List<ObjectId> ids = new ArrayList<ObjectId>();
		for (Task t : tasks) {
			ids.add(t.getId());
		}

		datastore.delete(Task.class, ids);
	}

	/**
	 * sets deadline for the specified tasks
	 * @param tasks
	 * @param newDeadLine
	 */
	public void setDeadLine(List<Task> tasks, Date newDeadLine) {
		final Morphia morphia = new Morphia();

		morphia.mapPackage("com.epam.jmp.dr.task15.task");

		final Datastore datastore = morphia.createDatastore(new MongoClient(), DB_NAME);
		datastore.ensureIndexes();

		List<ObjectId> ids = new ArrayList<ObjectId>();
		for (Task t : tasks) {
			ids.add(t.getId());
		}

		Query<Task> tq = datastore.createQuery(Task.class).field("id").in(ids);

		UpdateOperations<Task> updateOperations = datastore.createUpdateOperations(Task.class).set("deadLine",
				newDeadLine);

		datastore.update(tq, updateOperations);
	}

	/**
	 * returns tasks with overdue
	 * @return
	 */
	public List<Task> getTasksWithOverdue() {
		final Morphia morphia = new Morphia();

		morphia.mapPackage("com.epam.jmp.dr.task15.task");

		final Datastore datastore = morphia.createDatastore(new MongoClient(), DB_NAME);
		datastore.ensureIndexes();
		List<Task> tasks = datastore.createQuery(Task.class).field("deadLine").lessThan(new Date()).asList();
		return tasks;
	}

}
