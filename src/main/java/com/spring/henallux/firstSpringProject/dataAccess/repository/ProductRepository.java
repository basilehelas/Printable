package com.spring.henallux.firstSpringProject.dataAccess.repository;

import com.spring.henallux.firstSpringProject.dataAccess.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    // par prix et catégorie, tri par nom
    List<ProductEntity> findByPriceBetweenAndCategory_IdOrderByNameAsc(
            BigDecimal min, BigDecimal max, Integer categoryId);

    // par noms , tri par prix croissant
    List<ProductEntity> findByNameInOrderByPriceAsc(Collection<String> names);

    // Utilitaires fréquents
    List<ProductEntity> findByNameContainingIgnoreCaseOrderByNameAsc(String namePart);
    List<ProductEntity> findByCategory_IdOrderByNameAsc(Integer categoryId);
}
