package com.epam.jmp.dr.task10.db;

public enum Queries {
	
	DROP_TABLES ("DROP TABLE IF EXISTS users, likes, friendships, posts;"),
	
	CREATE_USERS_TABLE ("CREATE TABLE users ("
			+ "id INT NOT NULL AUTO_INCREMENT, "
			+ "name VARCHAR(50) NOT NULL, "
			+ "surname VARCHAR(50) NOT NULL, "
			+ "birthdate DATE NOT NULL, "
			+ "PRIMARY KEY (id), "
			+ "UNIQUE INDEX id_UNIQUE (id ASC)"
			+ ");"),
	
	CREATE_FRIENDSHIPS_TABLE ("CREATE TABLE friendships ("
			+ "userid1 INT NOT NULL, "
			+ "userid2 INT NOT NULL, "
			+ "timestamp TIMESTAMP NOT NULL, "
			+ "PRIMARY KEY (userid1, userid2)"
			+ ");"),
	
	CREATE_POSTS_TABLE ("CREATE TABLE posts ("
			+ "id INT NOT NULL AUTO_INCREMENT, "
			+ "userId INT NOT NULL, "
			+ "text VARCHAR(500) NOT NULL, "
			+ "timestamp TIMESTAMP NOT NULL, "
			+ "PRIMARY KEY (id), "
			+ "UNIQUE INDEX id_UNIQUE (id ASC)"
			+ ");"),
	
	CREATE_LIKES_TABLE ("CREATE TABLE likes ("
			+ "postid INT NOT NULL, "
			+ "userid INT NOT NULL, "
			+ "timestamp TIMESTAMP NOT NULL, "
			+ "PRIMARY KEY (postid, userid)"
			+ ");"),
	
	INSERT_USER ("INSERT INTO users (name, surname, birthdate) VALUES(?, ?, ?);"),
	
	INSERT_FRIENDSHIP ("INSERT INTO friendships values(?, ?, ?);"),
	
	GET_USERS_COUNT ("SELECT COUNT(*) as users_count FROM users;"),
	
	GET_USER_FRIENDSHIPS ("SELECT userid2 AS friend FROM friendships WHERE userid1 = ?;"),
	
	INSERT_POST ("INSERT INTO posts(userId, text, timestamp) VALUES(?, ?, ?);"),
	
	GET_POSTS_COUNT ("SELECT COUNT(*) as posts_count from posts;"),
	
	GET_POST_TIMESTAMP ("SELECT timestamp FROM posts WHERE id = ?"),
	
	INSERT_LIKE ("INSERT INTO likes VALUES(?, ?, ?);");
	
	private final String query;
	
	Queries(String q)
	{
		this.query = q;
	}
	
	public static String getQuery(Queries q) {
		return q.getQuery();
	}
	
	public String getQuery()
	{
		return this.query;
	}
	
	public String valueOf()
	{
		return this.getQuery();
	}
	
	public String toString() {
		return this.getQuery();
	}

}
