package com.openthinks.assist.helpdesk.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DerbyTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";
		String URL ="jdbc:derby:hddb;create=true";
		Class.forName(driver);
//		URL ="jdbc:derby:hddb;shutdown=true";
		Connection conn = DriverManager.getConnection(URL);
		System.out.println(conn.getMetaData());
		
		
		
		conn.close();
		
		
	}
	
}
