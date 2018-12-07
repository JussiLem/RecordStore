<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Add artist</h2>
<form action="addartists" method="post">
    <input id="artist-field" type="text" placeholder="Enter Artist Name" maxlength="40"
           name="add-artist" autofocus autocomplete="off" required>
    <button id="artist-button" type="submit">Add</button>
</form>
</body>
</html>