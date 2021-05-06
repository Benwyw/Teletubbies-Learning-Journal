<!DOCTYPE html>
<html>
    <head>
        <title>Customer Support</title>
    </head>
    <style>
        input[type="file"] {
            position: absolute;
            margin: 0;
            padding: 0;
            width: 200px;
            height: 200px;
            outline: none;
            opacity: 0;
            border: 2px;
        }

        .div_fileInput {
            width: 200px;
            background: #ddd;
            height: 200px;
            display: block;
            border: 4px dashed #000000;
            word-wrap: normal;
        }


        .inputbox_text {
            text-align: center;
            color: #000000;
            font-family: Arial;
        }
    </style>
    <body>
        <c:url var="logoutUrl" value="/logout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Log out" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <h2>Item #${item.id}</h2>
        <form:form method="POST" enctype="multipart/form-data"
                   modelAttribute="itemForm">
            <form:label path="itemName">Item Name</form:label><br />
            <form:input type="text" path="itemName" /><br /><br />
            <form:label path="price">Price</form:label><br/>
            <form:input type="number" path="price" /><br /><br />
            <form:label path="isabailability">Item is available</form:label><br />
            Is available <form:radiobutton path="isabailability" value="true"/>  
            Not available <form:radiobutton path="isabailability" value="false"/><br /><br />  
            <c:if test="${fn:length(item.attachments) > 0}">
                <b>Attachments:</b><br/>
                <ul>
                    <c:forEach items="${item.attachments}" var="attachment">
                        <li>
                            <c:out value="${attachment.name}" />
                            [<a href="<c:url
                                    value="/item/${item.id}/delete/${attachment.name}"
                                    />">Delete</a>]
                        </li>
                    </c:forEach>
                </ul>
            </c:if>
            <b>Add attachments</b><br />
            <div class="div_fileInput">
                <input type="file" id="file" name="attachments" multiple="multiple" onchange="javascript:updateList()" /><br/><br/>
                <div id="message">
                    <p class="inputbox_text">Drag your files here or click in this area.</p>
                    <p class="inputbox_text">Allowed extensions are: .jpg, .jpeg, .bmp, .gif, .png</p>
                </div>
            </div>
            <p id="error_message" style="color: red;"></p>
            <br/>
            <input type="submit" value="Save"/><br/><br/>
        </form:form>
        <a href="<c:url value="/item" />">Return to list items</a>
        <script>
            updateList = function () {
                var _validFileExtensions = [".jpg", ".jpeg", ".bmp", ".gif", ".png"];
                var input = document.getElementById("file");
                var output = document.getElementById("message");
                var children = "";
                if (input.files.length == 0) {
                    output.innerHTML =
                            "<p>Drag your files here or click in this area.</p>";
                } else {
                    var check = true;
                    for (var i = 0; i < input.files.length; ++i) {
                        var filename = input.files.item(i).name;
                        var valid = false;
                        for (var j = 0; j < _validFileExtensions.length; j++) {
                            var extension = _validFileExtensions[j];

                            if (
                                    filename
                                    .substr(filename.length - extension.length, extension.length)
                                    .toLowerCase() == extension.toLowerCase()
                                    ) {
                                valid = true;
                                break;
                            }
                        }
                        if (!valid) {
                            check = false;
                        }
                    }
                    if (check == true) {
                        for (var i = 0; i < input.files.length; ++i) {
                            var filename = input.files.item(i).name;
                            var valid = false;

                            children += "<li>" + filename + "</li>";
                        }
                        document.getElementById("error_message").innerHTML = "";
                    } else {
                        document.getElementById("error_message").innerHTML = "Incorrect file type.";
                        document.getElementById("file").value = "";
                    }

                    output.innerHTML =
                            "<ul>" +
                            children +
                            "</ul><p>Drag your files here or click in this area to reupload.</p>" +
                            "<p>Allowed extensions are: " +
                            _validFileExtensions.join(", ") +
                            "</p>";
                }
            };
        </script>
    </body>
</html>