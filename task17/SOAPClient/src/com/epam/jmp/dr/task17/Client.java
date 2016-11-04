package com.epam.jmp.dr.task17;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.epam.jmp.dr.task17.soap.ws.SOAPWebService;
import com.epam.jmp.dr.task17.soap.ws.SOAPWebServiceImplService;
import com.epam.jmp.dr.task17.soap.ws.Task;
import com.epam.jmp.dr.task17.soap.ws.User;

public class Client {

	public static void main(String[] args) throws DatatypeConfigurationException, ParseException {

		SOAPWebService service = new SOAPWebServiceImplService().getSOAPWebServiceImplPort();

		System.out.println("All users:");
		System.out.println(usersToString(service.getAllUsers()));

		System.out.println("\n");

		System.out.println("Tasks for user with id=1:");
		System.out.println(tasksToString(service.getTasks(1)));

		System.out.println("\n");

		System.out.println("Adding a new user:");
		service.addUser("new", "user", "new@user.com");
		System.out.println(usersToString(service.getAllUsers()));

		System.out.println("\n");

		System.out.println("Adding task for user with id=1");
		service.addTask(1, "new task", "task description", getXMLGregorianCalendarFromString("2019-05-14"));
		System.out.println(tasksToString(service.getTasks(1)));

		System.out.println("\n");

		System.out.println("Updating user with id = 1");
		User u = service.getUser(1);
		u.setName("updated Name");
		service.updateUser(u);
		System.out.println(usersToString(service.getAllUsers()));

		System.out.println("\n");

		System.out.println("Updating task id = 1 for user with id = 1:");
		Task t = service.getTask(1, 1);
		t.setDescription("updated description");
		service.updateTask(1, t);
		System.out.println(tasksToString(service.getTasks(1)));

		System.out.println("\n");

		System.out.println("deleting task with id = 1 for user with id = 1");
		service.deleteTask(1, 1);
		System.out.println(tasksToString(service.getTasks(1)));

		System.out.println("\n");

		System.out.println("deleting user with id = 1");
		service.deleteUser(1);
		System.out.println(usersToString(service.getAllUsers()));

	}

	private static String toString(User u) {
		return "User [id=" + u.getId() + ", name=" + u.getName() + ", surname=" + u.getSurname() + ", mail="
				+ u.getMail() + "]";
	}

	private static String toString(Task t) {
		return "Task [id=" + t.getId() + ", name=" + t.getName() + ", description=" + t.getDescription()
				+ ", creationDate="
				+ new SimpleDateFormat("yyyy-MM-dd").format(t.getCreationDate().toGregorianCalendar().getTime())
				+ ", deadLine="
				+ new SimpleDateFormat("yyyy-MM-dd").format(t.getDeadLine().toGregorianCalendar().getTime()) + "]";
	}

	private static String usersToString(List<User> users) {
		String result = "";

		for (User u : users) {
			result += toString(u) + "\n";
		}
		return result;
	}

	private static String tasksToString(List<Task> tasks) {
		String result = "";

		for (Task t : tasks) {
			result += toString(t) + "\n";
		}
		return result;
	}

	private static XMLGregorianCalendar getXMLGregorianCalendarFromString(String date)
			throws DatatypeConfigurationException, ParseException {
		Date dob = null;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		dob = df.parse(date);
		GregorianCalendar cal = new GregorianCalendar();

		cal.setTime(dob);

		XMLGregorianCalendar xmlDate2 = DatatypeFactory.newInstance()
				.newXMLGregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
						cal.get(Calendar.DAY_OF_MONTH), dob.getHours(), dob.getMinutes(), dob.getSeconds(),
						DatatypeConstants.FIELD_UNDEFINED, cal.getTimeZone().LONG)
				.normalize();

		return xmlDate2;
	}

}
