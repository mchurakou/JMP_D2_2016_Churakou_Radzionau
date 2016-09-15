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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


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
	private static String GET_USER_FRIENDSHIPS = "SELECT userid2 AS friend FROM friendships WHERE userid1 = ?;";
	
	
	public static void generateFriendships()
	{
		System.out.println("Creating friendships....");
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement insertFriendshipStatement = conn.prepareStatement(INSERT_FRIENDSHIP);
				Statement countUsersStatement = conn.createStatement();
				PreparedStatement getUserFriendships = conn.prepareStatement(GET_USER_FRIENDSHIPS);
				) {
			
			ResultSet usersCountSet = countUsersStatement.executeQuery(GET_USERS_COUNT);
			usersCountSet.first();
			int usersCount = usersCountSet.getInt("users_count");
			countUsersStatement.close();
			
			Map<Integer, Set<Integer>> friendships = new HashMap<>();
			
			for(int userid1 = 1; userid1 <= usersCount; userid1++)
			{
				friendships.put(userid1, new HashSet<Integer>());
			}
			
			System.out.println("Adding friendships pairs...");
			/// Adding friendships pairs
			for(int userid1 = 1; userid1 <= usersCount; userid1++)
			{
				int friendsAmount = randBetween(50, 500);
				for(int i = 0; i < friendsAmount; i++)
				{
					int userid2 = randBetween(1, usersCount);
					while(userid1 == userid2)
					{
						userid2 = randBetween(1, usersCount);
					}
					friendships.get(userid1).add(userid2);
					friendships.get(userid2).add(userid1);
				}
			}
			
			for (Map.Entry<Integer, Set<Integer>> entry : friendships.entrySet()) {
				int user1 = entry.getKey();
				Set<Integer> friends = entry.getValue();
				for(Integer user2 : friends)
				{
					int u1 = user1;
					int u2 = user2;
					Timestamp timestamp = getRandomDate(2010, 2016);
					
					insertFriendshipStatement.setInt(1, u1);
					insertFriendshipStatement.setInt(2, u2);
					insertFriendshipStatement.setTimestamp(3, timestamp);
					
					insertFriendshipStatement.addBatch();
				}
			}
			System.out.println("Adding friendships pairs...  Done");
			
			System.out.println("Trying to execute batch...");
			insertFriendshipStatement.executeBatch();
			System.out.println("Trying to execute batch...   Done");
			
			
			getUserFriendships.close();
			insertFriendshipStatement.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Creating friendships finished");
	}
	
	public static void generateUsers()
	{
		System.out.println("Creating users....");
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
					statement.setDate(3, new Date(getRandomDate(1950, 2000).getTime()));
					
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
		System.out.println("Creating users finished");
	}

	public static void createTables() {
		System.out.println("Creating tables...");
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
		System.out.println("Creating tables finished");
	}
	
	private static java.sql.Timestamp getRandomDate(int start, int end)
	{
		GregorianCalendar gc = new GregorianCalendar();
        int year = randBetween(start, end);
        gc.set(GregorianCalendar.YEAR, year);
        int dayOfYear = randBetween(1, gc.getActualMaximum(GregorianCalendar.DAY_OF_YEAR));
        gc.set(GregorianCalendar.DAY_OF_YEAR, dayOfYear);
        int hour = randBetween(0, 23);
        //gc.set(hour, GregorianCalendar.HOUR_OF_DAY);
        int minute = randBetween(0, 59);
        //gc.set(minute, GregorianCalendar.MINUTE);
        int second = randBetween(0, 59);
        //gc.set(second, GregorianCalendar.SECOND);
        
        
		//java.sql.Date date = new java.sql.Date(gc.getTimeInMillis());
        Timestamp timestamp = new Timestamp(gc.getTimeInMillis());
        return timestamp;
	}
	
	private static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
}
