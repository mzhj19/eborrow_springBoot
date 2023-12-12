package com.mzhj19.eborrow.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mzhj19.eborrow.model.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "borrowType")
@Getter
@Setter
@RequiredArgsConstructor

public class BorrowType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String borrowTypeName;

    @JsonIgnore /// pore add korechi
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "borrowType")
    private Set<Product> products;
}
