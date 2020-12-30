package cloudDatabase;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

// create table, drop it
// is there better way to describe it? 
// some rules for documentation in lab 1?

public class DatabaseTables 
{

	final static String codeWord = "CheckTableForExistence"; // perhaps not final, but
	final static String relativePathToFile = "opt/tomcat/users/tableInformation.txt";
	// "src\\trying_db2\\tableInformation.txt"
	
	public static void createAllTables(Connection connection) throws FileNotFoundException, SQLException 
	{
		File fileObj = new File(relativePathToFile); // check if it exist
		// if it doesn't, return error message through 'return' command?
		Scanner fileReader = new Scanner(fileObj);
		boolean itExists = true;
		String currentQuery = "";
		String data;
		// taking sql code from file in form and executing those 
		// statements if tables they interact with do not exist
		while (fileReader.hasNextLine()) 
		{
			data = fileReader.nextLine();
			if (data.contains(codeWord))
			{
				if (!itExists) DatabaseFunction.statementExecuteUpdate(connection, currentQuery);
				String[] resultOfSplit = data.split(" ", 2); // in theory, it is just two parts
				// check for mistakes here
				itExists = DatabaseFunction.checkTableForExistence(resultOfSplit[1], connection);
				currentQuery = "";
			} else
			{
				if (!itExists) currentQuery += data;
				// check for mistakes
			}
		}			
		if (!itExists) DatabaseFunction.statementExecuteUpdate(connection, currentQuery);
		fileReader.close();
		//connection.close();
		System.out.println("Tables exist now"); //
	}
	
	public static void dropAllTables(Connection connection) throws SQLException
	// for deleting all table information from database
	// WARNING: DANGEROUS FUNCTION
	// USING MAIN WILL EMPTY ALL DATA IN ALL TABLES
	{
		// DROPping tables doesn't drop sequences, so id =3, 6,... is the result
		// can be automated (find out all tables, for ... Cascade) 
		// 
		String[] tableNamesInDeletionOrder = {"shared_files", "accesses", "standard_file", "standard_log_entry",
				"standard_person", "user_access_levels", "file_access_levels"}; // perhaps outsource it further
		// now that I think about it, it's not necessary to drop everything
		// I can just drop files whose names I put in args
		// I will need to use drop ... cascade, though
		// 		checkforexistance already included anyway
		// not sure how to comment on that
		for (String currentTable : tableNamesInDeletionOrder)
			if (DatabaseFunction.checkTableForExistence(currentTable, connection))
				DatabaseFunction.statementExecuteUpdate(connection, "DROP TABLE " + currentTable + " CASCADE;");
		System.out.println("Tables do not exist now");
	}
}
