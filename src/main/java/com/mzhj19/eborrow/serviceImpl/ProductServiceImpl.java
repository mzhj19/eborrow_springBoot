package com.mzhj19.eborrow.serviceImpl;

import com.mzhj19.eborrow.dto.ProductDto;
import com.mzhj19.eborrow.model.Product;
import com.mzhj19.eborrow.model.User;
import com.mzhj19.eborrow.repository.ProductRepository;
import com.mzhj19.eborrow.repository.UserRepository;
import com.mzhj19.eborrow.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public Product save(ProductDto productDto) {
        User currentUser = userRepository.findByEmail("mzhj19@gmail.com");

        Product product = productRepository.save(Product.builder()
                .name(productDto.getName())
                .category(productDto.getCategory())
                .description(productDto.getDescription())
                .image1(productDto.getImage1())
                .borrowType(productDto.getBorrowType())
                .perUnitPrice(productDto.getPerUnitPrice())
                .ownerId(currentUser)
                .division(productDto.getDivision())
                .district(productDto.getDistrict())
                .subDistrict(productDto.getSubDistrict())
                .status(productDto.getStatus())
                .build());

        return product;
    }

    @Override
    public List<Product> getProductById(Long id) {
        return productRepository.findProductById(id);
    }

    @Override
    public List<Product> findProductByDivision(String division) {
        return productRepository.findProductByDivision(division);
    }
}
