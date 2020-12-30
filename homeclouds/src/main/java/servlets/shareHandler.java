/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.sql.Connection;
import java.io.IOException;
import java.io.PrintWriter;
import static java.util.Objects.nonNull;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jdk.internal.net.http.HttpRequestImpl;
import support.*;
import cloudDatabase.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.ServletContext;
/**
 *
 * @author docuc
 */
@WebServlet(urlPatterns = {"/share/"})
public class shareHandler extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {      
        
        String login = getParameterString(request, "login");
        String file = getParameterString(request, "file");
        String path = getParameterString(request, "path");
        String action = getParameterString(request, "action");
        String key = getParameterString(request, "key");
        String domain = "localhost:8080"; // изменить перед заливом на пай
        System.out.println(login + " " + file + " " + action + " " + key + " " + path);
        if (action.equals("send"))
        {
            try {
                Connection connection = ConnectToDatabase.getConnection();
                String link = DatabaseIO.selectLinkByPath(connection, path + File.separator + file, login);
                if (nonNull(link))
                {
                    response.getWriter().write(link);
                    System.out.println("existed link:" + link);
                    connection.close();
                    return;
                }
                
  
                link = domain + linkGenerator.generateLink(login, file);
                DatabaseIO.insertShare(connection, path + File.separator + file, login, link);            

                connection.close();
                System.out.println("new link:" + link);
                response.getWriter().write(link);  
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (action.equals("get") && nonNull(key))
        {
            System.out.println("trying to download");
            try {
                Connection connection = ConnectToDatabase.getConnection();
                //String link = DatabaseIO.getPath(connection, )
                String link = linkGenerator.collectLink(login, key, file, domain);
                System.out.println(link);
                
                path = DatabaseIO.getPath(connection, link);
                System.out.println(path);
                connection.close();
                                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        if (action.equals("download") && key == null)
        {
            path += File.separator + file;
        }
                if (nonNull(path))
        {
                    path = AbsPath.path + path;
                    System.out.println(path);
                    File downloadFile = new File(path);
                    FileInputStream inStream = new FileInputStream(downloadFile);
                    ServletContext context = getServletContext();
                    String mimeType = context.getMimeType(path);
                    if (mimeType == null) {        
                        mimeType = "application/octet-stream";
                    }
                    System.out.println("MIME type: " + mimeType);
                    response.setContentType(mimeType);
                    response.setContentLength((int) downloadFile.length());
                    response.setHeader("Content-Disposition", "attachment; filename=" + downloadFile.getName());
                    OutputStream outStream = response.getOutputStream();
                     byte[] buffer = new byte[4096];
                    int bytesRead = -1;
         
               while ((bytesRead = inStream.read(buffer)) != -1) {
                        outStream.write(buffer, 0, bytesRead);
                }
                    
                inStream.close();
               outStream.close();
        }             
            else
               response.getWriter().write("wrong link");  
                    

        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
    private String getParameterString(HttpServletRequest req, String param)
    {
        System.out.println(param);
        if (nonNull(req.getParameter(param)))
        {
            System.out.println(req.getParameter(param).toString());
            return req.getParameter(param).toString();
        }
        return null;
    }
    
    
}
