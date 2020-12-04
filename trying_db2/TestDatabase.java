package trying_db2;

import java.sql.SQLException;

public class TestDatabase 
{
	public static void main(String[] args) 
	{
		// just function for simplicity. Should be thrown out when included in bigger project
		// or included in Test Class
		try 
		{
			//DropTables.DropAllTables();
			//CreateTables.CreateAllTables();
			int i = CheckPerson.CheckPersonForExistence("guest2", "augustine");
			//System.out.println("guest2 existt in id = " + i);
			//DatabaseINSERT.InsertPerson("admin", "ADMIN", "fSgerds@gmail.com", "Adomin", "complicatedobviously");
			//i = CheckPerson.CheckPersonForExistence("Adomin", "complicatedobviously");
			//System.out.println("Adomin existt in id = " + i);
			//DatabaseINSERT.InsertPerson("regular user", "alexander", "fesfrrge@mail.ru", "gsreht", "querty");
			//DatabaseINSERT.InsertLogEntry("login", "user admin logged in", "Adomin", "");
			//DatabaseINSERT.InsertLogEntry("unknown", "", "gsreht", "");
			//DatabaseINSERT.InsertFile("/evgeney/", "checking_file", "read", "Adomin");
			//DatabaseINSERT.InsertFile("/evgeney/", "second", "write", "gsreht");
			//DatabaseINSERT.InsertAccess("/evgeney/", "checking_file", "gsreht", "write"); // failure
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

	}

}
