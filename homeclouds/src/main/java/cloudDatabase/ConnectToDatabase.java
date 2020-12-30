package cloudDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// class for connecting to database
public class ConnectToDatabase 
{
	final static String curDatabase = "jdbc:postgresql://localhost:5432/clouds"; // database data 
	// will it also be localhost in the final version? 
	final static String user = "postgres"; // user login
	final static String password = "123"; // user password


	public static Connection getConnection() throws SQLException, ClassNotFoundException 
	{
		Connection connection = null;
		Class.forName("org.postgresql.Driver"); // register JDBC driver
		// trying to connect
		connection = DriverManager.getConnection(curDatabase, user, password);
		return connection;
	}
}
