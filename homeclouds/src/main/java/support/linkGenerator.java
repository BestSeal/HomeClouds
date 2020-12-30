/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package support;
import java.util.UUID;

/**
 *
 * @author docuc
 */
public class linkGenerator {
    
    public static String generateLink(String login, String file)
    {
        StringBuilder link = new StringBuilder();
        UUID key = UUID.randomUUID(); 
        return link.append("/share/?action=get&login=").append(login).append("&file=").append(file).append("&key=").append(key.toString()).toString();
    }
    
    public static String collectLink(String login, String key, String file, String domain)
    {
        return domain + "/share/?action=get&login=" + login + "&file=" + file + "&key=" + key;
    }
}
