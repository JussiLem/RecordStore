<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Record Store - Artists</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <!--   <link rel="stylesheet" href="./css/style.css"> -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,700|Bitter" rel="stylesheet">

</head>
<body>
<div id="splash"></div>

<h1>Add and list artists</h1>

<div id="wrapper">
    <h2>Add artist</h2>
    <form action="artists" method="post">
        <input id="artist-field" type="text" placeholder="Enter Artist Name" maxlength="40"
               name="artist" autofocus autocomplete="off" required>
        <button id="artist-button" type="submit">Submit</button>
    </form>
    <h2>List all artists and albums</h2>
    <form action="artists" method="get">
        <button id="artist-button-get" type="submit" name="find" value="all">Find</button>
    </form>


        <c:forEach items="${ artistStream }" var="artist">
        <c:if test="${artist != null}">
    <table>
            <tr>
                <th>Artist id</th>
                <th>Artist name</th>
            </tr>
        <tr>
            <td>${artist.id}</td>
            <td>${artist.name}</td>
        </tr>
    </table>
        </c:if>
        </c:forEach>
    <span class="error">${message}</span>
    <div id="credits-text">By Jussi Lemmetyinen</div>
</div>

</body>
</html>
