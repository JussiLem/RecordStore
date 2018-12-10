<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../header.jsp"/>
    <title>Record Store - Add Albums</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/album/addalbum" method="post">
    <input id="album-id" type="number" placeholder="Enter Album Id" maxlength="100" name="id" min="1"
           required>
    <input id="artist-id" type="text" placeholder="Enter Artist Name" maxlength="40" name="artist"
           autofocus autocomplete="off" required>
    <input id="album-field" type="text" placeholder="Enter Album Name" maxlength="40"
           name="album" autofocus autocomplete="off" required>
    <button id="album-button" type="submit">Add</button>
</form>

</body>
</html>
