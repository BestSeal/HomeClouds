/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package support;
import java.util.List;
import cloudDatabase.*;
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
