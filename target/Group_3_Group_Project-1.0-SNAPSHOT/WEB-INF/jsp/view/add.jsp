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
        <h2>Create a Item</h2>
        <form:form method="POST" enctype="multipart/form-data"
                   modelAttribute="itemForm">
            <form:label path="itemName">Item Name</form:label><br />
            <form:input type="text" path="itemName" /><br /><br />
            <form:label path="price">Price</form:label><br />
            <form:input type="number" path="price" /><br /><br />
            <form:label path="isabailability">Item is abailability</form:label><br />
            Is abailability <form:radiobutton path="isabailability" value="true"/>  
            Not abailability <form:radiobutton path="isabailability" value="false"/><br /><br />  
            <b>Attachments</b><br />
            <input type="file" name="attachments" multiple="multiple" /><br /><br />
            <input type="submit" value="Submit"/>
        </form:form>
    </body>
</html>
