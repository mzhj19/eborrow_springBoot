package com.mzhj19.eborrow.serviceImpl;

import com.mzhj19.eborrow.configuration.security.JWTProvider;
import com.mzhj19.eborrow.dto.LoginDto;
import com.mzhj19.eborrow.dto.RegisterDto;
import com.mzhj19.eborrow.exceptions.EborrowApiException;
import com.mzhj19.eborrow.exceptions.EborrowApiValidationException;
import com.mzhj19.eborrow.model.EborrowUser;
import com.mzhj19.eborrow.model.Role;
import com.mzhj19.eborrow.repository.RoleRepository;
import com.mzhj19.eborrow.repository.UserRepository;
import com.mzhj19.eborrow.service.AuthService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JWTProvider jwtProvider;
    private Validator validator;


    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           JWTProvider jwtProvider,
                           Validator validator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.validator = validator;
    }


    @Override
    public String login(LoginDto loginDto) {
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getEmail(),
                            loginDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtProvider.generateToken(authentication);

            return token;
        }   catch (Exception exception) {
            throw new EborrowApiException("WRONG CREDENTIALS!");
        }
    }


    @Override
    public String register(RegisterDto registerDto) {
        Set<ConstraintViolation<RegisterDto>> violations = validator.validate(registerDto);

        if (!violations.isEmpty()) {
            Map<String, String> errors = new HashMap<>();

            for (ConstraintViolation<RegisterDto> constraintViolation : violations) {
                String field = constraintViolation.getPropertyPath().toString();
                String message = constraintViolation.getMessage();
                errors.put(field, message);
            }
            throw new EborrowApiValidationException(errors);
        }
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new EborrowApiException("USER ALREADY EXISTS!");
        }

        EborrowUser user = new EborrowUser();
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));

        Role roles = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));

        userRepository.save(user);

        return "USER REGISTERED SUCCESSFULLY!";
    }
}
