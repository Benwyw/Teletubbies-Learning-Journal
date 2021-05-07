<!DOCTYPE html>
<html>
    <head>
        <title>Customer Support</title>
    </head>
    <body>
        <security:authorize var="isAdmin" access="hasAnyRole('ADMIN')"/>
        <security:authorize var="isUser" access="hasAnyRole('USER')"/>
        
        <c:choose>
            <c:when test="${isAdmin or isUser}">
            </c:when>
            <c:otherwise>  
                <a href="<c:url value="/login" />">Login</a><br />
            </c:otherwise>
        </c:choose>


        <security:authorize access="hasRole('ADMIN') or hasRole('USER')">
            <c:url var="logoutUrl" value="/logout"/>
            <form action="${logoutUrl}" method="post">
                <input type="submit" value="Log out" />
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </security:authorize>
        <br/>
        <security:authorize access="hasRole('USER')">
            <a href="<c:url value="/cart" />">Shopping Cart</a><br/>
        </security:authorize>
        <br/>
        
        <c:set var="oh" value="${orderhistory}" />

        <c:choose>
            <c:when test="${fn:length(orderhistory) == 0}">
                <i>You have no order history in the system.</i>
            </c:when>
            <c:otherwise>
                <c:forEach var="history" items="${oh}">
                        <li>${history.item_name} (qty: ${history.quantity}) ${history.datetime}</li>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </body>
</html>