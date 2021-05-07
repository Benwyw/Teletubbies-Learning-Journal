<!DOCTYPE html>
<html>
    <head>
        <title>${item.itemName}</title>
    </head>
    <style>
        .card {
            /* Add shadows to create the "card" effect */
            box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
            transition: 0.3s;
            width: 40%;
        }

        /* On mouse-over, add a deeper shadow */
        .card:hover {
            box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
        }

        /* Add some padding inside the card container */
        .container {
            padding: 2px 16px;
        }
        .card {
            box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
            transition: 0.3s;
            border-radius: 5px; /* 5px rounded corners */
        }

        /* Add rounded corners to the top left and the top right corner of the image */
        img {
            border-radius: 5px 5px 0 0;
        }
        p {
            word-wrap: break-word;
        }
    </style>
    <body>
        <security:authorize access="!isAuthenticated()"> 
            <a href="<c:url value="/login" />">Login</a>
        </security:authorize>


        <security:authorize var="isAdmin" access="hasAnyRole('ADMIN')"/>
        <security:authentication var="principal" property="principal" /> 
        <security:authorize access="hasRole('ADMIN') or hasRole('USER') ">

            <c:url var="logoutUrl" value="/logout"/>
            <form action="${logoutUrl}" method="post">
                <input type="submit" value="Log out" />
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </security:authorize>
        
        <security:authorize access="hasRole('ADMIN')">
            <br/>[<a href="<c:url value="/item/edit/${item.id}" />">Edit</a>]
        </security:authorize>
        <security:authorize access="hasRole('ADMIN')">
            [<a href="<c:url value="/item/delete/${item.id}" />">Delete</a>]
        </security:authorize>   

        <h2>Item #${item.id}: <c:out value="${item.itemName}" /></h2>
        <h4>Price: <c:out value="${item.price}" /></h4>
        <i>${item.description}</i>
        <c:choose>
            <c:when test="${item.isabailability}">
                <h4>${item.itemName} is <span style="color:blue;">available</span>.</h4>
            </c:when>
            <c:otherwise>
                <h4>${item.itemName} is <span style="color:red;">not available</span>.</h4>
            </c:otherwise>

        </c:choose>

        <br /><br />
        <!--<i>Item Name - <c:out value="${item.itemName}" /></i><br /><br />-->


        <c:if test="${fn:length(item.attachments) > 0}">
            Photo(s): <br/>
            <c:forEach items="${item.attachments}" var="attachment"
                       varStatus="status">
                <c:if test="${!status.first}">, </c:if>

                    <img src='<c:url value="/item/${item.id}/attachment/${attachment.name}"></c:url>' />      
            </c:forEach><br /><br />
        </c:if>
        <c:if test="${fn:length(item.comments) > 0}">
            Comments:<br /><br />

            <c:forEach items="${item.comments}" var="comment">
                <div class="card">
                    <div class="container">
                        <h4><b>${comment.username}</b></h4>
                        <p>${comment.comment}</p>
                        <p>
                            <security:authorize access="isAuthenticated()">
                                <c:if test="${comment.username==principal.username}">
                                    <span style="color: blue">[<a style="color: blue" href="<c:url value="/item/editcomment/${item.id}/${comment.id}" />">Edit Comment</a>]</span>
                                </c:if>
                            </security:authorize>
                            <security:authorize access="isAuthenticated() or hasRole('ADMIN') ">
                                <c:if test="${(comment.username == principal.username) or isAdmin}">
                                    <span style="color: red"> [<a style="color: red" href="<c:url value="/item/deletecomment/${item.id}/${comment.id}" />">Delete Comment</a>]</span>
                                </c:if>
                            </security:authorize>

                        </p>
                    </div>
                </div>





            </c:forEach>
        </c:if>
        <security:authorize access="isAuthenticated()">
            [<a href="<c:url value="/item/addcomment/${item.id}" />">Add Comment</a>]
        </security:authorize>
        <a href="<c:url value="/item" />">Return to list items</a>
    </body>
</html>
