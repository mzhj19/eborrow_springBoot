package com.mzhj19.eborrow.service;

import com.mzhj19.eborrow.dto.LoginDto;
import com.mzhj19.eborrow.dto.RegisterDto;
import com.mzhj19.eborrow.model.EborrowUser;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
