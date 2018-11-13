<%@ page import="recordstore.servlet.LoginServlet" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Record Store</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,700|Bitter" rel="stylesheet">
</head>
<body>
<div id="splash"></div>
<div id="wrapper">
    <form action="login" method="post">
        <input id="login-field" type="text" placeholder="Enter Your Name" maxlength="40" name="name"
               autofocus autocomplete="off" required>
        <button id="login-button" type="submit">Submit</button>
    </form>

    <div id="credits-text">By Jussi Lemmetyinen</div>
</div>
<!--  Jquery -->
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
    // Focus login kenttään
    setTimeout(function () {$("#<%=LoginServlet.PARAM_LOGIN%>").focus().select();
    }, 1);

</script>
</body>
</html>
