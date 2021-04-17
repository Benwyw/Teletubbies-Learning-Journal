<!DOCTYPE html>
<html>
    <head>
        <title>Edit Comment</title>
    </head>
    <body>
        <h1>Edit Comment</h1>
        <form:form method="post" modelAttribute="entry">
            <form:label path="name">Name:</form:label> 
            <form:input type="text" path="name"/> <br />
            <form:textarea path="message" /> <br />
            <input type="submit" name="save" value="Save" />
        </form:form>
    </body>
</html>
