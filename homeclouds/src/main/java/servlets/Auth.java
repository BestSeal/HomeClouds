/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import support.FileTree;
import trying_db2.*;

/**
 *
 * @author docuc
 */
@WebServlet(name = "Auth", urlPatterns = {"/auth/*"})
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
        System.out.println("----auth----");
        try {
            int userId = DatabaseFunction.getPersonId(ConnectToDatabase.getConnection(), login);
            if (userId != 0)
            {
                List<String> userInfo = DatabaseIO.personSelect(ConnectToDatabase.getConnection(), userId);
                String userPass = userInfo.get(5);
                System.out.println(pass + " " + userPass);
                if (userPass.equals(pass))
                {
                    System.out.println(login + " was logged in");
                    request.getSession().setAttribute("login", login);
                    request.getSession().setAttribute("path", login);
                    request.getSession().setAttribute("path", login);
                    request.getSession().setAttribute("files", FileTree.ExploreDirectory(login));
                    request.getSession().setMaxInactiveInterval(10 * 60);
                    System.out.println(request.getSession().getId());
                    Cookie nyamCookie = new Cookie("login", login);
                    nyamCookie.setMaxAge(10 * 60);          
                    System.out.println("----auth done----");
                    response.addCookie(nyamCookie);
                    response.sendRedirect("../user/");
                    return;
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
        System.out.println("----auth failed----");
    }
    
    
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
