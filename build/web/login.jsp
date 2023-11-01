<%-- 
    Document   : login
    Created on : Oct 30, 2023, 2:58:41 PM
    Author     : ngtronghao <ngtronghao02@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login Page</h1>
        <form action="loginAction" method="POST">
            Username: <input type="text" name="txtUserName" value="" /><br/>
            Password: <input type="password" name="txtPassword" value="" /><br/>
            <input type="submit" name="btAction" value="Login">
            <input type="reset" value="Reset">
        </form><br/>
        <a href="registerPage">Click here to create new account</a><br/>
        <a href="storeView">Buy Books</a>
    </body>
</html>
