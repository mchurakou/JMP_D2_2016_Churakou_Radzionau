package com.epam.jmp.dr.task3.af.handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.epam.jmp.dr.task3.af.person.Person;

public class FileHandler implements Handler {

	/**
	 * filename
	 */
	private String fileName;

	public FileHandler(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public void writePerson(Person person) throws IOException {

		FileWriter fw = new FileWriter(fileName, true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(toWritableSting(person));
		bw.close();

	}

	@Override
	public Person readPerson(String name) throws IOException {
		Person persons[] = readPersons();
		Person result = null;
		for (Person person : persons) {
			if (person.getName().equals(name)) {
				result = person;
				break;
			}
		}
		return result;
	}

	@Override
	public Person[] readPersons() throws IOException {
		List<Person> personsList = new ArrayList<Person>();

		FileReader fileReader = new FileReader(fileName);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			Person p = fromWritableString(line);
			if (p != null) {
				personsList.add(p);
			}
		}
		bufferedReader.close();

		Person result[] = new Person[personsList.size()];
		return personsList.toArray(result);
	}

	/**
	 * Converts Person to string, that could be saved in file
	 * 
	 * @param person
	 * @return
	 */
	private static String toWritableSting(Person person) {
		String result = person.getName() + ";" + person.getGender() + ";" + person.getAge() + "\n";
		return result;
	}

	/**
	 * Converts record from file to Person
	 * 
	 * @param string
	 * @return
	 */
	private static Person fromWritableString(String string) {
		Person result = null;
		String personParts[] = string.split(";");
		if (personParts.length == 3) {
			String name = personParts[0];
			String gender = personParts[1];
			int age = Integer.valueOf(personParts[2]);
			result = new Person(name, gender, age);
		}
		return result;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

}
