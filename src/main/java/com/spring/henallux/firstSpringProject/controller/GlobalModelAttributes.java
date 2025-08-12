package com.spring.henallux.firstSpringProject.controller;

import com.spring.henallux.firstSpringProject.dataAccess.dao.CategoryTranslationDataAccess;
import com.spring.henallux.firstSpringProject.model.CategoryLabel;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Locale;

@ControllerAdvice
public class GlobalModelAttributes {

    private final CategoryTranslationDataAccess categoryTx;

    public GlobalModelAttributes(CategoryTranslationDataAccess categoryTx) {
        this.categoryTx = categoryTx;
    }
    @ModelAttribute("categoriesNames")
    public List<CategoryLabel> categoriesNames(
            @RequestParam(value = "lang", required = false) String lang,
            @RequestParam(value = "locale", required = false) String localeParam) {

        String language = ((lang != null && !lang.isEmpty())
                ? lang
                :  "fr");

        return categoryTx.listCategoriesWithNames(language);
    }
}
