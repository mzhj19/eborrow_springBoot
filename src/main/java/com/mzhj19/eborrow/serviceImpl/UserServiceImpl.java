package com.mzhj19.eborrow.serviceImpl;

import com.mzhj19.eborrow.dto.UserRegisterReqDto;
import com.mzhj19.eborrow.model.User;
import com.mzhj19.eborrow.repository.UserRepository;
import com.mzhj19.eborrow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public User save(UserRegisterReqDto userRegisterReqDto) {
        /*        String encodedPassword = bCryptPasswordEncoder.encode(userRegisterReqDto.getPassword());*/
        return userRepository.save(User.builder()
                .email(userRegisterReqDto.getEmail())
                .password(userRegisterReqDto.getPassword())
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
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
