package com.mzhj19.eborrow.model.lookup;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sub_district")
@Getter
@Setter
@RequiredArgsConstructor
public class SubDistrict {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String subDistrictName;

    @ManyToOne
    @JoinColumn(name = "district_id", nullable = false)
    @JsonIgnoreProperties("sub_district")
    private District district;
}
