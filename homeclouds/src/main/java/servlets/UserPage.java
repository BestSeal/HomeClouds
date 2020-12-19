/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sun.security.ssl.KAKeyDerivation;
import static java.util.Objects.nonNull;
import support.FileTree;
import support.buttonsHandler;

/**
 *
 * @author docuc
 */
@WebServlet(name = "UserPage", urlPatterns = {"/user/*"})
public class UserPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {          
            System.out.println("----user page get----");

            System.out.println("----user page get done----");
            getServletContext().getRequestDispatcher("/WEB-INF/user/user_page.jsp").forward(request, response); 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            System.out.println("----user page post----");
            String login = request.getSession().getAttribute("login").toString(); //may fail if session == null or login -- null
            String path = login;
            try {
                path = buttonsHandler.parseButtons(request, "path");
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            
            if (path != login)
            {
                request.getSession().setAttribute("path",  request.getSession().getAttribute("path").toString() + "\\" +  path);
                System.out.println(request.getSession().getAttribute("path").toString());
            }
                
            request.setAttribute("files",FileTree.ExploreDirectory(request.getSession().getAttribute("path").toString()));
            System.out.println("path: " + request.getSession().getAttribute("path").toString());
            System.out.println("----user page post done----");
            getServletContext().getRequestDispatcher("/WEB-INF/user/user_page.jsp").forward(request, response); 
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
