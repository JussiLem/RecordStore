<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h2>Remove artist</h2>
<form action="${pageContext.request.contextPath}/artist/deleteartist" method="post">
    <input id="artist-delete-field" type="text" placeholder="Enter Artist Name" maxlength="40"
           name="remove-artist" autofocus autocomplete="off" required>
    <button id="artist-delete" type="submit">Delete</button>
</form>