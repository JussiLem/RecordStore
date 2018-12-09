<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <title>Record Store - Albums</title>
</head>
<body>
<form action="addalbum" method="post">
    <input id="album-id" type="number" placeholder="Enter Album Id" maxlength="40" name="id" min="1"
           required>
    <input id="artist-id" type="text" placeholder="Enter Artist Name" maxlength="40" name="artist"
           autofocus autocomplete="off" required>
    <input id="album-field" type="text" placeholder="Enter Album Name" maxlength="40"
           name="album" autofocus autocomplete="off" required>
    <button id="album-button" type="submit">Add</button>
</form>
</body>
</html>