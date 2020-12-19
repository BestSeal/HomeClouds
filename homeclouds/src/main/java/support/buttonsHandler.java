/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package support;

import java.util.Map;
import static java.util.Objects.nonNull;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author docuc
 */
public class buttonsHandler {
    public static String parseButtons(HttpServletRequest request, String parameter)
    {
        for (Map.Entry<String, String[]> entry: request.getParameterMap().entrySet())
        {
            for (String st: entry.getValue())
                if (nonNull(st))
                    return st;               
        }      
    return null;
    }
    
}
