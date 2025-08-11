package com.spring.henallux.firstSpringProject.dataAccess.dao;

import com.spring.henallux.firstSpringProject.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ProductDataAccess {
    ArrayList<String> getProductNames();
    List<Product> getAll();
    Optional<Product> getById(Integer id);
    long count();

    // Requêtes personnalisées (version modèle)
    List<Product> getByPriceRangeAndCategory(BigDecimal min, BigDecimal max, Integer categoryId);
    List<Product> getByNamesOrderedByPrice(ArrayList<String> names);
}
