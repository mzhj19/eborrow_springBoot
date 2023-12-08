package com.mzhj19.eborrow.repository;

import com.mzhj19.eborrow.model.District;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository<District, Long> {
    District findDistrictByDistrictName(String name);
}
