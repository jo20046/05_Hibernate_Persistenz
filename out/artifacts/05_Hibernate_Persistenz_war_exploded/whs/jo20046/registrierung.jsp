<html>
<head>
    <title>Registrierung</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/register">
    <label for="usernameInput">Benutzername: </label>
    <input type="text" id="usernameInput" name="usernameInput" value=""><br><br>
    <label for="password">Passwort: </label>
    <input type="password" id="password" name="password" value=""><br><br>
    <input type="submit" value="Best&auml;tigen">
</form>
</body>
</html>