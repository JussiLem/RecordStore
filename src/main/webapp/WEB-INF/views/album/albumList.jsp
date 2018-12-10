<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="../header.jsp"/>
    <title>Record Store - List Albums</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/album/listalbum" method="get">
    <button id="album-button-get" type="submit" name="find" value="all">Find</button>
</form>
<c:forEach items="${ albumStream }" var="album">
    <c:if test="${album != null}">
        <table border="1">
            <tr>
                <th>Album id</th>
                <th>Album name</th>
                <th>Artist id</th>
                <th>Artist name</th>
            </tr>
            <tr>
                <td><fmt:formatNumber value="${album.albumId}"/></td>
                <td>${album.name}</td>
                <td><fmt:formatNumber value="${album.artistId}"/></td>
                <td>${album.artistName}</td>
            </tr>
        </table>
    </c:if>
</c:forEach>

<span class="error">${message}</span>
</body>
</html>
