package com.spring.henallux.firstSpringProject.controller;

import com.spring.henallux.firstSpringProject.dataAccess.dao.DiscountDataAccess;
import com.spring.henallux.firstSpringProject.model.Discount;
import com.spring.henallux.firstSpringProject.service.CartService;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

@Controller
@RequestMapping("/payement")
public class PaymentController {

    private final CartService cartService;
    private final DiscountDataAccess discountDao;
    private final MessageSource messageSource; // i18n


    private static final String PAYPAL_URL = "https://www.sandbox.paypal.com/cgi-bin/webscr";
    private static final String BUSINESS = "sb-vdqmk45344677@business.example.com";
    private static final String CLIENT_ID = "AWZsApF6HK9os6yWvkyoL4-wIwpmAtZWv9SQju2i3iuxri0MyEEcB4Ku36lBxbnGWsRtvdh9SWOuvJR5";
    private static final String RETURN_URL = "http://localhost:8082/printable/payement/success";
    private static final String CANCEL_URL = "http://localhost:8082/printable/payement/failed";
    private static final String CURRENCY = "EUR";

    private static final String SESSION_COUPON = "appliedCoupon";

    public PaymentController(CartService cartService, DiscountDataAccess discountDao, MessageSource messageSource) {
        this.cartService = cartService;
        this.discountDao = discountDao;
        this.messageSource = messageSource;
    }

    @GetMapping
    public String paymentForm(Model model, HttpSession session) {

        model.addAttribute("paypalUrl", PAYPAL_URL);
        model.addAttribute("business", BUSINESS);
        model.addAttribute("clientId", CLIENT_ID);
        model.addAttribute("returnUrl", RETURN_URL);
        model.addAttribute("cancelUrl", CANCEL_URL);
        model.addAttribute("currency", CURRENCY);

        // total toujours à 2 décimales
        BigDecimal total = cartService.getTotal().setScale(2, RoundingMode.HALF_UP);
        model.addAttribute("paymentCartItems", cartService.getItems());

        String appliedCode = (String) session.getAttribute(SESSION_COUPON);
        BigDecimal discountRate = BigDecimal.ZERO;
        BigDecimal discountAmount = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        BigDecimal finalTotal = total;

        if (appliedCode != null) {
            Discount d = discountDao.findByCode(appliedCode);

            if (d != null && Boolean.TRUE.equals(d.isValid())) {
                discountRate = d.getDiscount();

                discountAmount = total.multiply(discountRate)
                        .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)
                        .setScale(2, RoundingMode.HALF_UP);

                finalTotal = total.subtract(discountAmount)
                        .setScale(2, RoundingMode.HALF_UP);

                model.addAttribute("discountRate", discountRate);
                model.addAttribute("discountAmount", discountAmount);
            } else {
                session.removeAttribute(SESSION_COUPON);
                appliedCode = null;
            }
        }

        model.addAttribute("amount", finalTotal);
        model.addAttribute("subtotal", total);
        model.addAttribute("appliedCode", appliedCode);

        //cartService.clear();


        return "integrated:payement";
    }


    @GetMapping("/success")
    public String paymentSuccess(Model model) {

        cartService.clear();

        model.addAttribute("message", "Paiement réussi !");
        return "integrated:payment-success"; // à créer dans /WEB-INF/jsp/
    }

    @GetMapping("/failed")
    public String paymentFailed(Model model) {
        // Tu pourras gérer les erreurs, logs, etc.
        model.addAttribute("message", "Le paiement a échoué ou a été annulé.");
        return "integrated:payment-failed"; // à créer dans /WEB-INF/jsp/
    }




    @PostMapping
    public String applyCoupon(@RequestParam(value = "code", required = false) String rawCode,
                              HttpSession session,
                              RedirectAttributes ra,
                              Locale locale) {
        String code = (rawCode == null) ? "" : rawCode.trim();

        if (code.isEmpty()) {
            ra.addFlashAttribute("error", messageSource.getMessage("coupon.error.empty", null, locale));
            return "redirect:/payement";
        }

        Discount d = discountDao.findByCode(code);
        boolean invalid =
                d == null
                        || !Boolean.TRUE.equals(d.isValid())
                        || d.getDiscount() == null
                        || d.getDiscount().compareTo(BigDecimal.ZERO) < 0
                        || d.getDiscount().compareTo(new BigDecimal("100")) > 0;

        if (invalid) {
            ra.addFlashAttribute("error", messageSource.getMessage("coupon.error.invalid", null, locale));
            session.removeAttribute(SESSION_COUPON);
            return "redirect:/payement";
        }

        session.setAttribute(SESSION_COUPON, code);
        ra.addFlashAttribute("success", messageSource.getMessage("coupon.success", null, locale));
        return "redirect:/payement";
    }
}
