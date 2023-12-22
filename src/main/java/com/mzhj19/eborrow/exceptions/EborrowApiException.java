package com.mzhj19.eborrow.exceptions;

public class EborrowApiException extends RuntimeException {
    private static final long serialVerisionUID = 1;

    public EborrowApiException(String message) {
        super(message);
    }
}
