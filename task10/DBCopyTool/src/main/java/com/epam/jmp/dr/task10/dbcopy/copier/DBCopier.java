package com.epam.jmp.dr.task10.dbcopy.copier;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBCopier {
	
	private static String URL = "jdbc:mysql://localhost:3306/";
	
	private static String DROP_TABLES = "DROP TABLE IF EXISTS ";
	
	private Connection srcConn;
	private Connection targetConn;
	
	
	public void copy()
	{
		
		for(String s : getCreateStatements())
		{
			System.out.println(s);
		}
		
	}
	
	private List<String> getCreateStatements()
	{
		List<String> result = new ArrayList<String>();
		try {
			DatabaseMetaData databaseMetaData = srcConn.getMetaData();
			List<String> tableNames = getSrcTableNames(databaseMetaData);
			for(String tblName : tableNames)
			{
				String query = "CREATE TABLE " + tblName + " ( ";
				query += constructCreateColumnsPart(databaseMetaData, tblName);
				query += ");";
				result.add(query);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	private String constructCreateColumnsPart(DatabaseMetaData databaseMetaData, String tblName) {
		String result = " ";

		try {

			ResultSet rs = databaseMetaData.getColumns(null, null, tblName, null);
			while (rs.next()) {
				String cName = rs.getString("COLUMN_NAME");
				String type = rs.getString("TYPE_NAME");
				String size = rs.getString("COLUMN_SIZE");
				boolean isNull = "YES".equals(rs.getString("IS_NULLABLE"));

				result += cName + " " + type + "(" + size + ")";
				if(!isNull)
				{
					result += " NOT NULL";
				}
				if(!rs.isLast())
				{
					result += ",";
				}
			}
			
			rs = databaseMetaData.getPrimaryKeys(null, null, tblName);
			String pkCollsNames = "";
			
			while(rs.next())
			{
				pkCollsNames += rs.getString("COLUMN_NAME");
				if(!rs.isLast())
				{
					pkCollsNames += ",";
				}
			}
			if(!pkCollsNames.equals(""))
			{
				result += " , " + "PRIMARY KEY (" + pkCollsNames + ")";
			}
			
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	private List<String> getSrcTableNames(DatabaseMetaData databaseMetaData) {
		List<String> result = new ArrayList<String>();
		try {
			String types[] = { "TABLE" };
			ResultSet rs = databaseMetaData.getTables(null, null, null, types);

			while (rs.next()) {
				String str = rs.getString("TABLE_NAME");
				result.add(str);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void init(String srcDBName, String targetDBName, String user, String password)
	{
		srcConn = null;
		targetConn = null;
		try {
			srcConn = DriverManager.getConnection(URL + srcDBName, user, password);
			targetConn = DriverManager.getConnection(URL + targetDBName, user, password);	
		} catch (SQLException e) {
			try {
				srcConn.close();
				targetConn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void close() {
		if(srcConn != null)
		{
			try {
				srcConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(targetConn != null)
		{
			try {
				targetConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
