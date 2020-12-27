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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author docuc
 */
@WebServlet(name = "Home", urlPatterns = {"/index.html"})
public class Home extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie nyamCookie = new Cookie("login", "docu");
        nyamCookie.setMaxAge(10 * 60);          
        response.addCookie(nyamCookie);
        nyamCookie = new Cookie("path", "docu");
        nyamCookie.setMaxAge(10 * 60);          
        response.addCookie(nyamCookie);
        getServletContext().getRequestDispatcher("/WEB-INF/index.html").forward(request, response);    
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    getServletContext().getRequestDispatcher("/WEB-INF/index.html").forward(request, response);   
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
