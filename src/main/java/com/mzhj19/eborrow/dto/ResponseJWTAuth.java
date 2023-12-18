package com.mzhj19.eborrow.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseJWTAuth {
    private String type = "Bearer";
    private String Token;

}
