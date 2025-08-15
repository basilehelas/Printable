package com.spring.henallux.firstSpringProject.service;

import com.spring.henallux.firstSpringProject.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
@SessionScope
public class CartService {
    private final Map<Product, Integer> items = new HashMap<>();

    public void addProduct(Product product) {
        items.merge(product, 1, Integer::sum);
    }

    public void removeProduct(Product product) {
        items.computeIfPresent(product, (p, qty) -> (qty > 1) ? qty - 1 : null);
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public int getCount() {
        return items.values().stream().mapToInt(Integer::intValue).sum();
    }

    public BigDecimal getTotal() {
        return items.entrySet().stream()
                .map(e -> e.getKey().getPrice().multiply(BigDecimal.valueOf(e.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void clear() {
        items.clear();
    }
}
