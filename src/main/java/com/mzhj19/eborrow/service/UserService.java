package com.mzhj19.eborrow.service;

import com.mzhj19.eborrow.dto.UserRegisterReqDto;
import com.mzhj19.eborrow.model.EborrowUser;

import java.util.List;
import java.util.Optional;


public interface UserService {
    EborrowUser save(UserRegisterReqDto userRegisterReqDto);

    List<EborrowUser> getAllUsers();

    Optional<EborrowUser> getUserById(String id);

    Optional<EborrowUser> findByEmail(String email);

}
