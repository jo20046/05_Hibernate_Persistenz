<%@ page import="whs.jo20046.beans.Userdata" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<%
    Userdata userdata = (Userdata) session.getAttribute("userdata");
    if (userdata == null) userdata = new Userdata();
%>
<h4>Hallo <%=userdata.getUsername()%>!</h4><br>

<form method="get" action="${pageContext.request.contextPath}/whs/jo20046/ausgabe.jsp">
    <input type="submit" value="Newsfeed anzeigen">
</form>

<form method="get" action="${pageContext.request.contextPath}/whs/jo20046/eingabe.jsp">
    <input type="submit" value="Quellen bearbeiten">
</form>

<br>
<%=userdata.getUrlList().isEmpty() ? "Noch keine Quellen gespeichert." : userdata.urlsToHTMLString()%>


</body>
</html>