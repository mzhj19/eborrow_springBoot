package com.mzhj19.eborrow.repository;

import com.mzhj19.eborrow.model.lookup.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
