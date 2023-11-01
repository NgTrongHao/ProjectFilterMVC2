<%-- 
    Document   : cart
    Created on : Oct 30, 2023, 7:30:39 PM
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
        <h1>Book Store - Cart</h1>
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:if test="${not empty cart}">
            <c:set var="items" value="${cart.items}"/>
            <c:if test="${not empty items}">
                <form action="cart">
                    <table border="1" cellpadding="2">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Name</th>
                                <th>Quantity</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${items}" var="item" varStatus="counter">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${item.key}</td>
                                    <td>${item.value}</td>
                                    <td>
                                        <input type="checkbox" name="checkItem" value="${item.key}" />
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="3">
                                    <a href="storeView">Add more books to cart</a>
                                </td>
                                <td>
                                    <input type="submit" value="Remove Selected Items" name="btAction" />
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <input type="submit" value="Check-out" name="btAction" />
                </form>
            </c:if>
        </c:if>
        <c:if test="${empty cart.items}">
                <h2>No items in cart</h2>
                <a href="storeView">Click here to return BookStore</a>
            </c:if>
    </body>
</html>
