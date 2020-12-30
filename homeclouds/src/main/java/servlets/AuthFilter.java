package servlets;

import support.User;
import javax.servlet.*;
import javax.servlet.Filter;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import static java.util.Objects.nonNull;
import javax.servlet.http.*;
import java.util.logging.Logger;
import cloudDatabase.*;
import support.User.*;
import java.util.logging.FileHandler;
import java.util.logging.ConsoleHandler;
import support.MmmCookie;
import support.buttonsHandler;
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
    System.out.println("----filter----");   
    String cookieLogin = MmmCookie.getInfoFromCookie(req, "login");
    try {
            if (nonNull(req.getSession().getAttribute("login")) && nonNull(cookieLogin)){
                 String sessionLogin = req.getSession().getAttribute("login").toString();
                System.out.println(cookieLogin + " " + sessionLogin);
                if (cookieLogin.equals(sessionLogin))
                {
                    System.out.println(sessionLogin + " " + "tries to open user_page");
                    filterChain.doFilter(req, res);    
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }       
    System.out.println("well, woops");
    res.sendRedirect("../");
    //req.getRequestDispatcher("WEB-INF/auth/login_page.jsp").forward(request, response);
    System.out.println("----filter done----");  
    }
 
}
