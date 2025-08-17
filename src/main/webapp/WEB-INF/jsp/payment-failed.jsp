<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container my-5 text-center">
    <h1 class="text-danger mb-4">❌ Paiement échoué</h1>
    <p>Une erreur est survenue ou le paiement a été annulé.</p>

    <div class="mt-4 d-flex justify-content-center gap-3">
        <a href="${pageContext.request.contextPath}/home" class="btn btn-secondary">
            Retour à l'accueil
        </a>
        <a href="${pageContext.request.contextPath}/orders/checkout" class="btn btn-primary">
            Réessayer le paiement
        </a>
    </div>
</div>
