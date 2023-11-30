package com.mzhj19.eborrow.serviceImpl;

import com.mzhj19.eborrow.dto.ProductDto;
import com.mzhj19.eborrow.model.Product;
import com.mzhj19.eborrow.model.lookup.ProductCategory;
import com.mzhj19.eborrow.model.User;
import com.mzhj19.eborrow.repository.ProductCategoryRepository;
import com.mzhj19.eborrow.repository.ProductRepository;
import com.mzhj19.eborrow.repository.UserRepository;
import com.mzhj19.eborrow.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;


    @Override
    public Product save(ProductDto productDto) {
        User currentUser = userRepository.findByEmail("mzhj19@gmail.com");
        ProductCategory categoryFromDB = productCategoryRepository.findById(productDto.getCategory())
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));
        //if(categoryFromDB.isEmpty())  return  null;

        Product product = productRepository.save(Product.builder()
                .name(productDto.getName())
                .category(categoryFromDB)
                .description(productDto.getDescription())
                .image1(productDto.getImage1())
                .borrowType(productDto.getBorrowType())
                .perUnitPrice(productDto.getPerUnitPrice())
                .mobileNo(productDto.getMobileNo())
                .ownerId(currentUser)
                .division(productDto.getDivision())
                .district(productDto.getDistrict())
                .subDistrict(productDto.getSubDistrict())
                .status(productDto.getStatus())
                .build());

        return product;
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findProductById(id);
    }

 /*   @Override
    public List<Product> findProductByDivision(String division) {
        return productRepository.findProductByDivision(division);
    }*/

    @Override
    public List<Product> findProductByOwner(String mail) {
        return productRepository.findByOwnerIdEma(mail);
    }

    @Override
    public Page<Product> getAllProduct(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product updateProduct(Long id, ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            return null;
        }
        ProductCategory categoryFromDB = productCategoryRepository.findById(productDto.getCategory())
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));

        Product existingProduct = optionalProduct.get();
        existingProduct.setName(productDto.getName());
        existingProduct.setCategory(categoryFromDB);
        existingProduct.setDescription(productDto.getDescription());
        existingProduct.setImage1(productDto.getImage1());
        existingProduct.setBorrowType(productDto.getBorrowType());
        existingProduct.setPerUnitPrice(productDto.getPerUnitPrice());
        existingProduct.setMobileNo(productDto.getMobileNo());
        existingProduct.setDivision(productDto.getDivision());
        existingProduct.setDistrict(productDto.getDistrict());
        existingProduct.setSubDistrict(productDto.getSubDistrict());

        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }


}
