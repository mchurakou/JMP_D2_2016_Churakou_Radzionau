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
			+ "postid INT NOT NULL, "
			+ "userid INT NOT NULL, "
			+ "timestamp TIMESTAMP NOT NULL, "
			+ "PRIMARY KEY (postid, userid)"
			+ ");";
	
	private static String INSERT_USER = "INSERT INTO users (name, surname, birthdate) VALUES(?, ?, ?);";
	private static String INSERT_FRIENDSHIP = "INSERT INTO friendships values(?, ?, ?);";
	private static String GET_USERS_COUNT = "SELECT COUNT(*) as users_count FROM users;";
	private static String GET_USER_FRIENDSHIPS = "SELECT userid2 AS friend FROM friendships WHERE userid1 = ?;";
	private static String INSERT_POST = "INSERT INTO posts(userId, text, timestamp) VALUES(?, ?, ?);";
	private static String GET_POSTS_COUNT = "SELECT COUNT(*) as posts_count from posts;";
	private static String GET_POST_TIMESTAMP = "SELECT timestamp FROM posts WHERE id = ?";
	private static String INSERT_LIKE = "INSERT INTO likes VALUES(?, ?, ?);";
	
	
	public static void generateLikes()
	{
		System.out.println("Creating likes....");
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement count = conn.createStatement();
				PreparedStatement postTimestampStmt = conn.prepareStatement(GET_POST_TIMESTAMP);
				PreparedStatement insertLike = conn.prepareStatement(INSERT_LIKE);
				)
		{
			ResultSet usersCountSet = count.executeQuery(GET_USERS_COUNT);
			usersCountSet.first();
			int usersCount = usersCountSet.getInt("users_count");
			
			ResultSet postsCountSet = count.executeQuery(GET_POSTS_COUNT);
			postsCountSet.first();
			int postsCount = postsCountSet.getInt("posts_count");
			count.close();
			
			System.out.println("Generating likes map....");
			/* key - postID, value - Set<userId> */
			Map<Integer, Set<Integer>> postLikes = new HashMap<>();
			
			for(int pId = 1; pId <= postsCount; pId++)
			{
				postLikes.put(pId, new HashSet<Integer>());
			}
			
			for(int i = 0; i < 800000; i++)
			{
				int userid = randBetween(1, usersCount);
				int postId = randBetween(1, postsCount);
				
				postLikes.get(postId).add(userid);
			}
			System.out.println("Generating likes map.... Done");
			
			System.out.println("Creating likes batch....");
			for (Map.Entry<Integer, Set<Integer>> entry : postLikes.entrySet()) {
				int postId = entry.getKey();
				Set<Integer> likesUsersIds = entry.getValue();
				
				postTimestampStmt.setInt(1, postId);
				ResultSet postTimestampSet = postTimestampStmt.executeQuery();
				postTimestampSet.first();
				Timestamp postCreationTimestamp = postTimestampSet.getTimestamp("timestamp");
				
				for(Integer userId : likesUsersIds)
				{
					
					Timestamp likeTimestamp = getRandomDate(postCreationTimestamp, new Timestamp(new java.util.Date().getTime()));
					
					insertLike.setInt(1, postId);
					insertLike.setInt(2, userId);
					insertLike.setTimestamp(3, likeTimestamp);
					
					insertLike.addBatch();
					
				}
			}
			System.out.println("Creating likes batch.... Done");
			
			System.out.println("Trying to execute batch...");
			insertLike.executeBatch();
			System.out.println("Trying to execute batch...   Done");
			
			postTimestampStmt.close();
			insertLike.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Creating likes.... Done");
	}
	
	public static void generatePosts()
	{
		System.out.println("Creating posts....");
		
		try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement countUsersStatement = conn.createStatement();
				PreparedStatement insertPost = conn.prepareStatement(INSERT_POST);
				){
			ResultSet usersCountSet = countUsersStatement.executeQuery(GET_USERS_COUNT);
			usersCountSet.first();
			int usersCount = usersCountSet.getInt("users_count");
			countUsersStatement.close();
			
			System.out.println("Creating batch....");
			for(int i = 0; i < 150000; i++)
			{
				int userId = randBetween(1, usersCount);
				Timestamp timestamp = getRandomDate(2010, 2015);
				String text = "This post was written by user with id: " + userId + " on " + timestamp.toString();
				
				insertPost.setInt(1, userId);
				insertPost.setString(2, text);
				insertPost.setTimestamp(3, timestamp);
				
				insertPost.addBatch();
			}
			System.out.println("Creating batch.... Done");
			
			System.out.println("Trying to execute batch...");
			insertPost.executeBatch();
			System.out.println("Trying to execute batch...   Done");

			insertPost.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("Creating posts.... Done");
	}
	
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
				//int friendsAmount = randBetween(0, usersCount);
				int friendsAmount = randBetween(1, 150);
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
	
	private static java.sql.Timestamp getRandomDate(int startYear, int endYear)
	{		
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(GregorianCalendar.YEAR, startYear);
		gc.set(GregorianCalendar.DAY_OF_YEAR, 1);
		long start = gc.getTimeInMillis();
		gc.set(GregorianCalendar.YEAR, endYear);
		gc.set(GregorianCalendar.DAY_OF_YEAR, 1);
		long end = gc.getTimeInMillis();
		
		long diff = end - start + 1;
		
		Timestamp result = new Timestamp(start + (long)(Math.random() * diff));
		
        return result;
	}
	
	private static Timestamp getRandomDate(Timestamp startDate, Timestamp endDate)
	{
		long start = startDate.getTime();
		long end = endDate.getTime();
		
		long diff = end - start + 1;
		
		Timestamp result = new Timestamp(start + (long)(Math.random() * diff));
		
        return result;
	}
	
	private static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
}
