package com.mzhj19.eborrow.service;

import com.mzhj19.eborrow.dto.ProductDto;
import com.mzhj19.eborrow.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product save(ProductDto productDto);

    Product getProductById(Long id);

    List<Product> findProductByDivision(String division);

    List<Product> findProductByOwner(String mail);

    Page<Product> getAllProduct(Pageable pageable);

    Product updateProduct(Long id, ProductDto productDto);

    void deleteProduct(Long id);
}
