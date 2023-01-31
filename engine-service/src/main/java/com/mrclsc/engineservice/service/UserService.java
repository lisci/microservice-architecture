package com.mrclsc.engineservice.service;

import com.mrclsc.engineservice.entity.User;
import com.mrclsc.engineservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getUserById(long userId) {
        return userRepository.getReferenceById(userId);
    }

}
