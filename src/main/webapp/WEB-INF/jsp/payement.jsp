<%@ include file="include/importTags.jsp" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="checkout-card">
  <h2 class="checkout-title">Paiement avec PayPal</h2>

  <c:if test="${not empty success}">
    <div class="form-alert" style="background:#eaf9f0;color:#106b35;border:1px solid #c9efd7;">
      ${success}
    </div>
  </c:if>
  <c:if test="${not empty error}">
    <div class="form-alert form-alert-error">${error}</div>
  </c:if>

  <h3 class="m-0 mb-3" style="font-size:1.1rem;color:#222;">Récapitulatif de la commande</h3>

  <c:choose>
    <c:when test="${empty paymentCartItems}">
      <p class="text-muted">Votre panier est vide.</p>
    </c:when>
    <c:otherwise>
      <div style="overflow-x:auto;">
        <table class="table-clean" style="width:100%;border-collapse:collapse;">
          <thead>
          <tr style="background:#f9f9f9;">
            <th style="text-align:left;padding:8px;">Produit</th>
            <th style="text-align:center;padding:8px;">Qté</th>
            <th style="text-align:right;padding:8px;">Prix unitaire</th>
            <th style="text-align:right;padding:8px;">Total</th>
          </tr>
          </thead>
          <tbody>
            <c:forEach var="entry" items="${paymentCartItems}">
              <tr>
                <td style="padding:8px;"><c:out value="${entry.key.name}"/></td>
                <td style="text-align:center;padding:8px;"><c:out value="${entry.value}"/></td>
                <td style="text-align:right;padding:8px;">€ <fmt:formatNumber value="${entry.key.price}" minFractionDigits="2"/></td>
                <td style="text-align:right;padding:8px;">€ <fmt:formatNumber value="${entry.key.price * entry.value}" minFractionDigits="2"/></td>
              </tr>
            </c:forEach>
          </tbody>
          <tfoot>
          <tr>
            <th colspan="3" style="text-align:right;padding:8px;">Sous-total</th>
            <th style="text-align:right;padding:8px;">€ <fmt:formatNumber value="${subtotal}" minFractionDigits="2"/></th>
          </tr>

          <c:if test="${not empty appliedCode}">
            <tr>
              <th colspan="3" style="text-align:right;padding:8px;">
                Remise
                (<span style="background:#eef2ff;color:#003b7a;padding:2px 6px;border-radius:6px;font-size:0.9em;">
                  <c:out value="${appliedCode}"/>
                </span>)
              </th>
              <th style="text-align:right;padding:8px;color:#b91c1c;">− € <fmt:formatNumber value="${discountAmount}" minFractionDigits="2"/></th>
            </tr>
          </c:if>

          <tr style="border-top:2px solid #003b7a;">
            <th colspan="3" style="text-align:right;padding:10px;font-size:1.1rem;">Total à payer</th>
            <th style="text-align:right;padding:10px;font-size:1.2rem;color:#003b7a;font-weight:bold;">
              € <fmt:formatNumber value="${amount}" minFractionDigits="2"/>
            </th>
          </tr>
          </tfoot>
        </table>
      </div>
    </c:otherwise>
  </c:choose>

  <div class="mt-3">
    <h3 class="m-0 mb-3" style="font-size:1.1rem;color:#222;">Code promo</h3>
    <form method="post" action="${ctx}/payement" class="inline-form" style="display:flex;gap:8px;">
      <input type="text" name="code" class="form-input" maxlength="50"
             placeholder="Saisissez votre code"
             value="${appliedCode != null ? appliedCode : ''}" style="flex:1;">
      <button type="submit" class="form-button btn-outline">Appliquer</button>
    </form>
  </div>

  <div class="mt-4" style="text-align:center;">
    <form method="post" action="${paypalUrl}">
      <input type="hidden" name="cmd" value="_xclick"/>
      <input type="hidden" name="business" value="${business}"/>
      <input type="hidden" name="client_id" value="${clientId}"/>
      <input type="hidden" name="currency_code" value="${currency}"/>
      <input type="hidden" name="return" value="${returnUrl}"/>
      <input type="hidden" name="cancel_return" value="${cancelUrl}"/>

      <input type="hidden" name="item_name" value="Votre Commande"/>
      <input type="hidden" name="amount" value="${amount}"/>

      <button type="submit" class="form-button" style="font-size:1rem;padding:12px 20px;margin-top:12px;">
        💳 Payer avec PayPal
      </button>
    </form>
  </div>
</div>