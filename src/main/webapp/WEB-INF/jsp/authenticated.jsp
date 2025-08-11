<%@ include file="include/importTags.jsp" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PAGE AUTHENTIFIe</title>
</head>
<body>
<div>
    Bienvenue sur la page authentifiée ${pageContext.request.userPrincipal.principal.username}!
</div>
</body>
</html>
