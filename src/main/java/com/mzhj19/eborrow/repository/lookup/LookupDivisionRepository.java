package com.mzhj19.eborrow.repository.lookup;

import com.mzhj19.eborrow.model.ProductCategory;
import com.mzhj19.eborrow.model.lookup.LookupDivision;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LookupDivisionRepository extends JpaRepository<LookupDivision, Short> {
}
