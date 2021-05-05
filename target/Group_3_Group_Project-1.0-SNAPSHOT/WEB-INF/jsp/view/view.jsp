<!DOCTYPE html>
<html>
<head>
    <title>Customer Support</title>
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

<h2>Item #${item.id}: <c:out value="${item.itemName}" /></h2>
<security:authorize access="hasRole('ADMIN')">
    [<a href="<c:url value="/item/edit/${item.id}" />">Edit</a>]
</security:authorize>
<security:authorize access="hasRole('ADMIN')">
    [<a href="<c:url value="/item/delete/${item.id}" />">Delete</a>]
</security:authorize>   
<br /><br />
<i>Item Name - <c:out value="${item.itemName}" /></i><br /><br />
<c:out value="${item.price}" /><br /><br />
<c:if test="${fn:length(item.attachments) > 0}">
    Attachments:
    <c:forEach items="${item.attachments}" var="attachment"
               varStatus="status">
        <c:if test="${!status.first}">, </c:if>
        
      <img src='<c:url value="/item/${item.id}/attachment/${attachment.name}"></c:url>' />      
    </c:forEach><br /><br />
</c:if>
    <c:if test="${fn:length(item.comments) > 0}">
            Comments:<br /><br />
            <c:forEach items="${item.comments}" var="comment">
                By ${comment.username}:
                ${comment.comment}<br /><br />
                <c:if test="${comment.username==username}">
                    [<a href="<c:url value="/item/deletecomment/${item.id}/${comment.id}" />">Delete Comment</a>]
                    [<a href="<c:url value="/item/editcomment/${item.id}/${comment.id}" />">Edit Comment</a>]
                </c:if>
            </c:forEach><br /><br />
        </c:if>
        [<a href="<c:url value="/item/addcomment/${item.id}" />">Add Comment</a>]
<a href="<c:url value="/item" />">Return to list items</a>
</body>
</html>
