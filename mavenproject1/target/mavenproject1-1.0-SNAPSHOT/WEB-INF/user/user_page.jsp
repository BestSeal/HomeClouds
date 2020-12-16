<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="support.FileTree" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
    </head>
    <body>
        <h1>${login}</h1>
        <p>${name}</p>
        <p>${userId}</p>
        <%  out.println("WOWO!");
            out.println(FileTree.GetFiles(((String)request.getSession().getAttribute("login"))));%>
    </body>
</html>
