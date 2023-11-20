package com.mzhj19.eborrow.service;

import com.mzhj19.eborrow.dto.ProductDto;
import com.mzhj19.eborrow.model.Product;

import java.util.List;

public interface ProductService {
    Product save(ProductDto productDto);

    List<Product> getProductById(Long id);
    List<Product> findProductByDivision(String division);
}
