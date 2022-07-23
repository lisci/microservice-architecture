package com.mrclsc.engineservice.controller;

import com.mrclsc.engineservice.model.UserEntity;
import com.mrclsc.engineservice.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public UserEntity getUser(@PathVariable long userId) {
        return userService.getUser(userId);
    }

}
