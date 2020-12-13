/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.*;

/**
 *
 * @author docuc
 */
@WebServlet(urlPatterns = {"/register"})
public class Register extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException {
        try {
            getServletContext().getRequestDispatcher("/WEB-INF/register/register_page.jsp").forward(request, response); 
        } catch (Exception e) {
        }
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("password");
        String email = request.getParameter("emal");
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        try {
            System.out.println("register");
            if (CheckPerson.CheckPersonForExistence(ConnectToDatabase.GetConnection(), login, pass) != 0)
            {
                String error = "Такой пользователь уже зарегистрирован. Попробуйте авторизоваться.";
                request.setAttribute("registerError", error);  
                System.out.println("user exists");
                getServletContext().getRequestDispatcher("/WEB-INF/register/register_page.jsp").forward(request, response);
            }
            else
            {
                if ("1234".equals(code))
                    DatabaseINSERT.InsertPerson(ConnectToDatabase.GetConnection(), "admin", name, email, login, pass);
                else
                    DatabaseINSERT.InsertPerson(ConnectToDatabase.GetConnection(), "standard user", name, email, login, pass);
                System.out.println("new user " + login);
                getServletContext().getRequestDispatcher("/WEB-INF/auth/login_page.jsp").forward(request, response);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
