package trying_db2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Scanner;

// manual SELECT - not useful

public class DatabaseSELECT 
{
	public static void main(String[] args) 
	{
		// add SELECTQuery from String[] args
		try 
		{
			// connect to database  
			Connection connection = ConnectToDatabase.GetConnection();
			
			Scanner scanner = new Scanner(System.in); 
			String SELECTQuery = scanner.nextLine();
			
			// executing SELECTQuery and writing results in ResultSet
			ResultSet rs = DatabaseFunction.statementExecuteQuery(connection, SELECTQuery);
			
			// results metadata, like column names and such
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			
			// writing out SELECT results
			while (rs.next())
			{
				for (int i = 1; i <= columnsNumber; i++) 
				{
					if (i > 1) System.out.print(",  ");
					String columnValue = rs.getString(i);
					System.out.print(columnValue + " (" + rsmd.getColumnName(i) + ")"); 
					if (i == columnsNumber) System.out.println(".");
				}
			}
			scanner.close();
		}
		catch (Exception e) 
		{
			// check it for "org.postgresql.util.PSQLException: Запрос не вернул результатов." in the future? 
			e.printStackTrace();
		}
	}
}