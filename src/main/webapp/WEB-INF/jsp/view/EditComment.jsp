<!DOCTYPE html>
<html>
    <head>
        <title>Add Comment</title>
    </head>
    <body>
        <security:authorize var="isAdmin" access="hasAnyRole('ADMIN')"/>
        <security:authorize access="!isAuthenticated()"> 
                <c:redirect url="/item/${comment.id}"/>
         </security:authorize>
        <security:authorize access="isAuthenticated()"> 
            <c:if test="!${comment.username == username or isAdmin}">
                <c:redirect url="/item"/>
            </c:if>
        </security:authorize>    
        <h1>Add Comment</h1>
        <form:form method="POST" enctype="multipart/form-data"
                   modelAttribute="cm">
            <form:textarea path="message"></form:textarea> <br />
                <input type="submit" value="Add" />
        </form:form>
    </body>
</html>
