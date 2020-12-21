package cloudDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// miscellaneous functions like
// does person or table exist, execute update and query, deletions
public class DatabaseFunction 
{
	public static boolean checkTableForExistence(String tableName, Connection connection) throws SQLException
	// searching database for table
	{
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
			rs.close(); rs = null;
			return true; // it does exist
		}
	}
	
	public static int getPersonId(Connection connection, String login) throws SQLException
	// checking database to see if certain person exists
	{
		// standard_person has UNIQUE condition on login and password
		String check_query = "SELECT person_id FROM standard_person WHERE login = " + frameStr(login);
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
		Statement statement = connection.createStatement();
		statement.executeUpdate(query);
		statement.close(); statement = null;
	}
	
	public static ResultSet statementExecuteQuery(Connection connection, String query) throws SQLException
	{
		ResultSet rs = null;
		Statement statement = connection.createStatement();
		rs = statement.executeQuery(query);
		statement.close(); statement = null;
		return rs; 
	}
	
	// do remember, that tables reference each other.
	public static void deletePerson(Connection connection, int id) throws SQLException
	{
		statementExecuteUpdate(connection, "DELETE FROM standard_person WHERE person_id = " + id);
	}
	
	public static void deletePerson(Connection connection, String login) throws SQLException
	{
		statementExecuteUpdate(connection, "DELETE FROM standard_person WHERE login = " + frameStr(login));
	}

	public static void deleteFile(Connection connection, int id) throws SQLException
	{
		statementExecuteUpdate(connection, "DELETE FROM standard_file WHERE id_of_file = " + id);
	}
	
	public static void deleteAccess(Connection connection, int id) throws SQLException
	{
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
	
	public static void deleteLink(Connection connection, String login, String link) throws SQLException
	{
		statementExecuteUpdate(connection, "DELETE FROM shared_files WHERE link = " + frameStr(link)
				+ " AND creator_login = " + frameStr(login));
	}
	
	// THERE'S NO FUNCTIONS FOR DELETING LOGS
	// THIS IS A DELIBERATE CHOICE
	
	public static String frameStr(String stringToAdapt)
	{
		stringToAdapt = "'" + stringToAdapt + "'";
		return stringToAdapt;
	}
}
