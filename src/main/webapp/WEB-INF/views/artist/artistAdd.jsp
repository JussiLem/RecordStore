<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h2>Add artist</h2>
<c:catch var="recordStoreException">
<form action="${pageContext.request.contextPath}/artist/addartists" method="post">
    <input id="artist-id" type="number" placeholder="Enter Artist Id" maxlength="40" name="artist-id" min="1" autocomplete="on"
           required>
    <input id="artist-field" type="text" placeholder="Enter Artist Name" maxlength="40"
           name="add-artist" autofocus autocomplete="off" required>
    <button id="artist-button" type="submit">Add</button>
</form>
</c:catch>
<c:if test=" ${recordStoreException != null}">
    <p> Poikkeus tapahtunut: ${recordStoreException.message}</p>
</c:if>