package com.spring.henallux.firstSpringProject.controller;

import com.spring.henallux.firstSpringProject.dataAccess.dao.DiscountDataAccess;
import com.spring.henallux.firstSpringProject.model.Discount;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final DiscountDataAccess discountDao;

    public AdminController(DiscountDataAccess discountDao) {
        this.discountDao = discountDao;
    }
    @GetMapping
    public String adminRoot() {
        return "redirect:/admin/promo-codes";
    }
    @GetMapping("/promo-codes")
    public String promoCodesPage(Model model) {
        model.addAttribute("promoCodes", discountDao.findAll());
        return "integrated:admin";
    }

    @PostMapping("/promo-codes")
    public String createPromoCode(@RequestParam("code") String code,
                                  @RequestParam("discount") BigDecimal discount,
                                  @RequestParam(value = "isValid", required = false) String isValid,
                                  RedirectAttributes ra) {
        boolean valid = (isValid != null);

        if (discountDao.existsByCode(code)) {
            ra.addFlashAttribute("error", "Ce code existe déjà.");
            return "redirect:/admin/promo-codes";
        }

        Discount d = new Discount(code.trim(), discount, valid);
        discountDao.save(d);
        ra.addFlashAttribute("success", "Code « " + code + " » créé.");
        return "redirect:/admin/promo-codes";
    }

    // Suppression par code
    @PostMapping("/promo-codes/{code}/delete")
    public String deletePromoCode(@PathVariable("code") String code,
                                  RedirectAttributes ra) {
        if (!discountDao.existsByCode(code)) {
            ra.addFlashAttribute("error", "Code introuvable : " + code);
            return "redirect:/admin/promo-codes";
        }
        discountDao.deleteByCode(code);
        ra.addFlashAttribute("success", "Code « " + code + " » supprimé.");
        return "redirect:/admin/promo-codes";
    }
}
