package com.mzhj19.eborrow.serviceImpl;

import com.mzhj19.eborrow.configuration.security.JwtTokenProvider;
import com.mzhj19.eborrow.dto.LoginDto;
import com.mzhj19.eborrow.dto.UserRegisterDto;
import com.mzhj19.eborrow.exception.EborrowAPIException;
import com.mzhj19.eborrow.model.Role;
import com.mzhj19.eborrow.model.User;
import com.mzhj19.eborrow.repository.RoleRepository;
import com.mzhj19.eborrow.repository.UserRepository;
import com.mzhj19.eborrow.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


/*    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }*/

    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    @Override
    public String register(UserRegisterDto userRegisterDto) {

        // add check for username exists in database
/*        if(userRepository.existsByUsername(userRegisterReqDto.getUsername())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Username is already exists!.");
        }*/

        // add check for email exists in database
        if(userRepository.existsByEmail(userRegisterDto.getEmail())){
            throw new EborrowAPIException(HttpStatus.BAD_REQUEST.value(), "EMAIL ALREADY EXISTS!");
        }

        User user = new User();
        user.setEmail(userRegisterDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Optional<Role> userRole = roleRepository.findByName("ROLE_USER");

        if(userRole.isPresent())    {
            roles.add(userRole.get());
            user.setRoles(roles);
        }

        userRepository.save(user);

        return "USER REGISTERED SUCCESSFULLY!";
    }
}
