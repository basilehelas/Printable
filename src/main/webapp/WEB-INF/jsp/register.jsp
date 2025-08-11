<%@ include file="include/importTags.jsp" %>

<spring:url var="registerAction" value="/register"/>
<spring:url var="loginUrl" value="/login"/>
<head><link type="text/css" href="<spring:url value='/css/style.css'/>" rel="stylesheet"></head>
<div class="form-wrapper">
  <h2 class="form-title">Créer un compte</h2>

  <form:errors path="*" cssClass="form-alert form-alert-error"/>

  <form:form modelAttribute="user" method="post" action="${registerAction}" class="form">
    <div class="form-field">
      <label for="username">Nom d'utilisateur</label>
      <form:input path="username" id="username" cssClass="form-input"/>
      <form:errors path="username" cssClass="field-error"/>
    </div>

    <div class="form-field">
      <label for="email">E-mail</label>
      <form:input path="email" id="email" type="email" cssClass="form-input"/>
      <form:errors path="email" cssClass="field-error"/>
    </div>

    <div class="form-field">
      <label for="password">Mot de passe</label>
      <form:password path="password" id="password" cssClass="form-input"/>
      <form:errors path="password" cssClass="field-error"/>
    </div>

    <div class="form-field">
      <label for="address">Adresse</label>
      <form:textarea path="address" id="address" rows="3" cssClass="form-input"/>
      <form:errors path="address" cssClass="field-error"/>
    </div>

    <!-- CSRF si activé -->
    <c:if test="${not empty _csrf}">
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </c:if>

  <form:button  type="submit" class="form-button">S'inscrire</form:button>
  </form:form>
</div>
