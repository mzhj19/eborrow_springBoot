package com.mzhj19.eborrow.model.lookup;

import com.mzhj19.eborrow.model.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "division")
@Getter
@Setter
@RequiredArgsConstructor
public class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String divisionName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "division")
    private Set<District> districts;
}
