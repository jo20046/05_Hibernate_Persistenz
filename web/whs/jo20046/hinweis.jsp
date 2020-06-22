<%@ page import="whs.jo20046.beans.Data" %>
<html>
<head>
    <title>Hinweis</title>
</head>
<body>
<%
    Data data = (Data) session.getAttribute("Data");
    if (data == null) data = new Data();
%>
<form method="get" action="${pageContext.request.contextPath}/whs/jo20046/eingabe.jsp">
    <label>
        <%=data.getNotFoundUrls()%>
    </label>
    <input type="submit" value="OK">
</form>
</body>
</html>