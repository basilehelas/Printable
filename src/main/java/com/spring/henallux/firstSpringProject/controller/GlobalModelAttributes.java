package com.spring.henallux.firstSpringProject.controller;

import com.spring.henallux.firstSpringProject.dataAccess.dao.CategoryTranslationDataAccess;
import com.spring.henallux.firstSpringProject.model.CategoryLabel;
import com.spring.henallux.firstSpringProject.service.CartService;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Locale;

@ControllerAdvice
public class GlobalModelAttributes {

    private final CategoryTranslationDataAccess categoryTx;
    private final CartService cartService;

    public GlobalModelAttributes(CategoryTranslationDataAccess categoryTx, CartService cartService) {
        this.categoryTx = categoryTx;
        this.cartService = cartService;
    }
    @ModelAttribute("categoriesNames")
    public List<CategoryLabel> categoriesNames(
            @RequestParam(value = "lang", required = false) String lang
            ) {

        String language = ((lang != null && !lang.isEmpty())
                ? lang
                :  "fr");

        return categoryTx.listCategoriesWithNames(language);
    }

    @ModelAttribute("cartCount")
    public int cartCount() {
        return cartService.getCount();
    }
}
