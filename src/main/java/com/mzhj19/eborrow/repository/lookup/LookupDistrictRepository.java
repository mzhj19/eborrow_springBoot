package com.mzhj19.eborrow.repository.lookup;

import com.mzhj19.eborrow.model.ProductCategory;
import com.mzhj19.eborrow.model.lookup.LookupDistrict;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LookupDistrictRepository extends JpaRepository<LookupDistrict, Short> {
    List<LookupDistrict> findLookupDistrictsByLookupDivisionId(Short lookupDivisionId);
}
