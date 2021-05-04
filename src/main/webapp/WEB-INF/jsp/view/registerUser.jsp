<!DOCTYPE html>
<html>
<head>
    <title>Customer Support</title>
</head>
<body>

<h2>Register a User</h2>
<form:form method="POST" enctype="multipart/form-data"
           modelAttribute="reitemUser">
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
    <br /><br />
    <input type="submit" value="Register User"/>
</form:form>
</body>
</html>
