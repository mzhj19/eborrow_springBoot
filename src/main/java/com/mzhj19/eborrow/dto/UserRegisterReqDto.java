package com.mzhj19.eborrow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterReqDto implements Serializable {
    private static final Long serialVersionUID = 1L;

    @NotBlank(message = "Email CANNOT BE BLANK")
    @Pattern(regexp = "^[\\w\\.-]+@[a-zA-Z\\d\\.-]+\\.[a-zA-Z]{2,}$", message = "INVALID EMAIL FORMAT!")
    private String email;

    @NotBlank(message = "MOBILE NO CANNOT BE BLANK")
    @Pattern(regexp = "^[0-9]+$", message = "INVALID MOBILE NUMBER")
    private String mobileNo;

    @NotBlank(message = "PASSWORD CANNOT BE BLANK")
    private String password;

    @NotBlank(message = "CONFIRMEDPASSWORD CANNOT BE BLANK")
    private String confirmedPassword;
}
