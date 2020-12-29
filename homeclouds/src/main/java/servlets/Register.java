/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cloudDatabase.*;
import java.sql.Connection;
import support.*;

/**
 *
 * @author docuc
 */
//TESTS!!!!!
@WebServlet(urlPatterns = {"/register"})
public class Register extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException {
        try {
            getServletContext().getRequestDispatcher("/WEB-INF/register/index.html").forward(request, response); 
        } catch (Exception e) {
        }
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        String email = request.getParameter("email");
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        try {
            Connection connection = ConnectToDatabase.getConnection();
            System.out.println("------------ register -------------");
            if (DatabaseFunction.getPersonId(connection, login) != 0)
            {
                System.out.println("user exists");
                response.getWriter().write("User exists");
            }
            else
            {
                DatabaseIO.insertPerson(connection, "admin", name, email, login, pass);
                System.out.println("------------ new user " + login + " -------------");
                PersonalFolder.NewFolder(login);
                response.sendRedirect("../index.html");
            } 
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
            
        
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
