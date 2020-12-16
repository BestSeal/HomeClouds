/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import trying_db2.*;

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
            int userId = DatabaseFunction.getPersonId(ConnectToDatabase.getConnection(), login);
            if (userId != 0)
            {
                List<String> userInfo = DatabaseIO.personSelect(ConnectToDatabase.getConnection(), userId);
                String userPass = userInfo.get(5);
                System.out.println(pass + " " + userPass);
                if (userPass.equals(pass))
                {
                    System.out.println(login + " logged in");
                    request.getSession().setAttribute("pass", pass);
                    request.getSession().setAttribute("login", login);
                    request.getSession().setAttribute("userId", userId);
                    request.getSession().setAttribute("name", userInfo.get(2));
                    
                    getServletContext().getRequestDispatcher("/WEB-INF/user/user_page.jsp").forward(request, response); 
                }
            }
            else
            {
                request.setAttribute("loginError", "wrong login");
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
