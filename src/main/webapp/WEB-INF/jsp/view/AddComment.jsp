<!DOCTYPE html>
<html>
    <head>
        <title>Add Comment</title>
    </head>
    <body>
        <h1>Add Comment</h1>
        <form:form method="POST" enctype="multipart/form-data"
                   modelAttribute="cm">
            <form:textarea path="message"></form:textarea> <br />
                <input type="submit" value="Add" />
        </form:form>
    </body>
</html>
