package com.mzhj19.eborrow.dto;

import com.mzhj19.eborrow.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto implements Serializable {
    private static final Long serialVersionUID = 1L;

    @NotBlank(message = "NAME CAN NOT BE BLANK")
    private String name;

    @NotBlank(message = "CATEGORY CANNOT BE BLANK")
    private String category;

    @NotBlank(message = "DESCRIPTION CAN NOT BE BLANK")
    private String description;

    // TO DO 5 pictures
    private byte[] image1;

    /*private byte[] image2;
    private byte[] image3;
    private byte[] image4;*/




    @NotBlank(message = "PER UNIT PRICE CAN NOT BE BLANK")
    private String perUnitPrice;

    @NotBlank(message = "BORROW TYPE CAN NOT BE BLANK")
    private String borrowType;

    @NotBlank(message = "DIVISION CAN NOT BE BLANK")
    private String division;

    @NotBlank(message = "DISTRICT CAN NOT BE BLANK")
    private String district;

    @NotBlank(message = "SUB DISTRICT CAN NOT BE BLANK")
    private String subDistrict;

    private String status;
}
