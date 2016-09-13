package com.epam.jmp.dr.task10;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class DBHandler {

	private static String USER = "root";
	private static String PASSWORD = "root";
	private static String URL = "jdbc:mysql://localhost:3306/task10";
	
	private static String DROP_TABLES = "DROP TABLE IF EXISTS users, likes, friendships, posts;";
	
	private static String CREATE_USERS_TABLE = "CREATE TABLE users ("
			+ "id INT NOT NULL AUTO_INCREMENT, "
			+ "name VARCHAR(50) NOT NULL, "
			+ "surname VARCHAR(50) NOT NULL, "
			+ "birthdate DATE NOT NULL, "
			+ "PRIMARY KEY (id), "
			+ "UNIQUE INDEX id_UNIQUE (id ASC)"
			+ ");";
	
	private static String CREATE_FRIENDSHIPS_TABLE = "CREATE TABLE friendships ("
			+ "userid1 INT NOT NULL, "
			+ "userid2 INT NOT NULL, "
			+ "timestamp TIMESTAMP NOT NULL, "
			+ "PRIMARY KEY (userid1, userid2)"
			+ ");";
	
	private static String CREATE_POSTS_TABLE = "CREATE TABLE posts ("
			+ "id INT NOT NULL AUTO_INCREMENT, "
			+ "userId INT NOT NULL, "
			+ "text VARCHAR(500) NOT NULL, "
			+ "timestamp TIMESTAMP NOT NULL, "
			+ "PRIMARY KEY (id), "
			+ "UNIQUE INDEX id_UNIQUE (id ASC)"
			+ ");";
	
	private static String CREATE_LIKES_TABLE = "CREATE TABLE likes ("
			+ "postid INT NOT NULL AUTO_INCREMENT, "
			+ "userid INT NOT NULL, "
			+ "timestamp TIMESTAMP NOT NULL, "
			+ "PRIMARY KEY (postid), "
			+ "UNIQUE INDEX postid_UNIQUE (postid ASC)"
			+ ");";
	
	private static String INSERT_USER = "INSERT INTO users (name, surname, birthdate) VALUES(?, ?, ?);";
	private static String INSERT_FRIENDSHIP = "INSERT INTO friendships values(?, ?, ?);";
	private static String GET_USERS_COUNT = "SELECT COUNT(*) as users_count FROM users;";
	
	public static void generateFriendships()
	{
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement insertFriendshipStatement = conn.prepareStatement(INSERT_FRIENDSHIP);
				Statement countUsersStatement = conn.createStatement()) {
			
			ResultSet usersCountSet = countUsersStatement.executeQuery(GET_USERS_COUNT);
			usersCountSet.first();
			int usersCount = usersCountSet.getInt("users_count");
			
			
			
			
			countUsersStatement.close();
			insertFriendshipStatement.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void generateUsers()
	{
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement statement = conn.prepareStatement(INSERT_USER);) {
			
			try {
				FileReader fileReader = new FileReader("user_names.csv");
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				String line = null;
				
				while ((line = bufferedReader.readLine()) != null) {
					String tmp[] = line.split(",");
					
					String name = tmp[0];
					String surname = tmp[1];
					
					statement.setString(1, name);
					statement.setString(2, surname);
					statement.setDate(3, getRandomDate(1950, 2000));
					
					statement.executeUpdate();
					
				}
				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			statement.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void createTables() {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement statement = conn.createStatement();) {
			
			statement.executeUpdate(DROP_TABLES);
			
			statement.executeUpdate(CREATE_USERS_TABLE);
			statement.executeUpdate(CREATE_FRIENDSHIPS_TABLE);
			statement.executeUpdate(CREATE_POSTS_TABLE);
			statement.executeUpdate(CREATE_LIKES_TABLE);
			
			statement.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static java.sql.Date getRandomDate(int start, int end)
	{
		GregorianCalendar gc = new GregorianCalendar();
        int year = randBetween(start, end);
        gc.set(GregorianCalendar.YEAR, year);
        int dayOfYear = randBetween(1, gc.getActualMaximum(GregorianCalendar.DAY_OF_YEAR));
        gc.set(GregorianCalendar.DAY_OF_YEAR, dayOfYear);
        /*int hour = randBetween(0, 23);
        gc.set(hour, GregorianCalendar.HOUR_OF_DAY);
        int minute = randBetween(0, 59);
        gc.set(minute, GregorianCalendar.MINUTE);
        int second = randBetween(0, 59);
        gc.set(second, GregorianCalendar.SECOND);*/
        
        
		java.sql.Date date = new java.sql.Date(gc.getTimeInMillis());
        return date;
	}
	
	private static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
}
