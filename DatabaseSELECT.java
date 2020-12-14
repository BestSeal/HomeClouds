package trying_db2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData; // ? for rs.getMetaData()
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseSELECT 
{
    public static List<String> simpleSelect(Connection connection, String SELECTQuery) throws SQLException
    {
        List<String> selectList = new ArrayList<String>(); 
  		// executing SELECTQuery and writing results in ResultSet
    	ResultSet rs = DatabaseFunction.statementExecuteQuery(connection, SELECTQuery);
    	
    	int columnsNumber = rs.getMetaData().getColumnCount();    	
    	while (rs.next()) 
    		for (int i = 1; i <= columnsNumber; i++) 
    			selectList.add(rs.getString(i));
    	rs.close(); rs = null;
    	// connection.close(); connection = null;
    	return selectList;
    	// rsmd doesn't have 'close'
    	// check it for "org.postgresql.util.PSQLException: Запрос не вернул результатов." in the future?
    	// possible exception Cannot invoke "java.sql.ResultSet.getMetaData()" because "rs" is null
    	// results metadata, like column names and such
    } 
}
