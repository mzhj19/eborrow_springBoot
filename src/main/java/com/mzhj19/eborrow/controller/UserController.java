package com.mzhj19.eborrow.controller;


import com.mzhj19.eborrow.constant.ResponseMessageConstants;
import com.mzhj19.eborrow.constant.WebApiUrlConstants;
import com.mzhj19.eborrow.dto.ResponseErrorData;
import com.mzhj19.eborrow.dto.ResponseSuccessData;
import com.mzhj19.eborrow.model.User;
import com.mzhj19.eborrow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


//@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(WebApiUrlConstants.API_URI_PREFIX + "/user")
public class UserController {

    @Autowired
    private UserService userService;


    /*@RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterReqDto userRegisterReqDto, BindingResult bindingResult) throws Exception {

        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
            return new ResponseEntity<>(new ResponseErrorData<>(HttpStatus.BAD_REQUEST.value(), errors), HttpStatus.BAD_REQUEST);
        }

*//*        if (!userRegisterReqDto.getPassword().equals(userRegisterReqDto.getConfirmedPassword())) {
            return new ResponseEntity<>(new ResponseErrorData<>(HttpStatus.BAD_REQUEST.value(), ResponseMessageConstants.CONFIRMED_PASSWORD_NOT_MATCHED), HttpStatus.BAD_REQUEST);
        }*//*

        Optional<User> existEmail = Optional.ofNullable(userService.findByEmail(userRegisterReqDto.getEmail()));

        if (existEmail.isPresent()) {
            return new ResponseEntity<>(new ResponseErrorData<>(HttpStatus.BAD_REQUEST.value(), ResponseMessageConstants.EMAIL_ALREADY_EXISTS), HttpStatus.BAD_REQUEST);
        }

        User user = userService.save(userRegisterReqDto);

        return new ResponseEntity<>(new ResponseSuccessData<>(ResponseMessageConstants.SAVE_SUCCESS, user), HttpStatus.OK);
    }*/

    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    public ResponseEntity<?> getAllUsers() throws Exception {
        List<User> user = userService.getAllUsers();
        if (user.size() == 0) {
            return new ResponseEntity<>(new ResponseErrorData<>(HttpStatus.NOT_FOUND.value(), ResponseMessageConstants.DATA_NOT_FOUND), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseSuccessData<>(ResponseMessageConstants.DATA_FOUND, user), HttpStatus.OK);
    }

    @RequestMapping(value = "/getUserById", method = RequestMethod.GET)
    public ResponseEntity<?> test(@RequestParam("id") String id) throws Exception {
        Optional<User> user = userService.getUserById(id);
        if (user.isEmpty()) {
            return new ResponseEntity<>(new ResponseErrorData<>(HttpStatus.NOT_FOUND.value(), ResponseMessageConstants.DATA_NOT_FOUND), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseSuccessData<>(ResponseMessageConstants.DATA_FOUND, user), HttpStatus.OK);
    }


}
