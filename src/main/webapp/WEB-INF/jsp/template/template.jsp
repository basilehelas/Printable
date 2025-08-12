<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="../include/importTags.jsp" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
    <title><c:out value="${pageTitle}" /></title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet"/>

    <link rel="stylesheet" href="${ctx}/css/style.css"/>

    <spring:url var="localeFr" value="">
        <spring:param name="lang" value="fr" />
    </spring:url>

    <spring:url var="localeEn" value="">
        <spring:param name="lang" value="en" />
    </spring:url>

</head>
<body>


<nav class="navbar navbar-expand-md bg-light border-bottom">
    <div class="container">

        <a class="navbar-brand d-flex align-items-center gap-2" href="<spring:url value="/home"/>" aria-label="<spring:message code="navbar.home"/>">
            <img src='<spring:url value="/images/logo.png"/>' alt="Logo" height="65"/>
            <span class="fw-semibold">Printable</span>
        </a>


        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mainNavbar"
                aria-controls="mainNavbar" aria-expanded="false" aria-label="Ouvrir le menu">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="mainNavbar">
            <!-- Centre : Home + Produits -->
            <ul class="navbar-nav mx-auto mb-2 mb-md-0">
                <li class="nav-item">
                    <a class="nav-link" href="<spring:url value="/home"/>"><i class="bi bi-house-door"></i> <spring:message code="navbar.home"/> </a>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="productsDropdown" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="bi bi-bag"></i> <spring:message code="navbar.products"/>
                    </a>

                    <ul class="dropdown-menu" aria-labelledby="productsDropdown">
                        <c:forEach var="c" items="${categoriesNames}">
                            <li>
                                <a class="dropdown-item" href="${ctx}/product?categoryId=${c.id}">
                                    <c:out value="${c.name}"/>
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </li>

            </ul>


            <ul class="navbar-nav ms-auto align-items-md-center gap-md-2">

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle d-flex align-items-center gap-1" href="#" id="langDropdown"
                       role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="bi bi-globe2"></i> <span class="d-md-inline d-none"><spring:message code="navbar.language"/></span>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="langDropdown">
                        <li><a class="dropdown-item" href="${localeFr}">FR – Français</a></li>
                        <li><a class="dropdown-item" href="${localeEn}">EN – English</a></li>

                    </ul>
                </li>
                <sec:authorize access="!isAuthenticated()">
              <li class="nav-item">
                <a class="nav-link d-flex align-items-center gap-1" href="<spring:url value="/login"/>">
                  <i class="bi bi-person-circle"></i>
                  <span class="d-md-inline d-none"><spring:message code="navbar.account"/></span>
                </a>
              </li>
            </sec:authorize>

            <sec:authorize access="isAuthenticated()">
              <sec:authentication property="principal.username" var="username"/>

              <li class="nav-item">
                <span class="nav-link d-flex align-items-center gap-1">
                  <i class="bi bi-person-circle"></i>
                  <span class="d-md-inline d-none">Bienvenue, ${username}</span>
                </span>
              </li>

              <li class="nav-item">
                <a class="nav-link d-flex align-items-center gap-1" href="<spring:url value="/authenticated"/>">
                  <i class="bi bi-gear"></i>
                  <span class="d-md-inline d-none">Mon compte</span>
                </a>
              </li>

              <li class="nav-item">
                <a class="nav-link d-flex align-items-center gap-1" href="<spring:url value="/logout"/>">
                  <i class="bi bi-box-arrow-right"></i>
                  <span class="d-md-inline d-none">Déconnexion</span>
                </a>
              </li>
            </sec:authorize>


                <li class="nav-item">
                    <a class="nav-link position-relative d-flex align-items-center gap-1" href="${ctx}/cart" aria-label="Panier">
                        <i class="bi bi-cart3"></i> <span class="d-md-inline d-none"><spring:message code="navbar.cart"/></span>
                        <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-dark">
              <c:out value="${cartCount != null ? cartCount : 0}" />
              <span class="visually-hidden"> product in cart</span>
            </span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>


<main >
    <tiles:insertAttribute name="main-content"/>

</main>



<footer class="mt-auto py-4 bg-light border-top">
    <div class="container">
        <div class="row">

            <div class="col-md-4 mb-3">
                <a href="<spring:url value="/home"/>" class="d-flex align-items-center gap-2 mb-2 text-decoration-none">
                    <img src='<spring:url value="/images/logo.png"/>' alt="Logo" height="36"/>
                    <span class="fw-semibold">Printable</span>
                </a>
                <p class="text-muted small">
                    <spring:message code="footer.description"/>
                </p>
            </div>

            <div class="col-md-4 mb-3">
                <ul class="list-unstyled">
                    <li><a href="${ctx}/info/contact" class="text-muted text-decoration-none"><spring:message code="footer.contact"/></a></li>
                    <li><a href="${ctx}/info/privacy-policy" class="text-muted text-decoration-none"><spring:message code="footer.privacy"/></a></li>
                    <li><a href="${ctx}/info/terms" class="text-muted text-decoration-none"><spring:message code="footer.terms"/></a></li>
                    <li><a href="${ctx}/info/shipping" class="text-muted text-decoration-none"><spring:message code="footer.shipping"/></a></li>
                </ul>
            </div>

            <!-- Réseaux sociaux -->
            <div class="col-md-4 mb-3">
                <h6 class="fw-bold"><spring:message code="footer.follow"/></h6>
                <div class="d-flex gap-3">
                    <a href="#" class="text-muted fs-4"><i class="bi bi-facebook"></i></a>
                    <a href="#" class="text-muted fs-4"><i class="bi bi-instagram"></i></a>
                    <a href="#" class="text-muted fs-4"><i class="bi bi-twitter"></i></a>
                    <a href="#" class="text-muted fs-4"><i class="bi bi-youtube"></i></a>
                </div>
            </div>
        </div>

        <div class="border-top pt-3 mt-3 text-center small text-muted">
            &copy; 2025 Printable — <spring:message code="footer.copyright"/>.
        </div>
    </div>
</footer>




<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
