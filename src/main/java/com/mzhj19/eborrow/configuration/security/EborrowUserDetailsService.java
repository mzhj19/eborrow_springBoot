package com.mzhj19.eborrow.configuration.security;


import com.mzhj19.eborrow.model.User;
import com.mzhj19.eborrow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EborrowUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

/*    public EborrowUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }*/

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
          User user = userRepository.findByEmail(email)
                 .orElseThrow(() ->
                         new UsernameNotFoundException("USER NOT FOUND WITH EMAIL: "+ email));

        Set<GrantedAuthority> authorities = user
                .getRoles()
                .stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                authorities);
    }
}
