package com.mycompany.mavenproject1;

import javax.servlet.*;
import javax.servlet.Filter;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import static java.util.Objects.nonNull;
import javax.servlet.http.*;
import database.*;


/**
 *
 * @author Ilya G
 */
@WebFilter(urlPatterns = "/p/*")
public class AuthFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException{};
    
    @Override
    public void destroy() {
    }
    
    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain filterChain)

            throws IOException, ServletException {
      
    String login = request.getParameter("login");
    String pass = request.getParameter("password"); //we shoud add encryption or hashing as pass is stored in the cookie
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;
    HttpSession session =  req.getSession();
           
    if(nonNull(session) && nonNull(session.getAttribute("login")) && nonNull(session.getAttribute("password"))){
        try {
            if (CheckPerson.CheckPersonForExistence(ConnectToDatabase.GetConnection(), (String) session.getAttribute("login"), (String) session.getAttribute("pass")) != 0)
            {
                redirect(req, res, 1); 
            }
        } catch (Exception e) {
        }
       
    }
    else
        try {                   
             if (CheckPerson.CheckPersonForExistence(ConnectToDatabase.GetConnection(), login, (String) pass) != 0)
            {
                req.getSession().setAttribute("pass", pass);
                req.getSession().setAttribute("login", login);
                
                redirect(req, res, 1);
            }
        }           
        catch (Exception e) {
            
        }


        redirect(req, res, 0);
    }
       
    private void redirect(HttpServletRequest req, HttpServletResponse res, int status) throws ServletException, IOException
    {
        if (status == 1)
        {
            req.getRequestDispatcher("/WEB-INF/p/user_page.jsp").forward(req, res);
        }
        else
        {
            req.getRequestDispatcher("/WEB-INF/auth").forward(req, res);
        }
    }

}
