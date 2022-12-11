package com.mrclsc.engineservice.service;

import com.mrclsc.engineservice.entity.User;
import com.mrclsc.engineservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return User.builder().id(user.getId()).email(user.getEmail()).authorities(user.getAuthorities()).password(user.getPassword()).firstName(user.getFirstName()).lastName(user.getLastName()).username(user.getUsername()).build();
    }

    public User getUserById(long userId) {
        return userRepository.getReferenceById(userId);
    }

}
