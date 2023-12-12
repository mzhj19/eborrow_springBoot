package com.mzhj19.eborrow.serviceImpl;

import com.mzhj19.eborrow.dto.ProductDto;
import com.mzhj19.eborrow.model.*;
import com.mzhj19.eborrow.model.BorrowType;
import com.mzhj19.eborrow.model.ProductCategory;
import com.mzhj19.eborrow.repository.*;
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

    @Autowired
    private DivisionRepository divisionRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private SubDistrictRepository subDistrictRepository;

    @Autowired
    private BorrowTypeRepository borrowTypeRepository;


    @Override
    public Product save(ProductDto productDto) {
        User currentUser = userRepository.findByEmail("mzhj19@gmail.com");
        ProductCategory categoryFromDB = productCategoryRepository.findById((Long)productDto.getCategory())
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));
        //if(categoryFromDB.isEmpty())  return  null;

        BorrowType borrowTypeFromDB = borrowTypeRepository.getBorrowTypeByBorrowTypeName(productDto.getBorrowType());

        Division divisionFromDb = divisionRepository.findDivisionByDivisionName(productDto.getDivision());
        District districtFromDb = districtRepository.findDistrictByDistrictName(productDto.getDistrict());
        SubDistrict subDistrictFromDb = subDistrictRepository.findSubDistrictBySubDistrictName(productDto.getSubDistrict());

        Product product = productRepository.save(Product.builder()
                .name(productDto.getName())
                .category(categoryFromDB)
                .description(productDto.getDescription())
                .image1(productDto.getImage1())
                .borrowType(borrowTypeFromDB)
                .perUnitPrice(productDto.getPerUnitPrice())
                .mobileNo(productDto.getMobileNo())
                .ownerId(currentUser)
                .division(divisionFromDb)
                .district(districtFromDb)
                .subDistrict(subDistrictFromDb)
                /*.status(productDto.getStatus())*/
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
    public Page<ProductCategory> getAllProductByCategoryId(Long id, Pageable pageable) {
        return productCategoryRepository.getAllProductByCategoryId(id, pageable);
    }

    @Override
    public List<ProductCategory> getProductCategory() {
        return productCategoryRepository.findAll();
    }

    @Override
    public Product updateProduct(Long id, ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            return null;
        }
        ProductCategory categoryFromDB = productCategoryRepository.findById(productDto.getCategory())
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));

        BorrowType borrowTypeFromDB = borrowTypeRepository.getBorrowTypeByBorrowTypeName(productDto.getBorrowType());

        Division divisionFromDb = divisionRepository.findDivisionByDivisionName(productDto.getDivision());
        District districtFromDb = districtRepository.findDistrictByDistrictName(productDto.getDistrict());
        SubDistrict subDistrictFromDb = subDistrictRepository.findSubDistrictBySubDistrictName(productDto.getSubDistrict());


        Product existingProduct = optionalProduct.get();
        existingProduct.setName(productDto.getName());
        existingProduct.setCategory(categoryFromDB);
        existingProduct.setDescription(productDto.getDescription());
        existingProduct.setImage1(productDto.getImage1());
        existingProduct.setBorrowType(borrowTypeFromDB);
        existingProduct.setPerUnitPrice(productDto.getPerUnitPrice());
        existingProduct.setMobileNo(productDto.getMobileNo());
        existingProduct.setDivision(divisionFromDb);
        existingProduct.setDistrict(districtFromDb);
        existingProduct.setSubDistrict(subDistrictFromDb);

        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> getProductByCategoryId(Long id, Pageable pageable) {
        return productRepository.findProductByCategoryId(id, pageable);
    }


}
