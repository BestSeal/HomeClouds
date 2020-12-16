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

/**
 *
 * @author docuc
 */
@WebServlet(name = "UserPage", urlPatterns = {"/user/user_page.jsp"})
public class UserPage extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            getServletContext().getRequestDispatcher("/WEB-INF/user/user_page.jsp").forward(request, response); 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            getServletContext().getRequestDispatcher("/WEB-INF/user/user_page.jsp").forward(request, response); 
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
