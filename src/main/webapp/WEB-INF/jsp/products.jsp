<%@ include file="include/importTags.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


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
            <a href="${ctx}/products/${p.id}"
               class="text-decoration-none text-dark js-open-product"
               data-id="${p.id}"
               data-name="${p.name}"
               data-desc="${fn:escapeXml(p.description)}"
               data-price="${p.price}"
               data-img="${ctx}/products/${p.id}/image">
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
                  <div class="mt-auto d-flex justify-content-between align-items-center">
                    <span class="fw-bold">${p.price} €</span>
                    <i class="bi bi-cart-plus fs-5 text-secondary" title="Ajouter (bientôt)"></i>
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

<div class="modal fade" id="productModal" tabindex="-1" aria-hidden="true">
  <div class="modal-dialog modal-lg modal-dialog-scrollable">
    <div class="modal-content">
      <div class="modal-header">
        <h5 id="pm-title" class="modal-title"></h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>

      <div class="modal-body">
        <div class="row g-3">
          <div class="col-md-5 d-flex justify-content-center align-items-start">
            <img id="pm-img" alt="" class="img-fluid rounded border" style="max-height:360px;">
          </div>
          <div class="col-md-7">
            <p id="pm-desc" class="text-muted"></p>
            <div class="d-flex align-items-center gap-3 mt-3">
              <div id="pm-price" class="fw-bold fs-4"></div>
              <i class="bi bi-cart-plus fs-3 text-secondary" title="Ajouter (bientôt)"></i>
            </div>
          </div>
        </div>
      </div>

      <div class="modal-footer">
        <button class="btn btn-outline-secondary" data-bs-dismiss="modal">Fermer</button>
      </div>
    </div>
  </div>
</div>

<script>
  (function () {
    const modalEl = document.getElementById('productModal');

    document.querySelectorAll('.js-open-product').forEach(a => {
      a.addEventListener('click', (e) => {
        if (e.metaKey || e.ctrlKey) return;
        e.preventDefault();

        const name  = a.dataset.name || '';
        const desc  = a.dataset.desc || '';
        const price = a.dataset.price || '';
        const img   = a.dataset.img || '';

        document.getElementById('pm-title').textContent = name;
        document.getElementById('pm-desc').textContent  = desc;
        document.getElementById('pm-price').textContent = price + ' €';
        document.getElementById('pm-img').src = img;

        const modal = bootstrap.Modal.getOrCreateInstance(modalEl);
        modal.show();
      });
    });
  })();
</script>