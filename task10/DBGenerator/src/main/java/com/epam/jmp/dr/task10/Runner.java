package com.epam.jmp.dr.task10;

import java.sql.SQLException;

public class Runner {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		DBHandler.createTables();
		DBHandler.generateUsers();
		DBHandler.generateFriendships();
		DBHandler.generatePosts();
		DBHandler.generateLikes();
	}

}
