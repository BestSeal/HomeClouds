<%-- 
    Document   : register_page
    Created on : 13 дек. 2020 г., 11:12:19
    Author     : docuc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
</head>
<body>

    <div class="form">

        <h1>Регистрация</h1><br>
        <form method="post" action="">

            <input type="text" required placeholder="login" name="login"><br>
            <input type="text" required placeholder="acces code" name="code"><br>
            <input type="text" required placeholder="name" name="name"><br>
            <input type="text" required placeholder="email" name="email"><br>
            <input type="password" required placeholder="password" name="pass"><br><br>
            <input class="button" type="submit" value="Зарегестрироваться">
            <p>${registerError}</p>
        </form>      
        
    </div>
</body>
</html>
