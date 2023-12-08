package com.mzhj19.eborrow.repository;

import com.mzhj19.eborrow.model.SubDistrict;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubDistrictRepository extends JpaRepository<SubDistrict, Long> {
    SubDistrict findSubDistrictBySubDistrictName(String name);
}
