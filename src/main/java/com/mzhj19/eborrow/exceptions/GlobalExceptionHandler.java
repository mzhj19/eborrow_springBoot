package com.mzhj19.eborrow.exceptions;


import com.mzhj19.eborrow.dto.ResponseErrorData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EborrowApiNotFoundException.class)
    public ResponseEntity<ResponseErrorData<String>> handleEborrowApiNotFoundException(EborrowApiNotFoundException ex, WebRequest request) {

        ResponseErrorData<String> responseErrorData = new ResponseErrorData<>(HttpStatus.NOT_FOUND.value(), ex.getMessage());

        return new ResponseEntity<>(responseErrorData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EborrowApiBadRequestException.class)
    public ResponseEntity<ResponseErrorData<String>> handleEborrowApiBadRequestException(EborrowApiBadRequestException ex, WebRequest request) {

        ResponseErrorData<String> responseErrorData = new ResponseErrorData<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage());

        return new ResponseEntity<>(responseErrorData, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EborrowApiValidationException.class)
    public ResponseEntity<ResponseErrorData<Map<String, String>>> handleEborrowApiValidationException(EborrowApiValidationException ex, WebRequest request) {

        ResponseErrorData<Map<String, String>> responseErrorData = new ResponseErrorData<>(HttpStatus.BAD_REQUEST.value(), ex.errors);

        return new ResponseEntity<>(responseErrorData, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseErrorData<String>> handleAccessDeniedException(AccessDeniedException ex, WebRequest webRequest){

        ResponseErrorData<String> responseErrorData = new ResponseErrorData<>(HttpStatus.NOT_FOUND.value(), ex.getMessage());

        return new ResponseEntity<>(responseErrorData, HttpStatus.UNAUTHORIZED);
    }
}
