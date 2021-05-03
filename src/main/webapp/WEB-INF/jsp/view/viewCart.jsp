<%-- 
    Document   : cart
    Created on : 2021年5月2日, 下午05:45:58
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart In-progress</title>
    </head>
    <body>
        <a href="<c:url value="/cart/empty" />">Empty Cart</a>
        <h1>View Cart</h1>
        <a href="<c:url value="/" />">Product List</a><br /><br />
        <%! @SuppressWarnings("unchecked") %>
        <c:set var="products" value="${products}" />
        <c:set var="cart" value="${cart}" />
        
        <c:choose>
            <c:when test="${empty cart}">
                Your cart is empty
            </c:when>
            <c:otherwise>
                cart = ${cart}
                <ul>
                <c:forEach var="id" items="${cart}">
                    
                    <li>itemID=quantity | ${id}</li>
                    <!--<li>${products.id} (qty: ${cart.id})</li>-->
                </c:forEach>
                </ul>
            </c:otherwise>
        </c:choose>

    </body>
</html>
