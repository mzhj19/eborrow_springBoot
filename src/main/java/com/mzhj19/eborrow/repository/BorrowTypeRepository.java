package com.mzhj19.eborrow.repository;

import com.mzhj19.eborrow.model.BorrowType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowTypeRepository extends JpaRepository<BorrowType, Long> {
    BorrowType getBorrowTypeByBorrowTypeName(String name);
}
