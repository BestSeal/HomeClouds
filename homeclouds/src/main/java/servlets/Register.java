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
            getServletContext().getRequestDispatcher("/WEB-INF/register/register_page.jsp").forward(request, response); 
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
            System.out.println("register");
            if (DatabaseFunction.getPersonId(ConnectToDatabase.getConnection(), login) != 0)
            {
                String error = "Такой пользователь уже зарегистрирован. Попробуйте <a href=\"auth\">авторизоваться.</a>";
                request.setAttribute("registerError", error);  
                System.out.println("user exists");
                getServletContext().getRequestDispatcher("/WEB-INF/register/register_page.jsp").forward(request, response);
            }
            else
            {
                if ("1234".equals(code))
                    DatabaseIO.insertPerson(ConnectToDatabase.getConnection(), "admin", name, email, login, pass);
                else
                    DatabaseIO.insertPerson(ConnectToDatabase.getConnection(), "regular user", name, email, login, pass);
                System.out.println("new user " + login);
                PersonalFolder.NewFolder(login);
                request.setAttribute("loginError", "New user was registered, you can sign in now"); 
                response.sendRedirect("/auth");
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
