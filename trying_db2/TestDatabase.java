package trying_db2;

import java.sql.SQLException; // possibly needed
<<<<<<< HEAD
import java.util.List;
=======
//is it org.postgresql.util.PSQLException
import java.util.List;
import java.io.FileNotFoundException;
>>>>>>> db-(alexander)
import java.sql.Connection;

// testing various functions. I delete most experiments, but some are yet here



public class TestDatabase 
{
	public static void main(String[] args) 
	{
		try 
		{
<<<<<<< HEAD
			Connection connection = ConnectToDatabase.GetConnection();

			/*
			String query = "SELECT * FROM standard_person";
			List<String> people = DatabaseIO.simpleSelect(connection, query);
			System.out.println(people);
			
			query = "SELECT * FROM standard_file";
			List<String> files = DatabaseIO.simpleSelect(connection, query);
			System.out.println(files);
			*/
			
			String login, password; int id;
			
			login = "Adomin"; password = "complicatedobviously";
			id = CheckPerson.CheckPersonForExistence(connection, login, password);
			if (id == 0)
				System.out.println("Person " + login + " not found");
			else
			{
				System.out.println("Info about " + login);
				for (String el : DatabaseIO.personSelect(connection, id))
					System.out.println(el);
			}
			
			System.out.println("\n");
			login = "Modulator"; password = "qwerty1234";
			id = CheckPerson.CheckPersonForExistence(connection, login, password);
			if (id == 0)
				System.out.println("Person " + login + " not found");
			else System.out.println(DatabaseIO.personSelect(connection, id));
			
		}
		catch (SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
        

=======
			// need to check deletion of all person info
			
			Connection connection = ConnectToDatabase.getConnection();
			//DatabaseIO.insertPerson(connection, "regular user", "georgiy", "wefdsc@mail.ru", "anotherOne", "sefdsc");
			//DatabaseIO.insertPerson(connection, "regular user", "Sergiy", "Radonegi@mail.ru", "wedfwefvgrds", "sefdsc");
			/*
			String login = "delete_it_Us";
			System.out.println("Info about " + login);
			for (String el : DatabaseIO.personSelect(connection, login))
				System.out.println(el);
			for (String elp : DatabaseIO.personSelectwoPass(connection, login))
				System.out.println(elp);
			*/
			System.out.println("");
			for (String logins : DatabaseIO.getAllLogins(connection))
				System.out.println(logins);
			
			
			//DatabaseTables.dropAllTables(connection);
			//DatabaseTables.createAllTables(connection);
			//DatabaseIO.insertPerson(connection, "regular user", "user_for_deletion",	"sfe@mail.ru", "delete_it_Useee", "querty");
			//DatabaseIO.insertFile(connection, "/alexander/", "file for deletion", "read", "delete_it_Us");
			//DatabaseFunction.deletePerson(connection, "delete_it_Usee");
			/*
			String query = "SELECT * FROM standard_person";
			List<String> people = DatabaseIO.simpleSelect(connection, query);
			System.out.println(people);
			
			query = "SELECT * FROM standard_file";
			List<String> files = DatabaseIO.simpleSelect(connection, query);
			System.out.println(files);
			
			String login, password; int id;
			
			login = "Adomin"; password = "complicatedobviously";
			id = DatabaseFunction.checkPersonForExistence(connection, login, password);
			if (id == 0)
				System.out.println("Person " + login + " not found");
			else
			{
				System.out.println("Info about " + login);
				for (String el : DatabaseIO.personSelect(connection, id))
					System.out.println(el);
			}
			
			System.out.println(" ");
			login = "Modulator"; password = "qwerty1234";
			id = DatabaseFunction.checkPersonForExistence(connection, login, password);
			if (id == 0)
				System.out.println("Person " + login + " not found");
			else System.out.println(DatabaseIO.personSelect(connection, id));
			*/
			connection.close(); connection = null;
			
		}
		// FileNotFoundException e
		catch (SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
        

>>>>>>> db-(alexander)
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