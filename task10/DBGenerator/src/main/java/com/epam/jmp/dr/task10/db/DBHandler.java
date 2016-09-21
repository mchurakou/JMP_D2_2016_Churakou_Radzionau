package com.epam.jmp.dr.task10.db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DBHandler {
	
	private static String USER = "root";
	private static String PASSWORD = "root";
	private static String URL = "jdbc:mysql://localhost:3306/task10?rewriteBatchedStatements=true";
	
	private static int EXECUTE_INSERT_BATCH_COUNT = 1000;
	
	private static int LIKES_AMOUNT = 800000;
	private static int POSTS_AMOUNT = 150000;
	private static int USER_MAX_FRIENDSHIPS = 150;
	
	private Connection conn;
	
	public void init() {
		conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);;			
		} catch (SQLException e) {
			try {
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void close() {
		if(conn != null)
		{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean checkTablesExists() {
		try {
			DatabaseMetaData databaseMetaData = conn.getMetaData();
			ResultSet result = databaseMetaData.getTables(null, null, null, null);

			while (result.next()) {
				String tableName = result.getString(3);

				if(tableName.equals("friendships") || tableName.equals("likes") || tableName.equals("posts") || tableName.equals("users"))
				{
					result.close();
					return true;
				}
			}
			
			result.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	
	public void generateTables()
	{
		createTables();
		generateUsers();
		generateFriendships();
		generatePosts();
		generateLikes();
	}
	
	public List<String> getUsersData() {
		List<String> result = new ArrayList<String>();
		
		try (Statement st = conn.createStatement();)
		{
			
			ResultSet users = st.executeQuery(Queries.GET_USERS_DATA.getQuery());
			
			while(users.next())
			{
				String tmp = "";
				tmp += users.getString("user_name") + " " + users.getString("user_surname") + " | ";
				tmp += "Likes: " + users.getInt("likes_count") + ", " + "Friends: " + users.getInt("friends_count");
				
				result.add(tmp);
			}
			
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	private void generateLikes()
	{
		System.out.println("Creating likes....");
		
		try (
				Statement count = conn.createStatement();
				PreparedStatement postTimestampStmt = conn.prepareStatement(Queries.GET_POST_TIMESTAMP.getQuery());
				PreparedStatement insertLike = conn.prepareStatement(Queries.INSERT_LIKE.getQuery());
				)
		{
			ResultSet usersCountSet = count.executeQuery(Queries.GET_USERS_COUNT.getQuery());
			usersCountSet.first();
			int usersCount = usersCountSet.getInt("users_count");
			
			ResultSet postsCountSet = count.executeQuery(Queries.GET_POSTS_COUNT.getQuery());
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
			
			for(int i = 0; i < LIKES_AMOUNT; i++)
			{
				int userid = randBetween(1, usersCount);
				int postId = randBetween(1, postsCount);
				
				postLikes.get(postId).add(userid);
			}
			System.out.println("Generating likes map.... Done");
			
			System.out.println("Inserting likes....");
			for (Map.Entry<Integer, Set<Integer>> entry : postLikes.entrySet()) {
				int postId = entry.getKey();
				Set<Integer> likesUsersIds = entry.getValue();
				
				postTimestampStmt.setInt(1, postId);
				ResultSet postTimestampSet = postTimestampStmt.executeQuery();
				postTimestampSet.first();
				Timestamp postCreationTimestamp = postTimestampSet.getTimestamp("timestamp");
				
				int counter = 0;
				for(Integer userId : likesUsersIds)
				{
					
					Timestamp likeTimestamp = getRandomDate(postCreationTimestamp, new Timestamp(new java.util.Date().getTime()));
					
					insertLike.setInt(1, postId);
					insertLike.setInt(2, userId);
					insertLike.setTimestamp(3, likeTimestamp);
					
					insertLike.addBatch();
					
					counter++;
					if(counter == EXECUTE_INSERT_BATCH_COUNT)
					{
						counter = 0;
						insertLike.executeBatch();
					}
					
				}
			}
			
			insertLike.executeBatch();
			System.out.println("Inserting likes...   Done");
			
			postTimestampStmt.close();
			insertLike.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Creating likes.... Done");
	}
	
	private void generatePosts()
	{
		System.out.println("Creating posts....");
		
		try(
				Statement countUsersStatement = conn.createStatement();
				PreparedStatement insertPost = conn.prepareStatement(Queries.INSERT_POST.getQuery());
				){
			ResultSet usersCountSet = countUsersStatement.executeQuery(Queries.GET_USERS_COUNT.getQuery());
			usersCountSet.first();
			int usersCount = usersCountSet.getInt("users_count");
			countUsersStatement.close();
			
			int counter = 0;
			System.out.println("Inserting posts....");
			for(int i = 0; i < 150000; i++)
			{
				int userId = randBetween(1, usersCount);
				Timestamp timestamp = getRandomDate(2010, 2015);
				String text = "This post was written by user with id: " + userId + " on " + timestamp.toString();
				
				insertPost.setInt(1, userId);
				insertPost.setString(2, text);
				insertPost.setTimestamp(3, timestamp);
				
				insertPost.addBatch();
				
				counter++;
				if(counter == EXECUTE_INSERT_BATCH_COUNT)
				{
					counter = 0;
					insertPost.executeBatch();
				}
			}

			insertPost.executeBatch();
			System.out.println("Inserting posts.... Done");

			insertPost.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("Creating posts.... Done");
	}
	
	private void generateFriendships()
	{
		System.out.println("Creating friendships....");
		try (
				PreparedStatement insertFriendshipStatement = conn.prepareStatement(Queries.INSERT_FRIENDSHIP.getQuery());
				Statement countUsersStatement = conn.createStatement();
				PreparedStatement getUserFriendships = conn.prepareStatement(Queries.GET_USER_FRIENDSHIPS.getQuery());
				) {
			
			
			ResultSet usersCountSet = countUsersStatement.executeQuery(Queries.GET_USERS_COUNT.getQuery());
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
				int friendsAmount = randBetween(1, USER_MAX_FRIENDSHIPS);
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
			System.out.println("Adding friendships pairs...  Done");
			
			System.out.println("Inserting friendships...");
			int counter = 0;
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
					
					counter++;
					if(counter == EXECUTE_INSERT_BATCH_COUNT)
					{
						counter = 0;
						insertFriendshipStatement.executeBatch();
					}
				}
			}
			
			System.out.println("Inserting friendships... Done");
			insertFriendshipStatement.executeBatch();
			
			
			getUserFriendships.close();
			insertFriendshipStatement.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Creating friendships finished");
	}
	
	private void generateUsers()
	{
		System.out.println("Creating users....");
		try (
				PreparedStatement statement = conn.prepareStatement(Queries.INSERT_USER.getQuery());) {
			
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
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Creating users finished");
	}
	
	private void createTables() {
		System.out.println("Creating tables...");
		try (Statement statement = conn.createStatement();) {
			
			statement.executeUpdate(Queries.DROP_TABLES.getQuery());
			
			statement.executeUpdate(Queries.CREATE_USERS_TABLE.getQuery());
			statement.executeUpdate(Queries.CREATE_FRIENDSHIPS_TABLE.getQuery());
			statement.executeUpdate(Queries.CREATE_POSTS_TABLE.getQuery());
			statement.executeUpdate(Queries.CREATE_LIKES_TABLE.toString());
			
			statement.close();
			
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
