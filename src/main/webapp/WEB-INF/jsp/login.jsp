<%@ include file="include/importTags.jsp" %>

<html lang="fr">
<head>  <link type="text/css" href="<spring:url value='/css/style.css'/>" rel="stylesheet"></head>
<div class="form-wrapper">
  <h2 class="form-title">Se connecter</h2>

  <c:if test="${param.error != null}">
    <div class="form-alert form-alert-error">Identifiants invalides.</div>
  </c:if>

  <form:form modelAttribute="user" method="POST" class="form">
        <form:input path="username" placeholder="Adresse mail" cssClass="form-input" />
        <form:password path="password" placeholder="Mot de passe" cssClass="form-input" />
    <form:button type="submit" class="form-button">S'inscrire</form:button>
    </form:form>

</div>
<a type="submit" class="form-button" href="<c:url value='/register'/>">Créer un compte</a>
</body>
</div>

</html>
