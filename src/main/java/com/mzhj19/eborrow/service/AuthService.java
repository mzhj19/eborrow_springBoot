package com.mzhj19.eborrow.service;


import com.mzhj19.eborrow.dto.LoginDto;
import com.mzhj19.eborrow.dto.UserRegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(UserRegisterDto userRegisterDto);
}
