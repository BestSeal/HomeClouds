package trying_db2;

import java.sql.Connection;

// class for insertion into database

// table information is used
// perhaps outsource it somewhere?
 
public class DatabaseINSERT 
{
	public static String com = ", ";
	
	public static void InsertPerson(String person_access_level, String name, String email, String login, String password)
	{
		Connection connection = ConnectToDatabase.GetConnection(); // use it for others
		String INSERTQuery = "INSERT INTO standard_person(person_access_level, name, email, login, password) VALUES(";
		// name and email can be empty string
		// others can not
		INSERTQuery += FrameStr(person_access_level) + com
				+ FrameStr(name) + com + FrameStr(email) + com 
				+ FrameStr(login) + com + FrameStr(password) + ")";
		DatabaseFunction.statementExecuteUpdate(connection, INSERTQuery);
	}
	
	public static void InsertLogEntry(String entry_type, String message, String person_login, String IP, String when_happened)
	// can String house message VARCHAR(300) and others simultaneously 
	// message,	person_login, IP can be empty strings
	{
		Connection connection = ConnectToDatabase.GetConnection(); 
		String INSERTQuery = "INSERT INTO standard_log_entry(entry_type, message, person_login, IP, when_happened) VALUES(";
		// what are restrictions on IP? 
		INSERTQuery += FrameStr(entry_type) + com
				+ FrameStr(message) + com + FrameStr(person_login) + com 
				+ FrameStr(IP) + com + FrameStr(when_happened) + ")"; 
		DatabaseFunction.statementExecuteUpdate(connection, INSERTQuery);
	}

	public static void InsertFile(String path_to_file, String file_name, String general_file_access_level, String creator_login)
	{
		Connection connection = ConnectToDatabase.GetConnection(); 
		String INSERTQuery = "INSERT INTO standard_file(path_to_file, file_name, general_file_access_level, creator_login) VALUES(";
		INSERTQuery += FrameStr(path_to_file) + com
				+ FrameStr(file_name) + com + FrameStr(general_file_access_level) + com 
				+ FrameStr(creator_login) + ")"; 
		DatabaseFunction.statementExecuteUpdate(connection, INSERTQuery);
	}
	
	public static void InsertAccess(String path_to_file, String file_name, String person_login, String file_access_level)
	{
		Connection connection = ConnectToDatabase.GetConnection(); 
		String INSERTQuery = "INSERT INTO standard_file(path_to_file, file_name, person_login, file_access_level) VALUES(";
		INSERTQuery += FrameStr(path_to_file) + com
				+ FrameStr(file_name) + com + FrameStr(person_login) + com 
				+ FrameStr(file_access_level) + ")"; 
		DatabaseFunction.statementExecuteUpdate(connection, INSERTQuery);
	}
	
	public static String FrameStr(String stringToAdapt)
	{
		stringToAdapt = "'" + stringToAdapt + "'";
		return stringToAdapt;
	}
	
}
