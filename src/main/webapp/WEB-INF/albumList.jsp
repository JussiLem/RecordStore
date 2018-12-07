<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Record Store - Albums</title>
</head>
<body>
<form action="addalbum" method="post">
    <input id="album-field" type="text" placeholder="Enter Album Name" maxlength="40"
           name="album" autofocus autocomplete="off" required>
    <button id="album-button" type="submit">Add</button>
</form>
</body>
</html>
