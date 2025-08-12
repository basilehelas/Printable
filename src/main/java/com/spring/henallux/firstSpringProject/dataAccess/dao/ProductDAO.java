package com.spring.henallux.firstSpringProject.dataAccess.dao;

import com.spring.henallux.firstSpringProject.dataAccess.repository.ProductRepository;
import com.spring.henallux.firstSpringProject.dataAccess.util.ProductConverter;
import com.spring.henallux.firstSpringProject.model.Product;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(Transactional.TxType.SUPPORTS)
public class ProductDAO implements ProductDataAccess {

    private final ProductRepository repo;
    private final ProductConverter converter;

    public ProductDAO(ProductRepository repo, ProductConverter converter) {
        this.repo = repo;
        this.converter = converter;
    }

    @Override
    public List<Product> getAll() {
        return repo.findAll().stream().map(converter::toModel).collect(Collectors.toList());
    }

    @Override
    public List<Product> getByCategory(Integer categoryId) {
        return repo.findByCategoryIdOrderByNameAsc(categoryId)
                .stream().map(converter::toModel).collect(Collectors.toList());
    }

    //  image BLOB       z
    public Optional<byte[]> getImageBytes(Integer productId) {
        return repo.findById(productId).map(p -> p.getImage()); // LAZY: chargé ici seulement
    }
}
