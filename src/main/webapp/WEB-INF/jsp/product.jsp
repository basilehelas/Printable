<%@ include file="include/importTags.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="container py-4">
    <h1 class="mb-4">
        <c:out value="${pageTitle != null ? pageTitle : 'Products'}"/>
        <c:if test="${not empty currentCategoryId}">
            <small class="text-muted">
                <c:forEach var="c" items="${categoriesNames}">
                    <c:if test="${c.id == currentCategoryId}">
                        <c:out value="${c.name}"/>
                    </c:if>
                </c:forEach>
            </small>
        </c:if>
    </h1>


    <c:choose>
        <c:when test="${empty products}">
            <div class="alert alert-info">No products to display.</div>
        </c:when>
        <c:otherwise>
            <div class="row g-4">
                <c:forEach var="p" items="${products}">
                    <div class="col-12 col-sm-6 col-lg-4">
                        <a href="${ctx}/products/${p.id}" class="text-decoration-none text-dark">
                            <div class="card h-100 shadow-sm">
                                <img class="card-img-top"
                                     src="${ctx}/products/${p.id}/image"
                                     alt="${p.name}"
                                     onerror="this.style.display='none'"/>
                                <div class="card-body d-flex flex-column">
                                    <h5 class="card-title mb-1">${p.name}</h5>
                                    <p class="card-text text-muted small mb-3">
                                        <c:out value="${p.description}"/>
                                    </p>
                                    <div class="mt-auto">
                                        <span class="fw-bold">${p.price} €</span>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>

</div>
