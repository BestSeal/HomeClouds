package support;

import java.util.List;
import trying_db2.*;

public class UsersList {
    //work in progress!
    public static StringBuilder UsersInfo()
    {
        StringBuilder outInfo = new StringBuilder("<table>\n<tr>\n" +
            "    <th>id</th>\n" +
            "    <th>access_level</th>\n" +
            "    <th>name</th>\n" +
            "    <th>email</th>\n" +
            "    <th>login</th>\n" +
            "    <th>pass</th>\n" +
            "   </tr>");       
        
        try {
            List<String> logins = DatabaseIO.getAllLogins(ConnectToDatabase.getConnection());
            for (String login: logins)
            {
                outInfo.append("<tr>\n");
                List<String> userInfo = DatabaseIO.personSelect(ConnectToDatabase.getConnection(), login);
                for (String str: userInfo)
                {
                    outInfo.append("<th>" + str + "</th>\n");
                }
                outInfo.append("</tr>\n");
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return outInfo.append("</table>\n");
    }
    
}
