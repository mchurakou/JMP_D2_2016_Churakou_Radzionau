package com.epam.jmp.dr.task10;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Runner {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		String USER = "root";
		String PASSW = "root";
		String URL = "jdbc:mysql://localhost:3306/task10";
		
		
		Connection conn = DriverManager.getConnection(URL, USER, PASSW);
		DatabaseMetaData databaseMetaData = conn.getMetaData();
		String productName = databaseMetaData.getDatabaseProductName();
		System.out.println(productName);
		conn.close();

	}

}
