/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package support;

import static java.util.Objects.nonNull;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author docuc
 */
public class MmmCookie {
    public static String getInfoFromCookie(HttpServletRequest req, String parameter) {
        Cookie[] cookies = req.getCookies();
        if (nonNull(cookies))
            {
                for (Cookie cookie : cookies)
                {
                    if (cookie.getName().equals(parameter))
                    {
                        System.out.println("Cookied login:" + " " + cookie.getValue());
                        return cookie.getValue();
                    }                        
                }  
            }
        return null;
    }
    
    public static String setInfoToCookie(HttpServletRequest req, String parameter)
    {
        return null;
    }

}
