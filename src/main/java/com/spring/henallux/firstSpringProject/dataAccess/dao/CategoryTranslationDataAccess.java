package com.spring.henallux.firstSpringProject.dataAccess.dao;

import com.spring.henallux.firstSpringProject.model.CategoryLabel;
import java.util.List;

public interface CategoryTranslationDataAccess {
    // Pas de fallback : on suppose que la langue existe partout
    List<CategoryLabel> listCategoriesWithNames(String languageId);
}
