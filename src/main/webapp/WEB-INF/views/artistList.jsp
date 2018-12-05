<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
    <h2>List all artists and albums</h2>
    <form action="listartists" method="get">
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
