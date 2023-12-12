package com.mzhj19.eborrow.model.lookup;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "lookup_district")
@Getter
@Setter
@RequiredArgsConstructor
public class LookupDistrict {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Short id;

    @Column(name = "name")
    private String name;

    @JsonIgnore /// pore add korechi
    @ManyToOne
    @JoinColumn(name = "lookup_division_id", nullable = false)
    @JsonIgnoreProperties("district")
    private LookupDivision lookupDivision;

    @JsonIgnore /// pore add korechi
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lookupDistrict")
    private Set<LookupSubDistrict> lookupSubDistrictSet;
}
