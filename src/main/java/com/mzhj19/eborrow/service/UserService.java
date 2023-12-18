package com.mzhj19.eborrow.service;

import com.mzhj19.eborrow.dto.UserRegisterDto;
import com.mzhj19.eborrow.model.User;

import java.util.List;
import java.util.Optional;


public interface UserService {
    User save(UserRegisterDto userRegisterDto);

    List<User> getAllUsers();

    Optional<User> getUserById(String id);

    Optional<User> findByEmail(String email);

}
