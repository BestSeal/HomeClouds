/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import cloudDatabase.ConnectToDatabase;
import cloudDatabase.DatabaseFunction;
import cloudDatabase.DatabaseIO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import static java.util.Objects.nonNull;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jdk.internal.net.http.HttpRequestImpl;
import support.*;
import cloudDatabase.*;
import java.awt.desktop.FilesEvent;
import java.io.File;
import support.AbsPath;
/**
 *
 * @author docuc
 */
@WebServlet(urlPatterns = {"/delete/*"})
public class deleteHandler extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {      

        System.out.println("Lets delete!");
        String action = getParameterString(request, "action"); 
        String path = getParameterString(request, "path"); 
        String login = getParameterString(request, "login");
        String file = getParameterString(request, "file");
        try {
            Connection connection = ConnectToDatabase.getConnection();
            System.out.println(path + File.separator + file);
            DatabaseFunction.deleteLink(connection, login, path + File.separator + file);
            File temp = new File(AbsPath.path + path + File.separator + file);
            System.out.println(AbsPath.path + path + File.separator + file);
            if (temp.delete())
                System.out.println("deleted");  
            else
                System.out.println("Something went wrong");
        } catch (Exception e) {
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

    private Object File(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
