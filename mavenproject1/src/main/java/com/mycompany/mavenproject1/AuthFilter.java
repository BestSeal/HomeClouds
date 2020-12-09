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


/**
 *
 * @author Ilya G
 */
@WebFilter(urlPatterns = "/*")
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
        int status = (int) session.getAttribute("role");
        redirect(req, res, status);
    }

        redirect(req, res, 1);
    }
       
    private void redirect(HttpServletRequest req, HttpServletResponse res, int status) throws ServletException, IOException
    {
        if (status == 1)
        {
            req.getRequestDispatcher("/WEB-INF/view/login_page.jsp").forward(req, res);
        }
        else 
        {
        }
    }

}
