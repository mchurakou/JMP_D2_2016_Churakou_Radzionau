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
	
	INSERT_LIKE ("INSERT INTO likes VALUES(?, ?, ?);"),
	
	GET_USERS_DATA (" select * from \r\n" + 
			" (\r\n" + 
			"	select ulc.user_id, ulc.user_name, ulc.user_surname, ulc.likes_count, COUNT(ulc.user_id) as friends_count \r\n" + 
			"	from \r\n" + 
			"	(\r\n" + 
			"		select u.id as user_id, u.name as user_name, u.surname as user_surname, \r\n" + 
			"		COUNT(u.id) as likes_count\r\n" + 
			"		from posts as p\r\n" + 
			"		join likes as l on l.postid = p.id\r\n" + 
			"		join users as u on p.userId = u.id\r\n" + 
			"		where l.timestamp >= '2015-05-01 00:00:00' and l.timestamp <= '2015-05-31 23:59:59'\r\n" + 
			"		group by u.id\r\n" + 
			"	) as ulc\r\n" + 
			"	join friendships as f on f.userid1 = ulc.user_id\r\n" + 
			"	group by ulc.user_id\r\n" + 
			" ) as result\r\n" + 
			" where result.likes_count > 20 and result.friends_count > 100\r\n" + 
			" order by result.likes_count desc, result.friends_count desc");
	
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
