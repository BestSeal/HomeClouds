<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
     
</head>
<body>

    <div class="form">

        <h1>Вход в систему</h1><br>
        <form method="post" action="">

            <input type="text" required placeholder="login" name="login"><br>
            <input type="password" required placeholder="password" name="pass"><br><br>
            <input class="button" type="submit" value="Войти">
            <p>${loginError}</p>
        </form>      
        
    </div>
</body>
</html>