package database;

import java.sql.Connection;
import java.sql.SQLException;

// class for deleting all table information from database

// WARNING: DANGEROUS CLASS
// USING MAIN WILL EMPTY ALL DATA IN ALL TABLES
// is there better way to describe it? 
// some rules for documentation in lab 1?

public class DropTables 
{	
	public static void DropAllTables(Connection connection) throws SQLException
	{
		// DROPping tables doesn't drop sequences, so id =3, 6,... is the result
		// can be automated (find out all tables, for ... Cascade) 
		// 
		String[] tableNamesInDeletionOrder = {"accesses", "standard_file", "standard_log_entry",
				"standard_person", "user_access_levels", "file_access_levels"}; // perhaps outsource it further
		// now that I think about it, it's not necessary to drop everything
		// I can just drop files whose names I put in args
		// I will need to use drop ... cascade, though
		// 		checkforexistance already included anyway
		// not sure how to comment on that
		for (String currentTable : tableNamesInDeletionOrder)
			if (DatabaseFunction.CheckTableForExistence(currentTable, connection))
				DatabaseFunction.statementExecuteUpdate(connection, "DROP TABLE " + currentTable + ";");
		System.out.println("Tables do not exist now");
	}
}
