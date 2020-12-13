package database;

import java.sql.Connection;
import java.sql.SQLException;
// class for insertion into database

// table information is used
// perhaps outsource it somewhere?
 
public class DatabaseINSERT 
{
	public static String com = ", ";
	
	public static void InsertPerson(Connection connection, String person_access_level, String name, 
			String email, String login, String password) throws SQLException
	{
		String INSERTQuery = "INSERT INTO standard_person(person_access_level, name, email, login, password) VALUES(";
		// name and email can be empty string
		// others can not
		INSERTQuery += FrameStr(person_access_level) + com
				+ FrameStr(name) + com + FrameStr(email) + com 
				+ FrameStr(login) + com + FrameStr(password) + ")";
		DatabaseFunction.statementExecuteUpdate(connection, INSERTQuery);
	}
	
	public static void InsertLogEntry(Connection connection, String entry_type, String message,
			String person_login, String IP) throws SQLException
	// can String house message VARCHAR(300) and others simultaneously 
	// message,	person_login, IP can be empty strings
	{
		String INSERTQuery = "INSERT INTO standard_log_entry(entry_type, message, person_login, IP) VALUES(";
		// what are restrictions on IP? 
		INSERTQuery += FrameStr(entry_type) + com + FrameStr(message) + com + FrameStr(person_login) + com;
		if (IP == "")
			INSERTQuery += " null)";
		else
			INSERTQuery += FrameStr(IP) + ")";
		DatabaseFunction.statementExecuteUpdate(connection, INSERTQuery);
	}

	public static void InsertFile(Connection connection, String path_to_file, String file_name, 
			String general_file_access_level, String creator_login) throws SQLException
	{
		// adding \ or '\' might create problem (in path)
		// / is fine? 
		String INSERTQuery = "INSERT INTO standard_file(path_to_file, file_name, general_file_access_level, "
				+ "creator_login) VALUES(";
		INSERTQuery += FrameStr(path_to_file) + com + FrameStr(file_name) 
				+ com + FrameStr(general_file_access_level) + com 
				+ FrameStr(creator_login) + ")"; 
		DatabaseFunction.statementExecuteUpdate(connection, INSERTQuery);
	}
	
	public static void InsertAccess(Connection connection, String path_to_file, String file_name,
			String person_login, String file_access_level) throws SQLException
	
	// DOES NOT WORK
	
	{
		String INSERTQuery = "INSERT INTO standard_file(path_to_file, file_name, person_login, "
				+ "file_access_level) VALUES(";
		INSERTQuery += FrameStr(path_to_file) + com + FrameStr(file_name) + com
				+ FrameStr(person_login) + com + FrameStr(file_access_level) + ")"; 
		DatabaseFunction.statementExecuteUpdate(connection, INSERTQuery);
	}
	
	public static String FrameStr(String stringToAdapt)
	{
		stringToAdapt = "'" + stringToAdapt + "'";
		return stringToAdapt;
	}
	
}
