<html>
<head>
    <title>Willkommen</title>
</head>
<body>
<h4>Willkommen beim Newsfeed!</h4><br>
Bitte melde dich an oder registriere dich, um auf deinen Feed zuzugreifen.<br><br>

<form method="get" action="${pageContext.request.contextPath}/whs/jo20046/registrierung.jsp">
    <input type="hidden" name="intent" value="Login">
    <input type="submit" value="Anmelden">
</form>

<form method="post" action="${pageContext.request.contextPath}/whs/jo20046/registrierung.jsp">
    <input type="hidden" name="intent" value="Registrierung">
    <input type="submit" value="Registrieren">
</form>
</body>
</html>