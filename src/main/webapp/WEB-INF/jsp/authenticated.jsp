<%@ include file="include/importTags.jsp" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<spring:url var="account" value="/authenticated"/>

<spring:message var="registerTitle" code="register.title"/>
<spring:message var="registerUsername" code="register.username"/>
<spring:message var="registerEmail" code="register.email"/>
<spring:message var="registerPassword" code="register.password"/>
<spring:message var="registerConfirmPassword" code="register.confirmPassword"/>
<spring:message var="registerAddress" code="register.address"/>
<spring:message var="registerPhone" code="register.phone"/>
<spring:message var="registerSubmit" code="register.submit"/>

<html lang="fr">
<head>
  <link type="text/css" href="<spring:url value='/css/style.css'/>" rel="stylesheet">
  <title>${registerTitle}</title>
</head>

<body>
<div class="form-wrapper">

  <form:form modelAttribute="userUpdate" method="post" action="${account}" class="form">

    <div class="form-field">
      <form:label path="username">${registerUsername}</form:label>
      <form:input path="username" id="username" cssClass="form-input"/>
      <spring:bind path="userUpdate.username">
        <c:if test="${status.error}">
          <div class="form-alert form-alert-error">${status.errorMessages[0]}</div>
        </c:if>
      </spring:bind>
    </div>

    <div class="form-field">
      <form:label path="email">${registerEmail}</form:label>
      <form:input path="email" id="email" type="email" cssClass="form-input" readonly="readonly"/>
    </div>

    <!-- Nouveau mot de passe -->
    <div class="form-field">
      <form:label path="password">${registerPassword}</form:label>
      <form:password path="password" id="password" cssClass="form-input"/>
      <spring:bind path="userUpdate.password">
        <c:if test="${status.error}">
          <div class="form-alert form-alert-error">${status.errorMessages[0]}</div>
        </c:if>
      </spring:bind>
    </div>

    <!-- Confirmation du mot de passe -->
    <div class="form-field">
      <form:label path="confirmPassword">${registerConfirmPassword}</form:label>
      <form:password path="confirmPassword" id="confirmPassword" cssClass="form-input"/>
      <spring:bind path="userUpdate.confirmPassword">
        <c:if test="${status.error}">
          <div class="form-alert form-alert-error">${status.errorMessages[0]}</div>
        </c:if>
      </spring:bind>
    </div>

    <div class="form-field">
      <form:label path="address">${registerAddress}</form:label>
      <form:textarea path="address" id="address" rows="3" cssClass="form-input"/>
    </div>

    <div class="form-field">
      <form:label path="phoneNumber">${registerPhone}</form:label>
      <form:input path="phoneNumber" id="phone" type="tel" cssClass="form-input"/>
    </div>

    <form:button type="submit" class="form-button">${registerSubmit}</form:button>
  </form:form>
</div>

</body>
</html>
