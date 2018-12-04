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

<h1>Artist name</h1>

<div id="wrapper">
    <h2>Add artist</h2>
    <form action="artists" method="post">
        <input id="artist-field" type="text" placeholder="Enter Artist Name" maxlength="40"
               name="artist" autofocus autocomplete="off" required>
        <input id="album-field" type="text" placeholder="Enter Album Name" maxlength="40"
               name="album" autofocus autocomplete="off" required>
        <button id="artist-button" type="submit">Submit</button>
    </form>
    <h2>List all artists and albums</h2>
    <form action="artists" method="get">
        <button id="artist-button-get" type="submit" name="find" value="all">Find</button>
    </form>
    <table>
        <tr>
            <th>Artist id</th>
            <th>Artist name</th>
            <th>Album id</th>
            <th>Album name</th>
        </tr>

        <c:forEach items="${ artistStream }" var="artist">

        <tr>
            <td><p>${artist.id}</p></td>
            <td><p>${artist.name}</p></td>
            <c:forEach items="${ albumStream }" var="album">
            <td><p>${album.id}</p></td>
            <td><p>${album.name}</p></td>
            </c:forEach>
        </tr>
        </c:forEach>


    </table>
    <div id="credits-text">By Jussi Lemmetyinen</div>
</div>

</body>
</html>
