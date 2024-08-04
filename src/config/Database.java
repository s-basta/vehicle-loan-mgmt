package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	private static Connection CONN = null;

	private Database() {}
	
	public static Connection getConnection() {
		if(CONN != null) return CONN;
		
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			
			CONN = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "password");
			
			return CONN;
		}
		catch(Exception e) {
			System.out.println("Database Not Connected : "+e);
			return null;
		}
	}
	
}
