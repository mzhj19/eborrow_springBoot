package com.mzhj19.eborrow.repository;

import com.mzhj19.eborrow.model.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    @Query(value = "SELECT * FROM product_category p\n" +
            "WHERE p.id =:id", nativeQuery = true)
    Page<ProductCategory> getAllProductByCategoryId(@Param("id") Long id, Pageable pageable);
}
