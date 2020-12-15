package trying_db2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


//import trying_db2.DatabaseIO; // for frameString
 // how to lessen import?

// miscellaneous functions
// does person or table exist, execute update and query, deletions
public class DatabaseFunction 
{
	public static boolean checkTableForExistence(String tableName, Connection connection) throws SQLException
	{
		// searching database for table
		String check_query = "SELECT to_regclass('public." + tableName + "')";
		ResultSet rs = statementExecuteQuery(connection, check_query);
		rs.next();
	    if (rs.getString(1) == null)
		{
	    	rs.close(); rs = null;
			return false; // it doesn't exist
		}
		else
		{
			rs.close(); rs = null;		// why have I // it
			return true; // it does exist
		}
	}
	
	public static int checkPersonForExistence(Connection connection, String login, String password) throws SQLException
	// checking database to see if certain person exists
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
			rs.close(); rs = null;
			return i;  // it does exist
		}
		else 
		{	// getRow() gives 0 after next(), meaning that there's 0 rows in sum
			rs.close(); rs = null;
			return 0; // it doesn't exist
		}
		// rs.getRow() can't be > 1, since database has UNIQUE condition for login
		// if it's 2, something have gone majorly wrong
	}
	
	public static void statementExecuteUpdate(Connection connection, String query) throws SQLException
	{
		// creating statement here every time is expensive
		// and doing it outside just takes too many lines
		Statement statement = connection.createStatement();
		// add checks, and try to break it
		statement.executeUpdate(query);
		statement.close(); statement = null; // am I sure it's no longer necessary?
		// how do I work with errors?
	}
	
	public static ResultSet statementExecuteQuery(Connection connection, String query) throws SQLException
	{
		ResultSet rs = null;
		Statement statement = connection.createStatement();
		// add checks, and try to break it
		rs = statement.executeQuery(query);
		//rs.close();
		statement.close(); statement = null;
		return rs; // there's a function of ResultSet for returning statement that created it
		// how do I work with errors?
	}
	
	// do remember, that tables reference each other.
	public static void deletePerson(Connection connection, int id) throws SQLException
	{
		//if (id > 0)
		statementExecuteUpdate(connection, "DELETE FROM standard_person WHERE person_id = " + id);
	}
	
	public static void deletePerson(Connection connection, String login) throws SQLException
	{
		// add pass check? Is it really needed?
		statementExecuteUpdate(connection, "DELETE FROM standard_person WHERE login = " + frameStr(login));
	}

	public static void deleteFile(Connection connection, int id) throws SQLException
	{
		//if (id > 0)
		statementExecuteUpdate(connection, "DELETE FROM standard_file WHERE id_of_file = " + id);
	}
	
	public static void deleteAccess(Connection connection, int id) throws SQLException
	{
		//if (id > 0)
		statementExecuteUpdate(connection, "DELETE FROM standard_access WHERE access_id = " + id);
	}
	
	public static void deleteAllAboutPerson(Connection connection, String login) throws SQLException
	// for testing purposes only
	{
		statementExecuteUpdate(connection, "DELETE FROM standard_log_entry WHERE person_login = " + frameStr(login));
		statementExecuteUpdate(connection, "DELETE FROM standard_access WHERE person_login = " + frameStr(login));
		statementExecuteUpdate(connection, "DELETE FROM standard_file WHERE creator_login = " + frameStr(login));
		deletePerson(connection, login);
	}
	// create id variation? Or maybe switch to id
	
	// delete all logs? or about person
	
	// THERE'S NO FUNCTIONS FOR DELETING LOGS
	// THIS IS A DELIBERATE CHOICE
	// so far
	
	public static String frameStr(String stringToAdapt)
	{
		stringToAdapt = "'" + stringToAdapt + "'";
		return stringToAdapt;
	}
	// created it twice. Need to simplify usage.
	
	// if adding connection in function actually links it (CHECK) then I can
	// create function closeConnection with try...catch block inside
	// fail.  But others success. Why, though? Is it class placement?
}
