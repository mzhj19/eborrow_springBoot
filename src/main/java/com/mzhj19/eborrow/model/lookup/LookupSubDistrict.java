package com.mzhj19.eborrow.model.lookup;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "lookup_sub_district")
@Getter
@Setter
@RequiredArgsConstructor
public class LookupSubDistrict {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Short id;

    @Column(name = "name")
    private String name;

    @JsonIgnore /// pore add korechi
    @ManyToOne
    @JoinColumn(name = "lookup_district_id", nullable = false)
    @JsonIgnoreProperties("lookup_sub_district")
    private LookupDistrict lookupDistrict;
}
