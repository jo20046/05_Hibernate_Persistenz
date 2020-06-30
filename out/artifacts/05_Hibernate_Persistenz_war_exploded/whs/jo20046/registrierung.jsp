<html>
<head>
    <title>Registrierung</title>
</head>
<body>
<h3><%=request.getParameter("intent")%></h3>
<form method="post" action="${pageContext.request.contextPath}/register">
    <input type="hidden" name="intent" value=<%=request.getParameter("intent")%>>
    <label for="usernameInput">Benutzername: </label>
    <input type="text" id="usernameInput" name="usernameInput" value=""><br><br>
    <label for="password">Passwort: </label>
    <input type="password" id="password" name="password" value=""><br><br>
    <input type="submit" value="Best&auml;tigen">
</form>
<%=request.getSession().getAttribute("failureMessage") == null ? "" : request.getSession().getAttribute("failureMessage")%>
</body>
</html>