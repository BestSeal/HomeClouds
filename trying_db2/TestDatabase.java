package trying_db2;

import java.sql.SQLException; // possibly needed
//is it org.postgresql.util.PSQLException
import java.util.List;
import java.io.FileNotFoundException;
import java.sql.Connection;

// testing various functions. I delete most experiments, but some are yet here



public class TestDatabase 
{
	public static void main(String[] args) 
	{
		try 
		{
			// need to check deletion of all person info
			
			Connection connection = ConnectToDatabase.getConnection();
			//DatabaseTables.dropAllTables(connection);
			//DatabaseTables.createAllTables(connection);
			
			//DatabaseIO.insertPerson(connection, "regular user", "Zhenya", "zelassin@gmail.com",
			//		"evgeney", "qwertyuiop");
			//DatabaseIO.insertFile(connection, "/evgeney/testing_file", "all", "evgeney");
			//DatabaseIO.insertShare(connection, "/evgeney/testing_file", "evgeney", "Link");
			//DatabaseIO.insertShare(connection, "/evgeney/testing_file", "evgeney", "Link2");
			//DatabaseFunction.deleteLink(connection, "evgeney", "Link");
			//for (String str : DatabaseIO.sharedSelect(connection, "evgeney"))
			//	System.out.println(str);
			String path = DatabaseIO.getPath(connection, "Link2");
			System.out.println(path);
			path = DatabaseIO.getPath(connection, "Link");
			System.out.println(path);
			connection.close(); connection = null;
			
		}
		catch (Exception e)// 
		{
			e.printStackTrace();
		}
        

		// just function for simplicity. Should be thrown out when included in bigger project
		// or included in Test Class

	}
}

/*
//e.printStackTrace();
//System.out.println(e.getLocalizedMessage()); // identical
//System.out.println(e.getMessage()); // identical
// e.notify() - what is it
*/

