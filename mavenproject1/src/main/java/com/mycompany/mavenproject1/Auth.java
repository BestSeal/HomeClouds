/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import database.CheckPerson;
import database.ConnectToDatabase;
import database.DatabaseINSERT;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author docuc
 */
@WebServlet(name = "Auth", urlPatterns = {"/auth"})
public class Auth extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException {
        try {
            getServletContext().getRequestDispatcher("/WEB-INF/auth/login_page.jsp").forward(request, response); 
        } catch (Exception e) {
        }
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        try {
            System.out.println("login");
            if (CheckPerson.CheckPersonForExistence(ConnectToDatabase.GetConnection(), login, pass) != 0)
            {
                System.out.println(login + "logged in");
                request.getSession().setAttribute("pass", pass);
                request.getSession().setAttribute("login", login);
                
                getServletContext().getRequestDispatcher("/WEB-INF/p/user_page.jsp").forward(request, response);
                
            }
            else 
            {
                request.setAttribute("loginError", "wrong login/pass");
                getServletContext().getRequestDispatcher("/WEB-INF/auth").forward(request, response);
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
