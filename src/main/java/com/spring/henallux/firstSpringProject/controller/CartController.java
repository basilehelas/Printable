package com.spring.henallux.firstSpringProject.controller;

import com.spring.henallux.firstSpringProject.dataAccess.dao.ProductDataAccess;
import com.spring.henallux.firstSpringProject.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final ProductDataAccess products;

    @Autowired
    public CartController(CartService cartService, ProductDataAccess products) {
        this.cartService = cartService;
        this.products = products;
    }

    @PostMapping("/add/{id}")
    public String addToCart(@PathVariable Integer id, HttpServletRequest request) {
        products.getAll().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .ifPresent(cartService::addProduct);
        System.out.println("Adding product " + id + " to the cart");
        System.out.println(cartService.getItems());
        System.out.println(cartService.getTotal());

        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/products");
    }
}