<%-- 
    Document   : login
    Created on : 2021年4月17日, 上午02:16:38
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
    <h1>Login</h1> 
    <form action="login" method='POST'>
        User: <input type='text' name='username'><br />
        Password: <input type='password' name='password' /><br />
        Remember Me: <input type="checkbox" name="remember-me" /><br />
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input name="submit" type="submit" value="Log In" /><br />
    </form>
    </body>
</html>
