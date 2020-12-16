/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package support;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import trying_db2.*;
/**
 *
 * @author docuc
 */
public class User {
    
    public static boolean validadeUser(String userLogin, String userPass)
    {
        try {
             List<String> pass = DatabaseIO.personSelect(ConnectToDatabase.getConnection(), userLogin);      
             if (pass.isEmpty())
                 return false;
             if (pass.get(5).equals(userPass))
                 return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
