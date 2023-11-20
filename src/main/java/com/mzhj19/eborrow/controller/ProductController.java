package com.mzhj19.eborrow.controller;

import com.mzhj19.eborrow.constant.ResponseMessageConstants;
import com.mzhj19.eborrow.constant.WebApiUrlConstants;
import com.mzhj19.eborrow.data.response.ResponseErrorData;
import com.mzhj19.eborrow.data.response.ResponseSuccessData;
import com.mzhj19.eborrow.dto.ProductDto;
import com.mzhj19.eborrow.dto.UserRegisterReqDto;
import com.mzhj19.eborrow.model.Product;
import com.mzhj19.eborrow.model.User;
import com.mzhj19.eborrow.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(WebApiUrlConstants.API_URI_PREFIX + "/product")
public class ProductController {

    @Autowired
    private ProductService productService;


/*    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> register(@Valid @RequestBody ProductDto productDto, BindingResult bindingResult) throws Exception {

        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
            return new ResponseEntity<>(new ResponseErrorData<>(HttpStatus.BAD_REQUEST.value(), errors), HttpStatus.BAD_REQUEST);
        }


        String productId = productService.save(productDto).getId().toString();

        return new ResponseEntity<>(new ResponseSuccessData<>(ResponseMessageConstants.SAVE_SUCCESS, "id:" + productId), HttpStatus.OK);
    }*/


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestParam("image1") MultipartFile file,
                                      @RequestParam("name") String name,
                                      @RequestParam("category") String category,
                                      @RequestParam("description") String description,
                                      @RequestParam("perUnitPrice") String perUnitPrice,
                                      @RequestParam("borrowType") String borrowType,
                                      @RequestParam("division") String division,
                                      @RequestParam("district") String district,
                                      @RequestParam("subDistrict") String subDistrict,
                                      @RequestParam("status") String status

                                      ) throws Exception {

/*        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
            return new ResponseEntity<>(new ResponseErrorData<>(HttpStatus.BAD_REQUEST.value(), errors), HttpStatus.BAD_REQUEST);
        }*/

        ProductDto productDto = new ProductDto();
        productDto.setName(name);
        productDto.setCategory(category);
        productDto.setDescription(description);
        productDto.setImage1(file.getBytes());
        productDto.setPerUnitPrice(perUnitPrice);
        productDto.setBorrowType(borrowType);
        productDto.setDivision(division);
        productDto.setDistrict(district);
        productDto.setSubDistrict(subDistrict);
        productDto.setStatus(status);

        Product savedProduct = productService.save(productDto);

        return new ResponseEntity<>(new ResponseSuccessData<>(ResponseMessageConstants.SAVE_SUCCESS, savedProduct), HttpStatus.OK);
    }



    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<?> test(@RequestParam("division") String division ) throws Exception {
        //List<Product> product =  productService.getProductByUser(Long.parseLong(id));
        //List<Product> product =  productService.getProductById(Long.parseLong(id));
        List<Product> product =  productService.findProductByDivision(division);

        return new ResponseEntity<>(new ResponseSuccessData<>(ResponseMessageConstants.SAVE_SUCCESS, product), HttpStatus.OK);
    }
}
