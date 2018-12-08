<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<body>
<h2>List all artists</h2>
<form action="${pageContext.request.contextPath}/artist/listartists" method="get">
    <button id="artist-button-get" type="submit" name="find" value="all">Find</button>
</form>
<c:forEach items="${ artistStream }" var="artist">
    <c:if test="${artist != null}">
        <table border="1">
            <tr>
                <th>Artist id</th>
                <th>Artist name</th>
            </tr>
            <tr>
                <td><fmt:formatNumber value="${artist.id}"/></td>
                <td>${artist.name}</td>
            </tr>
        </table>
    </c:if>
</c:forEach>

<span class="error">${message}</span>
</body>
</html>