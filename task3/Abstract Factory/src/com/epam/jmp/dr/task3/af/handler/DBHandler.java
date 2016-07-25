package com.epam.jmp.dr.task3.af.handler;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epam.jmp.dr.task3.af.person.Person;

public class DBHandler implements Handler {

	/**
	 * create table sql query
	 */
	private static final String CREATE_TABLE_SQL = "CREATE TABLE abstract_factory_test_table (name VARCHAR(100) NOT NULL, gender VARCHAR(10) NULL, age INT NULL, PRIMARY KEY (name))";

	/**
	 * insert person sql query
	 */
	private static final String INSERT_PERSON_SQL = "INSERT INTO abstract_factory_test_table(name, gender, age) VALUES(?, ?, ?)";

	/**
	 * select single person by name sql query
	 */
	private static final String SELECT_PERSON_SQL = "SELECT * FROM abstract_factory_test_table WHERE BINARY name=? LIMIT 1";

	/**
	 * select all persons sql query
	 */
	private static final String SELECT_PERSONS_SQL = "SELECT * FROM abstract_factory_test_table";

	/**
	 * Database connection
	 */
	private Connection connection;

	public DBHandler(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void writePerson(Person person) throws SQLException {
		PreparedStatement pstmt = connection.prepareStatement(INSERT_PERSON_SQL);
		pstmt.setString(1, person.getName());
		pstmt.setString(2, person.getGender());
		pstmt.setInt(3, person.getAge());
		pstmt.executeUpdate();
	}

	@Override
	public Person readPerson(String name) throws SQLException {
		PreparedStatement pstmt = connection.prepareStatement(SELECT_PERSON_SQL);
		pstmt.setString(1, name);
		ResultSet rs = pstmt.executeQuery();
		Person result = null;
		if (rs.next()) {
			result = new Person(rs.getString(1), rs.getString(2), rs.getInt(3));
		}

		return result;

	}

	@Override
	public Person[] readPersons() throws SQLException {
		Statement stmt = connection.createStatement();
		List<Person> personsList = new ArrayList<Person>();
		ResultSet rs = stmt.executeQuery(SELECT_PERSONS_SQL);
		while (rs.next()) {
			Person p = new Person(rs.getString(1), rs.getString(2), rs.getInt(3));
			personsList.add(p);
		}
		Person result[] = new Person[personsList.size()];
		return personsList.toArray(result);
	}

	/**
	 * checks if abstract_factory_test_table exists in selected database
	 * 
	 * @return
	 * @throws SQLException
	 */
	public boolean checkIfTableExsists() throws SQLException {
		DatabaseMetaData meta = connection.getMetaData();
		ResultSet res = meta.getTables(null, null, "abstract_factory_test_table", new String[] { "TABLE" });
		if (res.next()) {
			return true;
		}
		return false;
	}

	/**
	 * creates abstract_factory_test_table table
	 * 
	 * @throws SQLException
	 */
	public void createTable() throws SQLException {
		Statement stmt = connection.createStatement();
		// pstmt.setString(1, TABLE_NAME);
		stmt.executeUpdate(CREATE_TABLE_SQL);

	}

	@Override
	public void close() throws Exception {
		connection.close();
	}

}
