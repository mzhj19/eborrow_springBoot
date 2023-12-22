package com.mzhj19.eborrow.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EborrowApiException.class)
    public ResponseEntity<ErrorObject> handleEborrowApiException(EborrowApiException ex, WebRequest request) {

        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(new Date());

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EborrowApiValidationException.class)
    public ResponseEntity<ValidationErrorObject> handleEborrowApiValidationException(EborrowApiValidationException ex, WebRequest request) {

        ValidationErrorObject validationErrorObject = new ValidationErrorObject();

        validationErrorObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
        validationErrorObject.setErrors(ex.errors);
        validationErrorObject.setTimestamp(new Date());

        return new ResponseEntity<ValidationErrorObject>(validationErrorObject, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorObject> handleAccessDeniedException(AccessDeniedException exception,
                                                                    WebRequest webRequest){
        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(exception.getMessage());
        errorObject.setTimestamp(new Date());
        return new ResponseEntity<>(errorObject, HttpStatus.UNAUTHORIZED);
    }
}
