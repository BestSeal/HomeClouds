package com.mycompany.mavenproject1;

import support.User;
import javax.servlet.*;
import javax.servlet.Filter;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import static java.util.Objects.nonNull;
import javax.servlet.http.*;
import trying_db2.*;
import support.User.*;

/**
 *
 * @author Ilya G
 */
@WebFilter(urlPatterns = "/user/*")
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
      
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;
    HttpSession session =  req.getSession();
    System.out.println("filter");
        try {
            if (nonNull(session) && nonNull(session.getAttribute("login")) && nonNull(session.getAttribute("pass"))){
                String login = (String) session.getAttribute("login");
                String pass = (String) session.getAttribute("pass");
                System.out.println(login);
                if (User.validadeUser(login, pass))
                {
                    System.out.println(login + " in the session");
                    req.getRequestDispatcher("/WEB-INF/user/user_page.jsp").forward(request, response); 
                    return;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    System.out.println((String)req.getParameter("login") + " " + (String) req.getParameter("pass"));
    if (User.validadeUser(req.getParameter("login"), req.getParameter("pass")))
    {
        
        System.out.println(req.getParameter("login") + " in the request");
        req.getSession().setAttribute("login", req.getParameter("login"));
        req.getSession().setAttribute("pass", req.getParameter("pass"));
        req.getRequestDispatcher("/WEB-INF/user/user_page.jsp").forward(request, response); 
        return;
    }
    System.out.println("well, woops");
    req.setAttribute("loginError", "You are not signed in");
    req.getRequestDispatcher("WEB-INF/auth/login_page.jsp").forward(req, res);
    
    }
}
