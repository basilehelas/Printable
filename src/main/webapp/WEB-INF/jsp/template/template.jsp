<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="../include/importTags.jsp" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
    <title><c:out value="${pageTitle}" /></title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"/>

    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet"/>

    <!-- Ton CSS -->
    <link rel="stylesheet" href="${ctx}/css/style.css"/>
</head>
<body>

<!-- Bandeau haut -->
<nav class="navbar navbar-expand-md bg-light border-bottom">
    <div class="container">
        <!-- Logo à gauche -->
        <a class="navbar-brand d-flex align-items-center gap-2" href="${ctx}/" aria-label="Accueil">
            <img src='<spring:url value="/imagies/logo.png"/>' alt="Logo" height="65"/>
            <span class="fw-semibold">Printable</span>
        </a>

        <!-- Burger -->
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mainNavbar"
                aria-controls="mainNavbar" aria-expanded="false" aria-label="Ouvrir le menu">
            <span class="navbar-toggler-icon"></span>
        </button>

        <!-- Contenu de la barre -->
        <div class="collapse navbar-collapse" id="mainNavbar">
            <!-- Centre : Home + Produits -->
            <ul class="navbar-nav mx-auto mb-2 mb-md-0">
                <li class="nav-item">
                    <a class="nav-link" href="${ctx}/"><i class="bi bi-house-door"></i> Home</a>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="productsDropdown" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="bi bi-bag"></i> Produits
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="productsDropdown">
                        <li><a class="dropdown-item" href="${ctx}/products">Tous les produits</a></li>
                        <li><a class="dropdown-item" href="${ctx}/categories">Catégories</a></li>
                    </ul>
                </li>
            </ul>


            <ul class="navbar-nav ms-auto align-items-md-center gap-md-2">
                <!-- Sélecteur FR/EN -->
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle d-flex align-items-center gap-1" href="#" id="langDropdown"
                       role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="bi bi-globe2"></i> <span class="d-md-inline d-none">Langue</span>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="langDropdown">
                        <li><a class="dropdown-item" href="?lang=fr">FR – Français</a></li>
                        <li><a class="dropdown-item" href="?lang=en">EN – English</a></li>
                    </ul>
                </li>

                <!-- Compte -->
                <li class="nav-item">
                    <a class="nav-link d-flex align-items-center gap-1" href="${ctx}/account">
                        <i class="bi bi-person-circle"></i> <span class="d-md-inline d-none">Compte</span>
                    </a>
                </li>

                <!-- Panier -->
                <li class="nav-item">
                    <a class="nav-link position-relative d-flex align-items-center gap-1" href="${ctx}/cart" aria-label="Panier">
                        <i class="bi bi-cart3"></i> <span class="d-md-inline d-none">Panier</span>
                        <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-dark">
              <c:out value="${cartCount != null ? cartCount : 0}" />
              <span class="visually-hidden">articles dans le panier</span>
            </span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Zone éditable -->
<main >
    <tiles:insertAttribute name="main-content"/>

</main>


<!-- Footer -->
<footer class="mt-auto py-4 bg-light border-top">
    <div class="container">
        <div class="row">

            <div class="col-md-4 mb-3">
                <a href="${ctx}/" class="d-flex align-items-center gap-2 mb-2 text-decoration-none">
                    <img src='<spring:url value="/imagies/logo.png"/>' alt="Logo" height="36"/>
                    <span class="fw-semibold">Printable</span>
                </a>
                <p class="text-muted small">
                    Votre boutique en ligne de confiance pour des produits de qualité et un service rapide.
                </p>
            </div>

            <div class="col-md-4 mb-3">
                <h6 class="fw-bold">Informations</h6>
                <ul class="list-unstyled">
                    <li><a href="${ctx}/contact" class="text-muted text-decoration-none">Contact Us</a></li>
                    <li><a href="${ctx}/privacy-policy" class="text-muted text-decoration-none">Privacy Policy</a></li>
                    <li><a href="${ctx}/terms" class="text-muted text-decoration-none">Terms & Conditions</a></li>
                    <li><a href="${ctx}/shipping" class="text-muted text-decoration-none">Politique de livraison</a></li>
                </ul>
            </div>

            <!-- Réseaux sociaux -->
            <div class="col-md-4 mb-3">
                <h6 class="fw-bold">Suivez-nous</h6>
                <div class="d-flex gap-3">
                    <a href="#" class="text-muted fs-4"><i class="bi bi-facebook"></i></a>
                    <a href="#" class="text-muted fs-4"><i class="bi bi-instagram"></i></a>
                    <a href="#" class="text-muted fs-4"><i class="bi bi-twitter"></i></a>
                    <a href="#" class="text-muted fs-4"><i class="bi bi-youtube"></i></a>
                </div>
            </div>
        </div>

        <div class="border-top pt-3 mt-3 text-center small text-muted">
            &copy; 2025 Printable — Tous droits réservés.
        </div>
    </div>
</footer>




<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
