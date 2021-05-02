<!DOCTYPE html>
<html>
    <head>
        <title>Item List</title>
    </head>
    <body>

        <a href="<c:url value="/login" />">Login</a><br />

        <security:authorize access="hasRole('ADMIN') or hasRole('USER')">
            <c:url var="logoutUrl" value="/logout"/>
            <form action="${logoutUrl}" method="post">
                <input type="submit" value="Log out" />
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </security:authorize>
        
        <security:authorize access="hasRole('USER')">
            <a href="<c:url value="/cart" />">Shopping Cart</a><br/>
        </security:authorize>

        <security:authorize access="hasRole('ADMIN') or hasRole('USER')">
            <a href="<c:url value="/guestbook" />">Guestbook</a><br />
        </security:authorize>
        <h2>Item</h2>
        <security:authorize access="hasRole('ADMIN')">
            <a href="<c:url value="/item/create" />">Create a Item</a><br /><br />
        </security:authorize>

        <c:choose>
            <c:when test="${fn:length(itemDatabase) == 0}">
                <i>There are no item in the system.</i>
            </c:when>
            <c:otherwise>
                <c:forEach items="${itemDatabase}" var="entry">
                    <c:if test="${entry.value.isabailability eq true}">
                        Item ${entry.key}:
                        <a href="<c:url value="/item/view/${entry.key}" />">
                            <c:out value="${entry.value.itemName}" /></a>
                        (price: <c:out value="${entry.value.price}" />)<br />

                        <security:authorize access="hasRole('ADMIN')">
                            [<a href="<c:url value="/item/edit/${entry.key}" />">Edit</a>]
                        </security:authorize>

                        <security:authorize access="hasRole('ADMIN')">
                            [<a href="<c:url value="/itemt/delete/${entry.key}" />">Delete</a>]
                        </security:authorize>
                        <br /> 
                    </c:if>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </body>
</html>