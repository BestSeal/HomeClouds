package trying_db2;

import java.sql.SQLException; // possibly needed
import java.sql.Connection;

// is it org.postgresql.util.PSQLException
public class TestDatabase 
{
	public static void main(String[] args) 
	{
		// just function for simplicity. Should be thrown out when included in bigger project
		// or included in Test Class
		try 
		{
			CreateTables.CreateAllTables();
			int i = CheckPerson.CheckPersonForExistence("guest2", "augustine");
			System.out.println("guest2 exist in id = " + i);
			// JUST REMOVE COMMENTS, BUT INSERTING SAME THING TWICE WILL LEAD TO ERRORS
			// SO PUT THEM BACK AGAIN AFTERWARDS
			
			//DatabaseINSERT.InsertPerson("admin", "ADMIN", "fSgerds@gmail.com", "Adomin", "complicatedobviously");
			//DatabaseINSERT.InsertPerson("regular user", "alexander", "fesfrrge@mail.ru", "gsreht", "querty");
			//DatabaseINSERT.InsertLogEntry("login", "user admin logged in", "Adomin", "");
			//DatabaseINSERT.InsertLogEntry("unknown", "", "gsreht", "");
			//DatabaseINSERT.InsertFile("/evgeney/", "checking_file", "read", "Adomin");
			//DatabaseINSERT.InsertFile("/evgeney/", "second", "write", "gsreht");
			String resultofss = DatabaseSELECT.SimpleSelect("SELECT * FROM standard_file");
			System.out.println(resultofss);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			//e.getErrorCode( );
			//e.getMessage( );   			////
			//e.getSQLState( );
		}
	}
	
}