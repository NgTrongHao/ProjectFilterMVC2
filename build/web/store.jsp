<%-- 
    Document   : store
    Created on : Oct 30, 2023, 6:20:04 PM
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
        <h1>Book Store</h1>
        <form action="cart">
            <c:set var="store" value="${sessionScope.STORE}"/>
            Choose your book <select name="ddlBook">
                <c:if test="${not empty store}">
                    <c:forEach items="${store}" var="productDTO">
                        <option>${productDTO.productName}</option>
                    </c:forEach>
                </c:if>
            </select>
            <input type="text" name="bookQuantity" value="" /><br/>
            <input type="submit" value="Add Book to Your Cart" name="btAction" />
            <input type="submit" value="View Cart" name="btAction" />
        </form>
        <a href="loginPage">Click here to return to login page</a>
    </body>
</html>
