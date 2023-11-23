package com.mzhj19.eborrow.controller;

import com.mzhj19.eborrow.constant.ResponseMessageConstants;
import com.mzhj19.eborrow.constant.WebApiUrlConstants;
import com.mzhj19.eborrow.data.response.ResponseErrorData;
import com.mzhj19.eborrow.data.response.ResponseSuccessData;
import com.mzhj19.eborrow.dto.ProductDto;
import com.mzhj19.eborrow.model.Product;
import com.mzhj19.eborrow.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(WebApiUrlConstants.API_URI_PREFIX + "/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> register(@Valid @RequestBody ProductDto productDto, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
            return new ResponseEntity<>(new ResponseErrorData<>(HttpStatus.BAD_REQUEST.value(), errors), HttpStatus.BAD_REQUEST);
        }
        Product savedProduct = productService.save(productDto);

        return new ResponseEntity<>(new ResponseSuccessData<>(ResponseMessageConstants.SAVE_SUCCESS, savedProduct), HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllProduct", method = RequestMethod.GET)
    public ResponseEntity<?> getAllProduct() throws Exception {
        List<Product> products = productService.getAllProduct();
        if (products.size() == 0) {
            return new ResponseEntity<>(new ResponseErrorData<>(HttpStatus.NOT_FOUND.value(), ResponseMessageConstants.DATA_NOT_FOUND), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseSuccessData<>(ResponseMessageConstants.DATA_FOUND, products), HttpStatus.OK);
    }

    @RequestMapping(value = "/getProductById", method = RequestMethod.GET)
    public ResponseEntity<?> getProductById(@RequestParam("id") String id) throws Exception {
        Product product = productService.getProductById(Long.valueOf(id));
        if (product == null) {
            return new ResponseEntity<>(new ResponseErrorData<>(HttpStatus.NOT_FOUND.value(), ResponseMessageConstants.DATA_NOT_FOUND), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseSuccessData<>(ResponseMessageConstants.DATA_FOUND, product), HttpStatus.OK);
    }


    @RequestMapping(value = "/getProductByOwner", method = RequestMethod.GET)
    public ResponseEntity<?> getProductByOwner() throws Exception {
        String email = "mzhj19@gmail.com";
        List<Product> product = productService.findProductByOwner(email);

        return new ResponseEntity<>(new ResponseSuccessData<>(ResponseMessageConstants.SAVE_SUCCESS, product), HttpStatus.OK);
    }


    @RequestMapping(value = "/update" + WebApiUrlConstants.PATH_VAR_ID, method = RequestMethod.PUT)
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDto productDto, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
            return new ResponseEntity<>(new ResponseErrorData<>(HttpStatus.BAD_REQUEST.value(), errors), HttpStatus.BAD_REQUEST);
        }

        Product updatedProduct = productService.updateProduct(id, productDto);

        if (updatedProduct == null) {
            return new ResponseEntity<>(new ResponseErrorData<>(HttpStatus.NOT_FOUND.value(), ResponseMessageConstants.DATA_NOT_FOUND), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ResponseSuccessData<>(ResponseMessageConstants.UPDATE_SUCCESS, updatedProduct), HttpStatus.OK);
    }


    @RequestMapping(value = "/delete" + WebApiUrlConstants.PATH_VAR_ID, method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) throws Exception {

        Product existingProduct = (Product) productService.getProductById(id);
        if (existingProduct == null) {
            return new ResponseEntity<>(new ResponseErrorData<>(HttpStatus.NOT_FOUND.value(), ResponseMessageConstants.DATA_NOT_FOUND), HttpStatus.NOT_FOUND);
        }
        productService.deleteProduct(id);

        return new ResponseEntity<>(new ResponseSuccessData<>(ResponseMessageConstants.DELETE_SUCCESS), HttpStatus.OK);
    }

}
