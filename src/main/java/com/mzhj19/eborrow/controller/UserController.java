package com.mzhj19.eborrow.controller;


import com.mzhj19.eborrow.constant.ResponseMessageConstants;
import com.mzhj19.eborrow.constant.WebApiUrlConstants;
import com.mzhj19.eborrow.data.response.ResponseErrorData;
import com.mzhj19.eborrow.data.response.ResponseSuccessData;
import com.mzhj19.eborrow.dto.UserRegisterReqDto;
import com.mzhj19.eborrow.model.User;
import com.mzhj19.eborrow.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


//@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(WebApiUrlConstants.API_URI_PREFIX + "/user")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterReqDto userRegisterReqDto, BindingResult bindingResult) throws Exception {

        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
            return new ResponseEntity<>(new ResponseErrorData<>(HttpStatus.BAD_REQUEST.value(), errors), HttpStatus.BAD_REQUEST);
        }

        if (!userRegisterReqDto.getPassword().equals(userRegisterReqDto.getConfirmedPassword())) {
            return new ResponseEntity<>(new ResponseErrorData<>(HttpStatus.BAD_REQUEST.value(), ResponseMessageConstants.CONFIRMED_PASSWORD_NOT_MATCHED), HttpStatus.BAD_REQUEST);
        }

        Optional<User> existEmail = Optional.ofNullable(userService.findByEmail(userRegisterReqDto.getEmail()));
        Optional<User> existMobileNo = Optional.ofNullable(userService.findByMobileNo(userRegisterReqDto.getMobileNo()));

        if (existEmail.isPresent() || existMobileNo.isPresent()) {
            if (existEmail.isPresent() && existMobileNo.isPresent()) {
                ArrayList<String> errorMessages = new ArrayList<>();
                errorMessages.add(ResponseMessageConstants.EMAIL_ALREADY_EXISTS);
                errorMessages.add(ResponseMessageConstants.MOBILE_NO_ALREADY_EXISTS);
                return new ResponseEntity<>(new ResponseErrorData<>(HttpStatus.BAD_REQUEST.value(), errorMessages), HttpStatus.BAD_REQUEST);
            }
            else if (existEmail.isPresent())
                return new ResponseEntity<>(new ResponseErrorData<>(HttpStatus.BAD_REQUEST.value(), ResponseMessageConstants.EMAIL_ALREADY_EXISTS), HttpStatus.BAD_REQUEST);
            else
                return new ResponseEntity<>(new ResponseErrorData<>(HttpStatus.BAD_REQUEST.value(), ResponseMessageConstants.MOBILE_NO_ALREADY_EXISTS), HttpStatus.BAD_REQUEST);
        }

        User user = userService.save(userRegisterReqDto);

        return new ResponseEntity<>(new ResponseSuccessData<>(ResponseMessageConstants.SAVE_SUCCESS, user), HttpStatus.OK);
    }
}
