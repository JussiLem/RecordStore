<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Error Page</title>
</head>
<body>
<% if(response.getStatus() == 500){ %>
<span style="color: red; ">Error: <%=exception.getMessage() %></span><br>

<%-- include login page --%>
<%@ include file="WEB-INF/views/index.jsp"%>
<%}else {%>
Hi There, error code is <%=response.getStatus() %><br>
Please go to <a href="${pageContext.request.contextPath}/home">home page</a>
<%} %>
</body>
</html>
