package com.spring.henallux.firstSpringProject.controller;

import com.spring.henallux.firstSpringProject.dataAccess.dao.ProductDataAccess;
import com.spring.henallux.firstSpringProject.model.Product;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductDataAccess products;

    public ProductController(ProductDataAccess products) {
        this.products = products;
    }

    @GetMapping
    public String list(@RequestParam(required = false) Integer categoryId, Model model) {
        List<Product> list = (categoryId == null)
                ? products.getAll()
                : products.getByCategory(categoryId);

        model.addAttribute("products", list);
        model.addAttribute("pageTitle", "Products");
        model.addAttribute("currentCategoryId", categoryId);
        return "integrated:products";
    }

    @GetMapping("/category/{categoryId}")
    public String listByCategory(@PathVariable Integer categoryId, Model model) {
        List<Product> list = products.getByCategory(categoryId);

        model.addAttribute("products", list);
        model.addAttribute("pageTitle", "Products");
        model.addAttribute("currentCategoryId", categoryId);
        return "integrated:products";
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> image(@PathVariable Integer id) {
        return ((com.spring.henallux.firstSpringProject.dataAccess.dao.ProductDAO) products)
                .getImageBytes(id)
                .map(bytes -> ResponseEntity.ok()
                        .header(HttpHeaders.CACHE_CONTROL, "max-age=86400")
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(bytes))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Product getOneAsJson(@PathVariable Integer id) {
        // si ton DAO expose getOne(id), sinon adapte
        return products.getAll().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
