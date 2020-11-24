package trying_db2;

import java.sql.Connection;
import java.sql.SQLException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// table information is used

public class CreateTables 
{
	public static void CreateAllTables()
	{
		final String codeWord = "CheckTableForExistence"; // perhaps not final, but
		final String relativePathToFile = "src\\trying_db2\\tableInformation.txt"; // some other word?
		
		// connect to DB
		Connection connection = ConnectToDatabase.GetConnection();
		try 
		{	
			File fileObj = new File(relativePathToFile); // check if it exist
			// if it doesn't, return error message through 'return' command
			Scanner fileReader = new Scanner(fileObj);
			boolean itExists = true;
			String currentQuery = "";
			// taking sql code from file in form and executing those 
			// statements if those tables they interact with do not exist
			while (fileReader.hasNextLine()) 
			{
				String data = fileReader.nextLine();
				if (data.contains(codeWord))
				{
					if (!itExists) DatabaseFunction.statementExecuteUpdate(connection, currentQuery);
					String[] resultOfSplit = data.split(" ", 2); // in theory, it is just two parts
					// check for mistakes here
					itExists = DatabaseFunction.CheckTableForExistence(resultOfSplit[1], connection);
					currentQuery = "";
				} else
					if (!itExists) currentQuery += data;
				// check for mistakes
			}
			if (!itExists) 
				DatabaseFunction.statementExecuteUpdate(connection, currentQuery);
			fileReader.close();
			System.out.println("Tables exist now"); //
		}
		catch (FileNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
	
}
