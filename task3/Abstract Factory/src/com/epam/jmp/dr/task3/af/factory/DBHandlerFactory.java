package com.epam.jmp.dr.task3.af.factory;

import java.sql.Connection;
import java.sql.DriverManager;

import com.epam.jmp.dr.task3.af.handler.DBHandler;
import com.epam.jmp.dr.task3.af.handler.Handler;

/**
 * implementtation of AbstractHandlerFactory class
 *
 */
public class DBHandlerFactory extends AbstractHandlerFactory {

	/**
	 * Builds DBHandler with provided details
	 */
	@Override
	public Handler createHandler(String details) throws Exception {

		String detailsParts[] = details.split(";");
		String host = detailsParts[0];
		String db = detailsParts[1];
		String user = detailsParts[2];
		String passw = detailsParts[3];

		String dbServerStr = "jdbc:mysql://" + host + "/" + db;

		String driverName = "org.gjt.mm.mysql.Driver";
		Class.forName(driverName);

		Connection connection = DriverManager.getConnection(dbServerStr, user, passw);

		DBHandler handler = new DBHandler(connection);

		if (!handler.checkIfTableExsists()) {
			handler.createTable();
		}

		return handler;

	}

}
