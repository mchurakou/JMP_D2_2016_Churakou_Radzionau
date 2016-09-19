package com.epam.jmp.dr.task10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import com.epam.jmp.dr.task10.db.DBHandler;

public class Runner {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(
	            System.in));
		
		DBHandler handler = new DBHandler();
		handler.init();
		if(handler.checkTablesExists()) {
			System.out.println("One, or more tables are excists in current database, would you like to generate them again? (y/n)");
			String str = "";
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
		//handler.generateTables();
		handler.close();
	}

}
