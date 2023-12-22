package com.mzhj19.eborrow.exceptions;

import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class ValidationErrorObject {
    private Integer statusCode;
    Map<String, String> errors;
    private Date timestamp;
}