package com.mzhj19.eborrow.controller;

import com.mzhj19.eborrow.constant.WebApiUrlConstants;
import com.mzhj19.eborrow.dto.ResponseJWTAuth;
import com.mzhj19.eborrow.dto.LoginDto;
import com.mzhj19.eborrow.dto.UserRegisterDto;
import com.mzhj19.eborrow.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(WebApiUrlConstants.API_URI_PREFIX + "/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

/*    public AuthController(AuthService authService) {
        this.authService = authService;
    }*/

    // Build Login REST API
    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<ResponseJWTAuth> login(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);

        ResponseJWTAuth responseJWTAuth = new ResponseJWTAuth();
        responseJWTAuth.setToken(token);

        return ResponseEntity.ok(responseJWTAuth);
    }

    // Build Register REST API
    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@Valid @RequestBody UserRegisterDto userRegisterDto, BindingResult bindingResult){
        String response = authService.register(userRegisterDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
