package com.mzhj19.eborrow.exceptions;

public class EborrowApiNotFoundException extends RuntimeException {
    private static final long serialVerisionUID = 1;

    public EborrowApiNotFoundException(String message) {
        super(message);
    }
}
