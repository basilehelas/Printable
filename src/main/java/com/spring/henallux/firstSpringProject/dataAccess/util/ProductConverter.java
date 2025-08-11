package com.spring.henallux.firstSpringProject.dataAccess.util;

import com.spring.henallux.firstSpringProject.dataAccess.entity.CategoryEntity;
import com.spring.henallux.firstSpringProject.dataAccess.entity.ProductEntity;
import com.spring.henallux.firstSpringProject.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    public Product toModel(ProductEntity e) {
        if (e == null) return null;
        Product m = new Product();
        m.setId(e.getId());
        m.setName(e.getName());
        m.setPrice(e.getPrice());
        m.setDescription(e.getDescription());
        m.setImageUrl(e.getImageUrl());
        m.setCategoryId(e.getCategory() != null ? e.getCategory().getId() : null);
        return m;
    }

    public ProductEntity toEntity(Product m, CategoryEntity category) {
        if (m == null) return null;
        ProductEntity e = new ProductEntity();
        e.setId(m.getId());
        e.setName(m.getName());
        e.setPrice(m.getPrice());
        e.setDescription(m.getDescription());
        e.setImageUrl(m.getImageUrl());
        e.setCategory(category); // peut être null
        return e;
    }
}
