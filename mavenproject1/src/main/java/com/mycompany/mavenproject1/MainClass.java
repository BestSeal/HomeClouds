/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/")
public class MainClass extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // set response headers
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        
        // create HTML form
        PrintWriter writer = response.getWriter();
        writer.append("<!DOCTYPE html>\r\n")
              .append("<html>\r\n")
              .append("        <head>\r\n")
              .append("            <title>Form input</title>\r\n")
              .append("        </head>\r\n")
              .append("        <body>\r\n")
              .append("            <form action=\"welcome\" method=\"POST\">\r\n")
              .append("        <a href='/file1' target='_blank'> Файл1 </a></br>")
              .append("        <a href='/file2' target='_blank'> Файл1 </a></br>")
                
              .append("                <a class='dropdown-toggle' href='/upload' data-toggle='dropdown'>")
              .append("                Загрузить файл")
              .append("                </a></br>")
                
              .append("            </form>\r\n")
              .append("        </body>\r\n")
              .append("</html>\r\n");
        
    }
}