package com.mzhj19.eborrow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "sub_district")
@Getter
@Setter
@Data
@RequiredArgsConstructor
public class SubDistrict {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String subDistrictName;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subDistrict")
    private Set<Product> products;

/*    @ManyToOne
    @JoinColumn(name = "district_id", nullable = false)
    @JsonIgnoreProperties("sub_district")
    private District district;*/
}
