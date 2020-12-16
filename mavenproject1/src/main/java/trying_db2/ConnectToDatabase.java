package trying_db2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// class for connecting to database
public class ConnectToDatabase 
{
	// yet it's kinda expensive to connect every time
	
	final static String curDatabase = "jdbc:postgresql://localhost:5432/postgres"; // database data 
	// will it also be localhost in the final version? 
	final static String user = "postgres"; // user login
	final static String password = "123"; // user password
	// PASSWORD IS DIFFERENT IN DISC DATABASE

	public static Connection getConnection() throws SQLException, ClassNotFoundException 
	// named as DriverManager.getConnection, might be confusing
	{
		Connection connection = null;
		Class.forName("org.postgresql.Driver"); // register JDBC driver
		// trying to connect
		connection = DriverManager.getConnection(curDatabase, user, password);
		/*
		if (connection != null) System.out.println("Connection successful");
		else System.out.println("Connection failed");
		*/ // will likely need it after merge
		return connection; // Is it original? Or is original left unclosed?
	}
}
