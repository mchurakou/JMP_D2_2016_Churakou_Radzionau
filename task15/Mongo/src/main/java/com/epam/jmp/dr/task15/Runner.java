package com.epam.jmp.dr.task15;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.epam.jmp.dr.task15.db.DBHelper;
import com.epam.jmp.dr.task15.task.Task;

/**
 * Hello world!
 *
 */
public class Runner {
	public static void main(String[] args) throws ParseException {
		Logger mongoLogger = Logger.getLogger("org.mongodb");
		mongoLogger.setLevel(Level.WARNING);

		DBHelper helper = new DBHelper();

		// Clearing datastore
		helper.clearDatastore();

		// Adding tasks to datastore
		System.out.println("Adding new tasks:");
		List<Task> newTasks = new ArrayList<Task>();

		newTasks.add(new Task("Get some food", "HEALTH", new SimpleDateFormat("yyyy-MM-dd").parse("2017-05-14")));
		newTasks.add(new Task("Go to walk", "HEALTH", new SimpleDateFormat("yyyy-MM-dd").parse("2017-05-15")));
		newTasks.add(new Task("Get some sleep", "HEALTH", new SimpleDateFormat("yyyy-MM-dd").parse("2017-05-17")));
		newTasks.add(new Task("Write some code", "WORK", new SimpleDateFormat("yyyy-MM-dd").parse("2017-05-14")));
		newTasks.add(new Task("Play game", "MENTAL_HEALTH", new SimpleDateFormat("yyyy-MM-dd").parse("2017-05-12")));
		newTasks.add(new Task("Read book", "MENTAL_HEALTH", new SimpleDateFormat("yyyy-MM-dd").parse("2017-06-14")));
		helper.addTasks(newTasks);

		printTasks(helper.getAllTasks());

		System.out.println("");

		// Tasks search
		System.out.println("Searching tasks with category HEALTH:");
		printTasks(helper.findTasksByCategory("HEALTH"));

		System.out.println("Searching tasks with category MENTAL_HEALTH:");
		printTasks(helper.findTasksByCategory("MENTAL_HEALTH"));

		System.out.println("Searching tasks with category WORK:");
		printTasks(helper.findTasksByCategory("WORK"));

		System.out.println("");

		// Tasks deletion
		System.out.println("Deleting tasks with category MENTAL_HEALTH:");
		helper.deleteTasks(helper.findTasksByCategory("MENTAL_HEALTH"));
		printTasks(helper.getAllTasks());

		System.out.println("");

		// Tasks update
		System.out.println("Setting deadline in past (to 2015-06-06) for tasks with category WORK:");
		helper.setDeadLine(helper.findTasksByCategory("WORK"), new SimpleDateFormat("yyyy-MM-dd").parse("2015-06-06"));
		printTasks(helper.getAllTasks());

		System.out.println("");

		// Search tasks with overdue
		System.out.println("Searching tasks with overdue:");
		printTasks(helper.getTasksWithOverdue());

	}

	private static void printTasks(List<Task> tasks) {
		for (Task t : tasks) {
			System.out.println(t);
		}
	}

}
