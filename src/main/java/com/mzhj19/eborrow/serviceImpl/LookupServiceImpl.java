package com.mzhj19.eborrow.serviceImpl;

import com.mzhj19.eborrow.model.lookup.LookupDistrict;
import com.mzhj19.eborrow.model.lookup.LookupDivision;
import com.mzhj19.eborrow.model.lookup.LookupSubDistrict;
import com.mzhj19.eborrow.repository.lookup.LookupDistrictRepository;
import com.mzhj19.eborrow.repository.lookup.LookupDivisionRepository;
import com.mzhj19.eborrow.repository.lookup.LookupSubDistrictRepository;
import com.mzhj19.eborrow.service.LookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LookupServiceImpl implements LookupService {
    @Autowired
    private LookupDivisionRepository lookupDivisionRepository;

    @Autowired
    private LookupDistrictRepository lookupDistrictRepository;

    @Autowired
    private LookupSubDistrictRepository lookupSubDistrictRepository;


    @Override
    public List<LookupDivision> getDivision() {
        return lookupDivisionRepository.findAll();
    }

    @Override
    public List<LookupDistrict> getDistrict(Short lookupDivisionId) {
        return lookupDistrictRepository.findLookupDistrictsByLookupDivisionId(lookupDivisionId);
    }

    @Override
    public List<LookupSubDistrict> getSubDistrict(Short lookupDistrictId) {
        return lookupSubDistrictRepository.findLookupSubDistrictsByLookupDistrictId(lookupDistrictId);
    }
}
