package com.mzhj19.eborrow.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mzhj19.eborrow.dto.ResponseErrorData;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;


public class EborrowAPIException extends RuntimeException {

    @JsonProperty("status")
    private Boolean status;

    @JsonProperty("statusCode")
    private Integer statusCode;

//    @JsonProperty("message")
//    private String message;

    @JsonProperty("message")
    private String message;


/*    public EborrowAPIException(Integer statusCode, String message) {
        this.status = false;
        this.statusCode = statusCode;
        this.message = message;
    }*/

    public EborrowAPIException(Integer statusCode, String message) {
        this.status = false;
        this.statusCode = statusCode;
        this.message = message;
    }
}