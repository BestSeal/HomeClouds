package trying_db2;

import java.sql.SQLException; // possibly needed
import java.util.List;
import java.sql.Connection;

// is it org.postgresql.util.PSQLException
public class TestDatabase 
{
	public static void main(String[] args) 
	{
		
		try 
		{
			Connection connection = ConnectToDatabase.GetConnection();

			String query = "SELECT * FROM standard_person";
			List<String> people = DatabaseSELECT.simpleSelect(connection, query);
			System.out.println(people);
			
			query = "SELECT * FROM standard_file";
			List<String> files = DatabaseSELECT.simpleSelect(connection, query);
			System.out.println(files);
		}
		catch (SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
        

		// just function for simplicity. Should be thrown out when included in bigger project
		// or included in Test Class
		
		//Connection con = ConnectToDatabase.GetConnection();
		//System.out.println(con.toString());
		//closeConnection(con);
		//if (con == null)
		//System.out.println("con = null");
		//if (con.isClosed())
		//System.out.println("con is closed");
		//System.out.println(con.toString());
		//con.close();
		//con = null;
		//if (con == null)
		//System.out.println("con = null after");
		//System.out.println(con.toString());
		//CreateTables.CreateAllTables(con);
		//int i = CheckPerson.CheckPersonForExistence("guest2", "augustine");
		//System.out.println("guest2 exist in id = " + i);
		// JUST REMOVE COMMENTS, BUT INSERTING SAME THING TWICE WILL LEAD TO ERRORS
		// SO PUT THEM BACK AGAIN AFTERWARDS
		// what?
		//DatabaseINSERT.InsertPerson("admin", "ADMIN", "fSgerds@gmail.com", "Adomin", "complicatedobviously");
		//DatabaseINSERT.InsertPerson("regular user", "alexander", "fesfrrge@mail.ru", "gsreht", "querty");
		//DatabaseINSERT.InsertLogEntry("login", "user admin logged in", "Adomin", "");
		//DatabaseINSERT.InsertLogEntry("unknown", "", "gsreht", "");
		//DatabaseINSERT.InsertFile("/evgeney/", "checking_file", "read", "Adomin");
		//DatabaseINSERT.InsertFile("/evgeney/", "second", "write", "gsreht");
		//String resultofss = DatabaseSELECT.SimpleSelect("SELECT * FROM standard_file");
		//System.out.println(resultofss);
		// check if connection-sharing gives two connections, or one
			
		/*
		}
		catch (Exception e)
		{
			e.printStackTrace();
			//e.getErrorCode( );
			//e.getMessage( );   			////
			//e.getSQLState( );
		}
		*/
	}
}

/*
//e.printStackTrace();
//System.out.println(e.getLocalizedMessage()); // identical
//System.out.println(e.getMessage()); // identical
// e.notify() - what is it
*/