<%-- 
    Document   : index
    Created on : 2021年4月14日, 上午11:46:46
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Start Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
    <center>
        <table>
            <tr>
                <td colspan = 2><h1>Welcome to Fast Food Ordering System!</h1></td>
            </tr>
            <tr>
                <th>Food</th>
                <th>Drinks</th>
            </tr>
            <tr>
                <td>
                    <ul>
                        <li>Burger</li>
                        <li>Fries</li>
                    </ul>
                </td>
                <td>
                    <ul>
                        <li>Coke</li>
                        <li>Sprite</li>
                    </ul>
                </td>
            </tr>
        </table>
        <img src="https://pok.benwyw.com/doge.gif">
        <c:url var="logoutUrl" value="/logout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Log out" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <h1>Login</h1>
        <form action="login" method='POST'>
            User: <input type='text' name='username'><br/> 
            Password: <input type='password' name='password' /><br/> 
            Remember Me: <input type="checkbox" name="remember-me" /><br/> 
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
            <input name="submit" type="submit" value="Log In" /><br/> 
        </form>
    </center>
</body>
</html>