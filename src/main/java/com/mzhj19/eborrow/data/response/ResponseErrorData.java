package com.mzhj19.eborrow.data.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseErrorData<T> {

    @JsonProperty("status")
    private Boolean status;

    @JsonProperty("statusCode")
    private Integer statusCode;

//    @JsonProperty("message")
//    private String message;

    @JsonProperty("message")
    private T message;


/*    public ResponseErrorData(Integer statusCode, String message) {
        this.status = false;
        this.statusCode = statusCode;
        this.message = message;
    }*/

    public ResponseErrorData(Integer statusCode, T message) {
        this.status = false;
        this.statusCode = statusCode;
        this.message = message;
    }

}
