package com.spring.henallux.firstSpringProject.model;

import java.math.BigDecimal;

public class Product {
    private final Integer id;
    private final String name;
    private final BigDecimal price;
    private final String description;
    private final Integer categoryId;

    public Product(Integer id, String name, BigDecimal price, String description, Integer categoryId) {
        this.id = id; this.name = name; this.price = price;
        this.description = description; this.categoryId = categoryId;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getPrice() { return price; }
    public String getDescription() { return description; }
    public Integer getCategoryId() { return categoryId; }
}
