package com.mzhj19.eborrow.exceptions;

public class EborrowApiBadRequestException extends RuntimeException {
    private static final long serialVerisionUID = 1;

    public EborrowApiBadRequestException(String message) {
        super(message);
    }
}
