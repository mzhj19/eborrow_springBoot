package com.mzhj19.eborrow.service;

import com.mzhj19.eborrow.dto.UserRegisterReqDto;
import com.mzhj19.eborrow.model.User;

public interface UserService {
    User findByEmail(String email);

    User findByMobileNo(String mobileNo);

    User save(UserRegisterReqDto userRegisterReqDto);
}
