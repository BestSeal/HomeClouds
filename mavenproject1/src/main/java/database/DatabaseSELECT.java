package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

// manual SELECT - not useful

public class DatabaseSELECT 
{
	public static String SimpleSelect(Connection connection, String SELECTQuery) throws SQLException 
	// pretty dumb, since output is not customized, not simplified for that purpose   
	{
		// add SELECTQuery from String[] args
		String columnValue = "";
		// executing SELECTQuery and writing results in ResultSet
		ResultSet rs = DatabaseFunction.statementExecuteQuery(connection, SELECTQuery);
		// Cannot invoke "java.sql.ResultSet.getMetaData()" because "rs" is null
		// results metadata, like column names and such
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		// writing out SELECT results
		while (rs.next())
		{
			//System.out.print(rs.getString(0)); // 
			for (int i = 1; i <= columnsNumber; i++) 
			{
				columnValue += rs.getString(i);
				columnValue +=" (" + rsmd.getColumnName(i) + ")" + "\n";
			}
		}
		rs.close();
		//connection.close();
		// rsmd doesn't have 'close'
		// check it for "org.postgresql.util.PSQLException: ������ �� ������ �����������." in the future? 
		return columnValue;
	}
}
