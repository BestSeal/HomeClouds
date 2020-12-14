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
	    	rs.close();
			return false; // it doesn't exist
		}
		else
		{
			rs.close();		// why have I // it
			return true; // it does exist
		}
	}
	
	public static void statementExecuteUpdate(Connection connection, String query) throws SQLException
	{
		// creating statement here every time is expensive
		// and doing it outside just takes too many lines
		Statement statement = connection.createStatement();
		// add checks, and try to break it
		statement.executeUpdate(query);
		statement.close(); // am I sure it's no longer necessary?
		// how do I work with errors?
	}
	
	public static ResultSet statementExecuteQuery(Connection connection, String query) throws SQLException
	{
		ResultSet rs = null;
		Statement statement = connection.createStatement();
		// add checks, and try to break it
		rs = statement.executeQuery(query);
		//rs.close();
		statement.close();
		return rs; // there's a function of ResultSet for returning statement that created it
		// how do I work with errors?
	}
	
	// if adding connection in function actually links it (CHECK) then I can
	// create function closeConnection with try...catch block inside
}
