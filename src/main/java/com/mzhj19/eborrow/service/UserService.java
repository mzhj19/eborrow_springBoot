package com.mzhj19.eborrow.service;

import com.mzhj19.eborrow.dto.UserRegisterReqDto;
import com.mzhj19.eborrow.model.User;

import java.util.List;
import java.util.Optional;


public interface UserService {
    User save(UserRegisterReqDto userRegisterReqDto);

    List<User> getAllUsers();

    Optional<User> getUserById(String id);

    User findByEmail(String email);

    User findByMobileNo(String mobileNo);

}
