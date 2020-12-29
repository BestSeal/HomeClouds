/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.File;
import java.sql.Connection;
import java.io.IOException;
import java.io.PrintWriter;
import static java.util.Objects.nonNull;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import support.*;
import cloudDatabase.*;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
/**
 *
 * @author docuc
 */
@WebServlet("/upload/") 
@MultipartConfig  
public class uploadHandler extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {              
            String savePath = AbsPath.path + File.separator + MmmCookie.getInfoFromCookie(request, "path");
            //String descriprion = request.getParameter("description");
            Part filePart = request.getPart("file");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            InputStream fileContent = filePart.getInputStream();
            File file = new File(savePath, fileName);
            Files.copy(fileContent, file.toPath());
            fileContent.close();
            response.getWriter().write("done");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
}
