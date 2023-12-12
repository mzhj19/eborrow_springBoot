package com.mzhj19.eborrow.model.lookup;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mzhj19.eborrow.model.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "lookup_division")
@Getter
@Setter
@RequiredArgsConstructor
public class LookupDivision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Short id;

    @Column(name = "name")
    private String name;

    @JsonIgnore /// pore add korechi
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lookupDivision")
    private Set<LookupDistrict> lookupDistrict;
}
