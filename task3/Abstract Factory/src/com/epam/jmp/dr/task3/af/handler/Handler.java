package com.epam.jmp.dr.task3.af.handler;

import com.epam.jmp.dr.task3.af.person.Person;

public interface Handler {

	/**
	 * writes person to the storage
	 * 
	 * @param person
	 * @throws Exception
	 */
	void writePerson(Person person) throws Exception;

	/**
	 * Reads person with specific name from the storage
	 * 
	 * @param name
	 * @return Person
	 * @throws Exception
	 */
	Person readPerson(String name) throws Exception;

	/**
	 * Reads all Persons from the sorage
	 * 
	 * @return array of Persons
	 * @throws Exception
	 */
	Person[] readPersons() throws Exception;

	/**
	 * Closes handler
	 * 
	 * @throws Exception
	 */
	void close() throws Exception;

}
