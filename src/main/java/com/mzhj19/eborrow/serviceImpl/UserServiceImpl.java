package com.mzhj19.eborrow.serviceImpl;

import com.mzhj19.eborrow.dto.UserRegisterReqDto;
import com.mzhj19.eborrow.model.User;
import com.mzhj19.eborrow.repository.UserRepository;
import com.mzhj19.eborrow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

/*    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;*/


    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByMobileNo(String mobileNo) {
        return userRepository.findByMobileNo(mobileNo);
    }

    @Override
    public User save(UserRegisterReqDto userRegisterReqDto) {
        /*        String encodedPassword = bCryptPasswordEncoder.encode(userRegisterReqDto.getPassword());*/
        return userRepository.save(User.builder()
                .email(userRegisterReqDto.getEmail())
                .mobileNo(userRegisterReqDto.getMobileNo())
                .password(userRegisterReqDto.getPassword())
                .build()
        );
    }
}
