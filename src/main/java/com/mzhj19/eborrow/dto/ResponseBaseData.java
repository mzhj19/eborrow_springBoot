package com.mzhj19.eborrow.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
