package com.spring.henallux.firstSpringProject.dataAccess.dao;

import com.spring.henallux.firstSpringProject.dataAccess.entity.DiscountEntity;
import com.spring.henallux.firstSpringProject.dataAccess.repository.DiscountRepository;
import com.spring.henallux.firstSpringProject.dataAccess.util.DiscountConverter;
import com.spring.henallux.firstSpringProject.model.Discount;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class DiscountDAO implements DiscountDataAccess {

    private final DiscountRepository repository;
    private final DiscountConverter converter;

    public DiscountDAO(DiscountRepository repository, DiscountConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public List<Discount> findAll() {
        return repository.findAll()
                .stream()
                .map(converter::discountEntityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public Discount findByCode(String code) {
        return converter.discountEntityToModel(repository.findByCode(code));
    }

    @Override
    public Discount save(Discount discount) {
        DiscountEntity saved = repository.save(converter.discountModelToEntity(discount));
        return converter.discountEntityToModel(saved);
    }

    @Override
    public void deleteByCode(String code) {
        repository.deleteByCode(code);
    }

    @Override
    public boolean existsByCode(String code) {
        return repository.existsByCode(code);
    }
}
