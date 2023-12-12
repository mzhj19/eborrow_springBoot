package com.mzhj19.eborrow.service;

import com.mzhj19.eborrow.model.lookup.LookupDistrict;
import com.mzhj19.eborrow.model.lookup.LookupDivision;
import com.mzhj19.eborrow.model.lookup.LookupSubDistrict;

import java.util.List;

public interface LookupService {
    List<LookupDivision> getDivision();

    List<LookupDistrict> getDistrict(Short lookupDivisionId);

    List<LookupSubDistrict> getSubDistrict(Short lookupDistrictId);
}
