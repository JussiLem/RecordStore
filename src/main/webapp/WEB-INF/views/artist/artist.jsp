
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../header.jsp"/>
    <title>RecordStore - Artists</title>
</head>
<body>
<jsp:include page="artistList.jsp"/>
<jsp:include page="artistAdd.jsp"/>

<p>Delete may not work</p>
<jsp:include page="artistDelete.jsp"/>
<a href="home">Record Store - Homepage</a>
</body>
</html>
