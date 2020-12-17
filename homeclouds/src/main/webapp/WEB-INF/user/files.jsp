<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="support.*" %>
<div class = "files">
    <p>Файлы:</p><b>
    <%
        String login = (String)request.getSession().getAttribute("login");
        out.println(FileTree.GetFiles(login));
        out.println((String) request.getSession().getAttribute("path"));
        out.println(FileTree.ExploreDirectory(login));
    %>
    <p>Загрузка файла:</p>
</div>
