package com.mzhj19.eborrow.exceptions;

import java.util.HashMap;
import java.util.Map;

public class EborrowApiValidationException extends RuntimeException {
    private static final long serialVerisionUID = 1;
    Map<String, String> errors = new HashMap<>();

    public EborrowApiValidationException(String message) {
        super(message);
    }

    public EborrowApiValidationException(Map<String, String> errors) {
        this.errors = errors;
    }
}
