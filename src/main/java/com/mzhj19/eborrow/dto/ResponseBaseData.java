package com.mzhj19.eborrow.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ResponseBaseData<T> {
    @JsonProperty("status")
    private Boolean status;

    @JsonProperty("statusCode")
    private Integer statusCode;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private T data;

}
