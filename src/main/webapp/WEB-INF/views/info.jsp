<%@ page import="recordstore.data.Artist" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Record Store - Info Page</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/login_style.css">
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,700|Bitter" rel="stylesheet">
</head>
<body>
<div id="user-info">
    <%
        Artist artist = (Artist) session.getAttribute("name");
    %>
    <div id="welcome-text">Welcome <%= artist.getName()%></div>

</div>
</body>
</html>
