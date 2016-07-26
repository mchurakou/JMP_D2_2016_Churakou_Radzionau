package com.epam.jmp.dr.task3.af;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.epam.jmp.dr.task3.af.factory.AbstractHandlerFactory;
import com.epam.jmp.dr.task3.af.factory.FactoryProducer;
import com.epam.jmp.dr.task3.af.handler.Handler;
import com.epam.jmp.dr.task3.af.handler.HandlerType;
import com.epam.jmp.dr.task3.af.person.Person;

public class Runner {

	public static void main(String[] args) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		AbstractHandlerFactory factory = null;
		HandlerType handlerType = HandlerType.DB;

		System.out.println("Please select type of storage to work with: db/file");
		String userInput = "";
		boolean chooseMade = false;
		do {
			try {
				userInput = reader.readLine();
				switch (userInput) {
				case "db":
					handlerType = HandlerType.DB;
					chooseMade = true;
					break;

				case "file":
					handlerType = HandlerType.FILE;
					chooseMade = true;
					break;

				default:
					System.out.println("Unknown storage type. Please try again:");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} while (!chooseMade);

		factory = FactoryProducer.getFactory(handlerType);
		Handler handler = null;

		try {

			String details = "";
			switch (handlerType) {
			case DB:

				System.out.println("Host: ");
				userInput = reader.readLine();
				details += userInput + ";";

				System.out.println("Database name: ");
				userInput = reader.readLine();
				details += userInput + ";";

				System.out.println("User: ");
				userInput = reader.readLine();
				details += userInput + ";";

				System.out.println("Passw: ");
				userInput = reader.readLine();
				details += userInput;

				handler = factory.createHandler(details);
				break;

			case FILE:

				System.out.println("File name: ");
				userInput = reader.readLine();
				handler = factory.createHandler(userInput);

				break;
			}

		} catch (Exception e) {
			System.err.println("Please verify entered data");
			e.printStackTrace();
			return;
		}

		boolean exit = false;

		do {
			try {
				System.out.println(
						"Please, select action:\nnew  - add new Person\nlist - show all stored Persons\nsearch - search Person by exact name\nexit - exit programm");
				userInput = reader.readLine();

				switch (userInput) {
				case "exit":
					exit = true;
					break;

				case "list":
					Person persons[] = handler.readPersons();
					if (persons.length == 0) {
						System.out.println(
								"Sorry, there are no persons in storage now. You can add some using 'new' command");
					} else {
						System.out.println("Persons list:");
						for (Person person : persons) {
							System.out.println(person);
						}
					}
					break;

				case "search":
					String searchName = "";
					System.out.println("Please enter name:");
					searchName = reader.readLine();
					Person person = handler.readPerson(searchName);
					if (person == null) {
						System.err.println("Sorry, there is no person with such name");
					} else {
						System.out.println(person);
					}
					break;

				case "new":
					System.out.println("Please enter name:");
					String name = reader.readLine();
					System.out.println("Please enter gender (M/F or male/female):");
					String gender = reader.readLine();
					System.out.println("Please enter age:");
					int age = Integer.valueOf(reader.readLine());
					Person newPerson = new Person(name, gender, age);
					handler.writePerson(newPerson);
					Person writedPerson = handler.readPerson(name);
					System.out.println("new Person succesfully created: ");
					System.out.println(writedPerson);
					break;
				}

			} catch (Exception e) {
				System.err.println("Please verify entered data, and try again");
				e.printStackTrace();
			}

		} while (!exit);

		try {
			handler.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
