<html>
<head>
    <title>Login</title>
</head>
<body>
<h3>Login</h3>
<form method="get" action="${pageContext.request.contextPath}/login">
    <label for="usernameInput">Benutzername: </label>
    <input type="text" id="usernameInput" name="usernameInput" value=""><br><br>
    <label for="password">Passwort: </label>
    <input type="password" id="password" name="password" value=""><br><br>
    <input type="submit" value="Best&auml;tigen">
</form>
<%=request.getSession().getAttribute("loginFailedCause") == null ? "" : request.getSession().getAttribute("loginFailedCause")%>
</body>
</html>