<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Record Store</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/style.css">
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,700|Bitter" rel="stylesheet">
</head>
<body>
<div id="splash"></div>

<h1>Artist name</h1>

<div id="wrapper">
    <h2>Add artist</h2>
    <form action="artists" method="post">
        <input id="artist-id" type="number" placeholder="Enter Artist Id" min="1" maxlength="40" name="number" required>
        <input id="artist-field" type="text" placeholder="Enter Artist Name" maxlength="40" name="name" autofocus autocomplete="off" required>
        <button id="artist-button" type="submit">Submit</button>
    </form>
    <h2>List all artists</h2>
    <form action="artists" method="get">
        <button id="artist-button-get" type="submit" name="find">Find</button>
    </form>
    <table border="1" >
    <c:forEach var="row" items="${rows}">
    <tr>
        <td><c:out value="${row.results}"/></td>
    </tr>
    </c:forEach>
    </table>
    <div id="credits-text">By Jussi Lemmetyinen</div>
</div>

</body>
</html>
