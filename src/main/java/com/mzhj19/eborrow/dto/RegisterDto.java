package com.mzhj19.eborrow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterDto implements Serializable {
    private static final Long serialVersionUID = 1L;

    @NotBlank(message = "EMAIL CANNOT BE BLANK!")
    @Pattern(regexp = "^[\\w\\.-]+@[a-zA-Z\\d\\.-]+\\.[a-zA-Z]{2,}$", message = "INVALID EMAIL!")
    private String email;

/*    @NotBlank(message = "MOBILE NO CANNOT BE BLANK")
    @Pattern(regexp = "^[0-9]+$", message = "INVALID MOBILE NUMBER")
    private String mobileNo;*/

    @NotBlank(message = "PASSWORD CANNOT BE BLANK!")
    @Size(min = 5, message = "MINIMUM 5 CHARACTERS!")
    private String password;
}