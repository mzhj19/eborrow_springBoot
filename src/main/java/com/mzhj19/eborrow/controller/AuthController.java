package com.mzhj19.eborrow.controller;

import com.mzhj19.eborrow.configuration.security.JWTProvider;
import com.mzhj19.eborrow.constant.WebApiUrlConstants;
import com.mzhj19.eborrow.dto.AuthResponseDTO;
import com.mzhj19.eborrow.dto.LoginDto;
import com.mzhj19.eborrow.dto.RegisterDto;
import com.mzhj19.eborrow.model.EborrowUser;
import com.mzhj19.eborrow.model.Role;
import com.mzhj19.eborrow.repository.RoleRepository;
import com.mzhj19.eborrow.repository.UserRepository;
import com.mzhj19.eborrow.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping(WebApiUrlConstants.API_URI_PREFIX + "/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JWTProvider jwtProvider;

    private AuthService authService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          UserRepository userRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder,
                          JWTProvider jwtProvider,
                          AuthService authService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {

        String response = authService.register(registerDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
