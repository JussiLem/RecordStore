
<%@page import="recordstore.data.User"%>

<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Record vault</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,700|Bitter" rel="stylesheet">
</head>
<body>
<div id="user-info">
    <%
        User user = (User) session.setAttribute("name");
    %>
    <div id="welcome">Welcome <%= user.getName()%></div>
</div>

</body>
</html>
