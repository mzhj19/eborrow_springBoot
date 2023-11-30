package com.mzhj19.eborrow.repository;

import com.mzhj19.eborrow.dto.ProductDto;
import com.mzhj19.eborrow.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(ProductDto productDto);

    //@Query("SELECT c FROM product c where c.ownerId = ?1")
    Product findProductById(Long id);

    List<Product> findProductByDivision(String division);

    @Query(value = "SELECT * FROM products p " +
            "WHERE p.owner_id IN (SELECT id FROM users u WHERE u.email = :email)", nativeQuery = true)
    List<Product> findByOwnerIdEma(@Param("email") String email);
}
