<%@ page pageEncoding="UTF-8"
         contentType="text/html; charset=UTF-8"%>
<%@ include file="include/importTags.jsp" %>
<html>
<head>
    <title> Welcome </title>
</head>
<body>
<div>
    <spring:message code="welcome.message" />
</div>
<div>
    ${message}
</div>
<h2>Enter <spring:message code="magicKey.label" /></h2>

<form:form method="post" modelAttribute="magicKeyForm" action="/firstSpring/hello/submit">
    <form:label path="magicKey">Magic Key:</form:label>
    <form:input path="magicKey" />
    <br/><br/>
    <form:button><spring:message code="send.button" /></form:button>
</form:form>
</body>
</html>