package com.mzhj19.eborrow.repository;

import com.mzhj19.eborrow.dto.ProductDto;
import com.mzhj19.eborrow.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(ProductDto productDto);

    //@Query("SELECT c FROM product c where c.ownerId = ?1")
    List<Product> findProductById(Long id);
    List<Product> findProductByDivision(String division);
}
