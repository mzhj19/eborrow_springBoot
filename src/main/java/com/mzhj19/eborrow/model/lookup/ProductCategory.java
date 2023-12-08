package com.mzhj19.eborrow.model.lookup;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mzhj19.eborrow.model.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "product_category")
@Getter
@Setter
@RequiredArgsConstructor
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    @JsonIgnore /// pore add korechi
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private Set<Product> products;

}
