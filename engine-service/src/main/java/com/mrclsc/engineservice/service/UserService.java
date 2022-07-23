package com.mrclsc.engineservice.service;

import com.mrclsc.engineservice.model.UserEntity;
import com.mrclsc.engineservice.model.UserRequest;
import com.mrclsc.engineservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity getUser(long userId) {
        return UserEntity.builder()
                .firstName("Antonio")
                .lastName("Rossi")
                .email("antonio.rossi@email.com")
                .build();
    }


}
