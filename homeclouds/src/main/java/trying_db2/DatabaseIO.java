package trying_db2;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
//import java.sql.ResultSetMetaData; // for rs.getMetaData()
//import java.sql.SQLException; // throws SQLException
import java.util.ArrayList;
import java.util.List;


// insert person, log entry, file, access (BROKE IT AGAIN), general select, and select person by id

public class DatabaseIO 
{
	final public static String com = ", ";
	
	public static void insertPerson(Connection connection, String person_access_level, String name, 
			String email, String login, String password) throws SQLException
	{
		// name and email can be empty string
		// others can not
		String INSERTQuery;
		if (email != "")
		{
			INSERTQuery = "INSERT INTO standard_person(person_access_level, name, email, login, password) VALUES(";
			INSERTQuery += frameStr(person_access_level) + com
				+ frameStr(name) + com + frameStr(email) + com 
				+ frameStr(login) + com + frameStr(password) + ")";
		}
		else
		{
			INSERTQuery = "INSERT INTO standard_person(person_access_level, name, login, password) VALUES(";
			INSERTQuery += frameStr(person_access_level) + com
				+ frameStr(name) + com + frameStr(login) + com + frameStr(password) + ")";
		}
		DatabaseFunction.statementExecuteUpdate(connection, INSERTQuery);
		// if null string
	}
	
	public static void insertLogEntry(Connection connection, String entry_type, String message,
			String person_login, String IP) throws SQLException
	// can String house message VARCHAR(300) and others simultaneously 
	// message,	person_login, IP can be empty strings
	{
		String INSERTQuery = "INSERT INTO standard_log_entry(entry_type, message, person_login, IP) VALUES(";
		// what are restrictions on IP? 
		INSERTQuery += frameStr(entry_type) + com + frameStr(message) + com + frameStr(person_login) + com;
		if (IP == "")
			INSERTQuery += " null)";
		else
			INSERTQuery += frameStr(IP) + ")"; // NOT TESTED
		DatabaseFunction.statementExecuteUpdate(connection, INSERTQuery);
	}

	public static void insertFile(Connection connection, String path_to_file, String file_name, 
			String general_file_access_level, String creator_login) throws SQLException
	{
		// adding \ or '\' might create problem (in path)
		// / is fine? 
		String INSERTQuery = "INSERT INTO standard_file(path_to_file, file_name, general_file_access_level, "
				+ "creator_login) VALUES(";
		INSERTQuery += frameStr(path_to_file) + com + frameStr(file_name) 
				+ com + frameStr(general_file_access_level) + com 
				+ frameStr(creator_login) + ")"; 
		DatabaseFunction.statementExecuteUpdate(connection, INSERTQuery);
	}
	
	public static void insertAccess(Connection connection, String path_to_file, String file_name,
			String person_login, String file_access_level) throws SQLException
	// DOES NOT WORK	
	{
		String INSERTQuery = "INSERT INTO standard_file(path_to_file, file_name, person_login, "
				+ "file_access_level) VALUES(";
		INSERTQuery += frameStr(path_to_file) + com + frameStr(file_name) + com
				+ frameStr(person_login) + com + frameStr(file_access_level) + ")"; 
		DatabaseFunction.statementExecuteUpdate(connection, INSERTQuery);
	}
	
	public static List<String> simpleSelect(Connection connection, String SELECTQuery) throws SQLException
    {
        List<String> selectList = new ArrayList<String>(); 
  		// executing SELECTQuery and writing results in ResultSet
    	ResultSet rs = DatabaseFunction.statementExecuteQuery(connection, SELECTQuery);
    	
    	int columnsNumber = rs.getMetaData().getColumnCount();    	
    	while (rs.next()) 
    		for (int i = 1; i <= columnsNumber; i++) 
    			selectList.add(rs.getString(i));
    	rs.close(); rs = null;
    	// // connection.close(); connection = null;
    	return selectList;
    	// rsmd doesn't have 'close'
    	// check it for "org.postgresql.util.PSQLException: Çàïðîñ íå âåðíóë ðåçóëüòàòîâ." in the future?
    	// possible exception Cannot invoke "java.sql.ResultSet.getMetaData()" because "rs" is null
    	// results metadata, like column names and such
    } 
	
	public static List<String> personSelect(Connection connection, int id) throws SQLException
	// selects with password - for testing purposes mostly
	// perhaps 'public' is not what I want from it
	{
		List<String> personInfo = new ArrayList<String>(); 
		if (id == 0)
			return personInfo;
		String query = "SELECT * FROM standard_person WHERE person_id = " + id;
		personInfo = simpleSelect(connection, query);
		return personInfo;
	}
	
	public static List<String> personSelect(Connection connection, String login) throws SQLException
	// selects with password - for testing purposes mostly
	// perhaps 'public' is not what I want from it
	{
		List<String> personInfo = new ArrayList<String>(); 
		String query = "SELECT * FROM standard_person WHERE login = " + frameStr(login);
		personInfo = simpleSelect(connection, query);
		return personInfo;
	}
	
	public static List<String> personSelectwoPass(Connection connection, int id) throws SQLException
	// selects with password - for testing purposes mostly
	{
		List<String> personInfo = new ArrayList<String>(); 
		if (id == 0)
			return personInfo;
		String query = "SELECT person_id, person_access_level, name, email, login "
				+ "FROM standard_person WHERE person_id = " + id;
		personInfo = simpleSelect(connection, query);
		return personInfo;
	}
	
	public static List<String> personSelectwoPass(Connection connection, String login) throws SQLException
	// selects with password - for testing purposes mostly
	{
		List<String> personInfo = new ArrayList<String>();
		String query = "SELECT person_id, person_access_level, name, email, login "
				+ "FROM standard_person WHERE login = " + frameStr(login);
		personInfo = simpleSelect(connection, query);
		return personInfo;
	}
	
	public static List<String> getAllLogins(Connection connection) throws SQLException
	{
		List<String> logins = new ArrayList<String>();
		String query = "SELECT login FROM standard_person";
		logins = simpleSelect(connection, query);
		return logins;
	}
	
	public static String frameStr(String stringToAdapt)
	{
		stringToAdapt = "'" + stringToAdapt + "'";
		return stringToAdapt;
	}
	// created it twice. Need to simplify usage.
}