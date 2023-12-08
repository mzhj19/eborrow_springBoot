package com.mzhj19.eborrow.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "district")
@Getter
@Setter
@RequiredArgsConstructor
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String districtName;

/*    @ManyToOne
    @JoinColumn(name = "division_id", nullable = false)
    @JsonIgnoreProperties("district")
    private Division division;*/

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "district")
    private Set<Product> products;
}
