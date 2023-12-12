package com.mzhj19.eborrow.controller;


import com.mzhj19.eborrow.constant.ResponseMessageConstants;
import com.mzhj19.eborrow.constant.WebApiUrlConstants;
import com.mzhj19.eborrow.data.response.ResponseErrorData;
import com.mzhj19.eborrow.data.response.ResponseSuccessData;
import com.mzhj19.eborrow.model.Product;
import com.mzhj19.eborrow.model.lookup.LookupDistrict;
import com.mzhj19.eborrow.model.lookup.LookupDivision;
import com.mzhj19.eborrow.model.lookup.LookupSubDistrict;
import com.mzhj19.eborrow.service.LookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(WebApiUrlConstants.API_URI_PREFIX + "/lookup")
public class LookupController {
    @Autowired
    private LookupService lookupService;


    @RequestMapping(value = "/getDivision", method = RequestMethod.GET)
    public ResponseEntity<?> getDivision() throws Exception {
        List<LookupDivision> lookupDivisions = lookupService.getDivision();

        if (lookupDivisions.isEmpty()) {
            return new ResponseEntity<>(new ResponseErrorData<>(HttpStatus.NOT_FOUND.value(), ResponseMessageConstants.DATA_NOT_FOUND), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseSuccessData<>(ResponseMessageConstants.DATA_FOUND, lookupDivisions), HttpStatus.OK);
    }

    @RequestMapping(value = "/getDistrict", method = RequestMethod.GET)
    public ResponseEntity<?> getDistrict(@RequestParam("id") Short lookupDivisionId) throws Exception {
        List<LookupDistrict> lookupDistricts = lookupService.getDistrict(lookupDivisionId);

        if (lookupDistricts.isEmpty()) {
            return new ResponseEntity<>(new ResponseErrorData<>(HttpStatus.NOT_FOUND.value(), ResponseMessageConstants.DATA_NOT_FOUND), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseSuccessData<>(ResponseMessageConstants.DATA_FOUND, lookupDistricts), HttpStatus.OK);
    }

    @RequestMapping(value = "/getSubDistrict", method = RequestMethod.GET)
    public ResponseEntity<?> getSubDistrict(@RequestParam("id") Short lookupDistrictId) throws Exception {
        List<LookupSubDistrict> lookupSubDistricts = lookupService.getSubDistrict(lookupDistrictId);

        if (lookupSubDistricts.isEmpty()) {
            return new ResponseEntity<>(new ResponseErrorData<>(HttpStatus.NOT_FOUND.value(), ResponseMessageConstants.DATA_NOT_FOUND), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseSuccessData<>(ResponseMessageConstants.DATA_FOUND, lookupSubDistricts), HttpStatus.OK);
    }

}
