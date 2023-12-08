package com.mzhj19.eborrow.repository;

import com.mzhj19.eborrow.model.Division;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DivisionRepository extends JpaRepository<Division, Long> {
    Division findDivisionByDivisionName(String name);
}
