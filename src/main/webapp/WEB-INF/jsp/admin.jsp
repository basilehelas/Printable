<%@ include file="include/importTags.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
    <title>Administration — Codes promo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet"/>
</head>
<body class="bg-light">

<div class="container py-4">

    <div class="d-flex align-items-center justify-content-between mb-4">
        <h1 class="h4 m-0"><i class="bi bi-ticket-perforated me-2"></i>Codes promo</h1>
        <a class="btn btn-outline-secondary btn-sm" href="<spring:url value='/home'/>">
            <i class="bi bi-arrow-left"></i> Retour
        </a>
    </div>

    <!-- Messages -->
    <c:if test="${not empty success}">
        <div class="alert alert-success">${success}</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <!-- Création -->
    <div class="card mb-4">
        <div class="card-header fw-semibold">
            <i class="bi bi-plus-circle me-2"></i>Nouveau code
        </div>
        <div class="card-body">
            <form action="${ctx}/admin/promo-codes" method="post" class="row g-3">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <div class="col-md-4">
                    <label class="form-label">Code</label>
                    <input type="text" name="code" class="form-control" maxlength="64" required>
                </div>

                <div class="col-md-3">
                    <label class="form-label">Remise</label>
                    <div class="input-group">
                        <input type="number" name="discount" class="form-control" step="0.01" min="0" required>
                        <span class="input-group-text">%</span>
                    </div>
                </div>

                <div class="col-md-3 d-flex align-items-end">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="isValid" name="isValid" checked>
                        <label class="form-check-label" for="isValid">Actif</label>
                    </div>
                </div>

                <div class="col-12">
                    <button class="btn btn-dark">
                        <i class="bi bi-save"></i> Créer
                    </button>
                </div>
            </form>
        </div>
    </div>

    <!-- Liste -->
    <div class="card">
        <div class="card-header fw-semibold">
            <i class="bi bi-list-ul me-2"></i>Codes existants
        </div>
        <div class="card-body p-0">
            <c:choose>
                <c:when test="${empty promoCodes}">
                    <div class="p-4 text-muted">Aucun code pour l’instant.</div>
                </c:when>
                <c:otherwise>
                    <div class="table-responsive">
                        <table class="table table-hover align-middle m-0">
                            <thead class="table-light">
                            <tr>
                                <th>Code</th>
                                <th>Remise</th>
                                <th>Statut</th>
                                <th class="text-end">Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="p" items="${promoCodes}">
                                <tr>
                                    <td class="fw-semibold"><c:out value="${p.code}"/></td>
                                    <td>
                                        <fmt:formatNumber value="${p.discount}" minFractionDigits="0" maxFractionDigits="2"/>%
                                    </td>
                                    <td>
                                        <c:if test="${p.valid}">
                                            <span class="badge text-bg-success">Actif</span>
                                        </c:if>
                                        <c:if test="${!p.valid}">
                                            <span class="badge text-bg-secondary">Inactif</span>
                                        </c:if>
                                    </td>
                                    <td class="text-end">
                                        <form action="${ctx}/admin/promo-codes/${p.code}/delete" method="post" class="d-inline"
                                              onsubmit="return confirm('Supprimer le code « ${p.code} » ?');">
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                            <button class="btn btn-sm btn-outline-danger">
                                                <i class="bi bi-trash"></i>
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
