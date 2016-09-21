package com.epam.jmp.dr.task10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import com.epam.jmp.dr.task10.db.DBHandler;

public class Runner {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(
	            System.in));
		
		String str = "";
		DBHandler handler = new DBHandler();
		handler.init();
		if(handler.checkTablesExists()) {
			System.out.println("One, or more tables are excists in current database, would you like to generate them again? (y/n)");
			try {
				str = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(str.equals("y"))
			{
				handler.generateTables();
			}
		}
		
		System.out.println("Show users with more than 20 likes in 2015 and more than 100 friends? (y/n)");
		try {
			str = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(str.equals("y"))
		{
			List<String> users = handler.getUsersData();
			for(String user : users)
			{
				System.out.println(user);
			}
		}
		handler.close();
	}

}
