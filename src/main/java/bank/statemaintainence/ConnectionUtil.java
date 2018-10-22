package bank.statemaintainence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	public static Connection getConnection() {
		
		try {
			
			String url = "jdbc:postgresql://class-db.cpu5qfifrml3.us-east-1.rds.amazonaws.com:5432/revature_ben";
			String username = System.getenv("JDBC_LOGIN");
			String password = System.getenv("JDBC_PASSWORD");
			return DriverManager.getConnection(url, username, password);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
			
		}
		
	}
	
}
