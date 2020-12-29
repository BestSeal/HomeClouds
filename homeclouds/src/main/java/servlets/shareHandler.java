/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.sql.Connection;
import java.io.IOException;
import java.io.PrintWriter;
import static java.util.Objects.nonNull;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import support.*;
import cloudDatabase.*;
import java.util.List;
/**
 *
 * @author docuc
 */
@WebServlet(urlPatterns = {"/share/"})
public class shareHandler extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {      
        
        String login = getParameterString(request, "login");
        String file = getParameterString(request, "file");
        String path = getParameterString(request, "path");
        String action = getParameterString(request, "action");
        String key = getParameterString(request, "key");
        System.out.println(login + " " + file + " " + action + " " + key + " " + path);
        if (action.equals("send"))
        {
            try {
                Connection connection = ConnectToDatabase.getConnection();
                String link = DatabaseIO.selectLinkByPath(connection, path, login);
                if (nonNull(link))
                {
                    response.getWriter().write(link);
                    System.out.println("existed link:" + link);
                    connection.close();
                    return;
                }
                
                link = "localhost:8080"; // изменить перед заливом на пай
                link += linkGenerator.generateLink(login, file);
                DatabaseIO.insertShare(connection, path, login, link);
                
                connection.commit();
                connection.close();
                System.out.println("new link:" + link);
                response.getWriter().write(link);  
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
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
