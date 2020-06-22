<%@ page import="whs.jo20046.beans.Data" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ausgabe</title>
</head>
<body>
<%
    Data data = (Data) session.getAttribute("Data");
    if (data == null) data = new Data();
%>
<%=data.getArticles()%>

</body>
</html>
