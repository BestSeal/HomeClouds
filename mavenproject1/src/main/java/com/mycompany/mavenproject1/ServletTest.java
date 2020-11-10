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

@WebServlet("/upload")
public class ServletTest extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        
        // create HTML form
        PrintWriter writer = resp.getWriter();
        writer.append("<form method=\"POST\" enctype=\"multipart/form-data\" action=\"fup.cgi\">")
              .append("Загрузить: <input type=\"file\" name=\"upfile\"><br/>")
              .append("<br/>")
              .append("<input type=\"submit\" value=\"Загрузить\">")
              .append("</form>");
   }
}