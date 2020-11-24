package trying_db2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// miscellaneous functions

public class DatabaseFunction 
{
	public static boolean CheckTableForExistence(String tableName, Connection connection) throws SQLException
	{
		// searching database for table
		String check_query = "SELECT to_regclass('public." + tableName + "')";
		ResultSet rs = statementExecuteQuery(connection, check_query);
		rs.next();
	    if (rs.getString(1) == null)
		{
			return false; // it doesn't exist
		}
		else
		{
			return true; // it does exist
		}
	}
	
	public static void statementExecuteUpdate(Connection connection, String query)
	{
		// creating statement here every time is expensive
		// and doing it outside just takes too many lines
		try
		{
			Statement statement = connection.createStatement();
			// add checks
			// and try to break it
			statement.executeUpdate(query);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		// how do I work with errors?
	}
	
	public static ResultSet statementExecuteQuery(Connection connection, String query)
	{
		ResultSet rs = null;
		try
		{
			Statement statement = connection.createStatement();
			// add checks
			// and try to break it
			rs = statement.executeQuery(query);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
		// how do I work with errors?
	}
		
}
