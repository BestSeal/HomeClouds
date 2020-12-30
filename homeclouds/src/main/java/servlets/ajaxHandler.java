/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import static java.util.Objects.nonNull;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jdk.internal.net.http.HttpRequestImpl;
import support.*;
/**
 *
 * @author docuc
 */
@WebServlet(urlPatterns = {"/hop/*"})
public class ajaxHandler extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {      

        String action = getParameterString(request, "action"); 
        String path = getParameterString(request, "path"); 
        
        if (nonNull(action))           
        {   
            switch (action)
            {
                case ("open"):
                    System.out.println("-- open --");
                    response.getWriter().write(FileTree.ExploreDirectory(path));
                    break;                   
                case ("send"):
                    System.out.println("-- send --");
                    
                    break;
                case ("download"):
                    System.out.println("-- download --");
                    
                    break;
                case("delete"):
                    System.out.println("-- delete --");
                    
                    break;
            }
        }
        else
        {
            System.out.println("empty action accured");
            response.getWriter().write("empty action accured");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
    private String getParameterString(HttpServletRequest req, String param)
    {
        System.out.println(param);
        if (nonNull(req.getParameter(param)))
        {
            System.out.println(req.getParameter(param).toString());
            return req.getParameter(param).toString();
        }
        return null;
    }
    
}
