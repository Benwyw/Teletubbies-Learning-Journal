<!DOCTYPE html>
<html>
    <head>
        <title>Customer Support</title>
    </head>
    <body>
        <c:url var="logoutUrl" value="/logout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Log out" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <h2>User #${user.username}</h2>
        <form:form method="POST" enctype="multipart/form-data"
                   modelAttribute="edituserForm">
            <form:label path="username">Username</form:label><br/>
            <form:input type="text" path="username" /><br/><br/>
            <form:label path="password">Password</form:label><br/>
            <form:input type="text" path="password" /><br/><br/>
            <form:label path="fullname">Full Name</form:label><br/>
            <form:input type="text" path="fullname" /><br/><br/>
            <form:label path="phone">Phone</form:label><br/>
            <form:input type="text" path="phone" /><br/><br/>
            <form:label path="address">Address</form:label><br/>
            <form:input type="text" path="address" /><br/><br/>
            
      
            <input type="submit" value="Confirm changes"/><br/><br/>
        </form:form>
  
    </body>
</html>