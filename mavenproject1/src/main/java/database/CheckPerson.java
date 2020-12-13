package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

// checking database to see if certain person exists

public class CheckPerson 
{
	
	public static int CheckPersonForExistence(Connection connection, String login, String password) throws SQLException
	{
		// standard_person has UNIQUE condition on login and password
		String check_query = "SELECT person_id FROM standard_person WHERE (login = '"
				+ login + "' AND password = '"
				+ password + "')";
		ResultSet rs = DatabaseFunction.statementExecuteQuery(connection, check_query);
		rs.next();
		if (rs.getRow() == 1)
		{
			int i = Integer.parseInt(rs.getString(1));
			rs.close(); connection.close();
			return i;  // it does exist
		}
		else 
		{	// getRow() gives 0 after next(), meaning that there's 0 rows in sum
			rs.close();
			return 0; // it doesn't exist
		}
		// rs.getRow() can't be > 1, since database has UNIQUE condition for login
		// if it's 2, something have gone majorly wrong
	}
}
