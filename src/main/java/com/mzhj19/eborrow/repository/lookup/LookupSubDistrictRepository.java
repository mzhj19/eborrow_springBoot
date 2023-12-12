package com.mzhj19.eborrow.repository.lookup;

import com.mzhj19.eborrow.model.ProductCategory;
import com.mzhj19.eborrow.model.lookup.LookupSubDistrict;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LookupSubDistrictRepository extends JpaRepository<LookupSubDistrict, Short> {
    List<LookupSubDistrict> findLookupSubDistrictsByLookupDistrictId(Short lookupDistrictId);
}
