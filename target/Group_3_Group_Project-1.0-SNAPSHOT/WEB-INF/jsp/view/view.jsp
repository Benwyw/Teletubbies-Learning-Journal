<!DOCTYPE html>
<html>
    <head>
        <title>Item View</title>
    </head>
    <body>
         <a href="<c:url value="/login" />">Login</a><br />
         
        <security:authorize access="hasRole('ADMIN') or hasRole('USER') ">
        <c:url var="logoutUrl" value="/logout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Log out" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        </security:authorize>

       <security:authorize access="hasRole('ADMIN')">
            [<a href="<c:url value="/item/edit/${itemId}" />">Edit</a>]
        </security:authorize>

        <security:authorize access="hasRole('ADMIN')">
            [<a href="<c:url value="/item/delete/${itemId}" />">Delete</a>]
        </security:authorize>

        <h2>Item #${itemId}:</h2>
        <i>Item Name - <c:out value="${item.itemName}" /></i><br /><br />
        <i>Item Price - <c:out value="${item.price}" /></i><br /><br />
        <c:out value="${item.isabailability}" /><br /><br />
        <c:if test="${item.numberOfAttachments > 0}">
            Attachments:
            <c:forEach items="${item.attachments}" var="attachment"
                       varStatus="status">
                <c:if test="${!status.first}">, </c:if>
                <a href="<c:url value="/item/${itemId}/attachment/${attachment.name}" />">
                    <c:out value="${attachment.name}" /></a>
            </c:forEach><br /><br />
        </c:if>
        <a href="<c:url value="/item" />">Return to list item</a>
    </body>
</html>