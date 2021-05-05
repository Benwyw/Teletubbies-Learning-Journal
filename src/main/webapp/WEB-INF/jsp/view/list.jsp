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
            <a href="<c:url value="/cart/orderHistory" />">Order history</a>
        </security:authorize>


        <c:choose>
            <c:when test="${isAdmin and isUser}">
            </c:when>
            <c:otherwise>  
                <security:authorize access="hasRole('ADMIN') or hasRole('USER')">
                    <a href="<c:url value="/user/editUser2" />">Update personal info</a>
                </security:authorize>
            </c:otherwise>
        </c:choose>

        <h2>Items</h2>
        <security:authorize access="hasRole('ADMIN')">    
            <a href="<c:url value="/user" />">Manage User Accounts</a><br /><br />
            <a href="<c:url value="/item/create" />">Create a Item</a><br /><br />
        </security:authorize>
        <security:authorize access="hasRole('USER')">
            <a href="<c:url value="/cart" />">Shopping Cart</a><br/>
        </security:authorize>

        <c:choose>
            <c:when test="${fn:length(itemDatabase) == 0}">
                <i>There are no items in the system.</i>
            </c:when>
            <c:otherwise>
                <c:forEach items="${itemDatabase}" var="item">
                    <security:authorize var="isAdmin" access="hasAnyRole('ADMIN')"/>
                    <c:if test="${item.isabailability eq true or isAdmin}">
                        Item ${item.id}:
                        <a href="<c:url value="/item/view/${item.id}" />">
                            <c:out value="${item.itemName}" /></a>
                        (price: <c:out value="${item.price}" />)
                        <security:authorize access="hasRole('USER')">
                            [<a href="<c:url value="/cart/add/${item.id}" />">Add to Cart</a>]
                        </security:authorize>
                        <security:authorize access="hasRole('ADMIN')">
                            [<a href="<c:url value="/item/edit/${item.id}" />">Edit</a>]
                        </security:authorize>
                        <security:authorize access="hasRole('ADMIN')">            
                            [<a href="<c:url value="/item/delete/${item.id}" />">Delete</a>]
                        </security:authorize>
                        <br /><br />
                    </c:if>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </body>
</html>
