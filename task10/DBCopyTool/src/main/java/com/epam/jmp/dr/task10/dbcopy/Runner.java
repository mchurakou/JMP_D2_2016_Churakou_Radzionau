package com.epam.jmp.dr.task10.dbcopy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.epam.jmp.dr.task10.dbcopy.copier.DBCopier;

public class Runner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(
	            System.in));
		
		String srcDBName = "";
		String targetDBName = "";
		String user = "";
		String password = "";
		
		DBCopier dbc = new DBCopier();
		
		/*try {
			System.out.println("Enter target database name: ");
			targetDBName = reader.readLine();
			System.out.println("Enter source database name: ");
			srcDBName = reader.readLine();
			System.out.println("Enter target user name: ");
			user = reader.readLine();
			System.out.println("Enter target password: ");
			password = reader.readLine();
			
			dbc.init(srcDBName, targetDBName, user, password);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		srcDBName = "task10";
		targetDBName = "test";
		user = "root";
		password = "root";
		
		dbc.init(srcDBName, targetDBName, user, password);
		dbc.copy();
		dbc.close();
	}

}
