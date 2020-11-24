package trying_db2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// class for connecting to database
public class ConnectToDatabase 
{
	// yet it's kinda expensive to connect every time

	public static Connection GetConnection() // add throws exception instead of try?
	{
		final String curDatabase = "jdbc:postgresql://localhost:5432/postgres"; // database data 
		// will it also be localhost in the final version? 
		final String user = "postgres"; // user data
		final String password = "elju200postgre"; // user data
		// PASSWORD IS DIFFERENT IN DISC DATABASE

		Connection connection = null;
		try 
		{
			Class.forName("org.postgresql.Driver"); 
			// trying to connect
			connection = DriverManager.getConnection(curDatabase, user, password);
			// throw this out in the future 
			if (connection != null) 
				System.out.println("Connection successful");
			else 
				System.out.println("Connection failed");
		}
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
		return connection;
	}
}
