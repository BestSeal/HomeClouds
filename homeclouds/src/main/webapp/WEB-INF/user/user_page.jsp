<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="support.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <h1>${login}</h1>
        <jsp:include page="files.jsp"></jsp:include>
    </body>
</html>
