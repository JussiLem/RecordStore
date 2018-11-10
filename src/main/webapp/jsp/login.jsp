
<%@page import="recordstore.data.User"%>

<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="header.jsp" %>
    <title>Record vault</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,700|Bitter" rel="stylesheet">
</head>
<body>
<div id="user-info">
    <%
        User user = (User) session.getAttribute("name");
    %>
    <div id="welcome">Welcome <%= user.getName()%></div>
    <form method="post" action="login">
        <h2>Name:</h2>
        <input type="text" id="say-hello-text-input" name="name" />
        <input type="submit" id="say-hello-button" value="Say Hello" />
    </form>
</div>

</body>
</html>
