package com.mzhj19.eborrow.serviceImpl;

import com.mzhj19.eborrow.dto.UserRegisterDto;
import com.mzhj19.eborrow.model.User;
import com.mzhj19.eborrow.repository.UserRepository;
import com.mzhj19.eborrow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User save(UserRegisterDto userRegisterDto) {
        String encodedPassword = passwordEncoder.encode(userRegisterDto.getPassword());
        return userRepository.save(User.builder()
                .email(userRegisterDto.getEmail())
                .password(encodedPassword)
                .build()
        );
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepository.findById(Long.parseLong(id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
