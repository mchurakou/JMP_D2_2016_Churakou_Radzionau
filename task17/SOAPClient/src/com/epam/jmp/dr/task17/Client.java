package com.epam.jmp.dr.task17;

import java.nio.file.attribute.UserPrincipalLookupService;
import java.text.SimpleDateFormat;
import java.util.List;

import com.epam.jmp.dr.task17.soap.ws.SOAPWebService;
import com.epam.jmp.dr.task17.soap.ws.SOAPWebServiceImplService;
import com.epam.jmp.dr.task17.soap.ws.Task;
import com.epam.jmp.dr.task17.soap.ws.User;

public class Client {

	
	public static void main(String[] args) {
		
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
		
		
		
		
	}
	
	private static String toString(User u)
	{
		return "User [id=" + u.getId() + ", name=" + u.getName() + ", surname=" + u.getSurname() + ", mail=" + u.getMail() + "]";
	}
	
	private static String toString(Task t)
	{
		return "Task [id=" + t.getId() + ", name=" + t.getName() + ", description=" + t.getDescription() + ", creationDate=" + new SimpleDateFormat("yyyy-MM-dd").format(t.getCreationDate().toGregorianCalendar().getTime())
				+ ", deadLine=" + new SimpleDateFormat("yyyy-MM-dd").format(t.getDeadLine().toGregorianCalendar().getTime()) + "]";
	}
	
	private static String usersToString(List<User> users)
	{
		String result = "";
		
		for(User u: users)
		{
			result += toString(u) + "\n";
		}
		return result;
	}
	
	private static String tasksToString(List<Task> tasks)
	{
		String result = "";
		
		for(Task t : tasks)
		{
			result += toString(t) + "\n";
		}
		return result;
	}

}
