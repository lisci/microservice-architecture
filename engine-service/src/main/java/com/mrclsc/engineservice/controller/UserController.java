package com.mrclsc.engineservice.controller;

import com.mrclsc.engineservice.entity.User;
import com.mrclsc.engineservice.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    @ResponseBody
    public ResponseEntity<?> getUserById(@PathVariable long userId) {
        User user = userService.getUserById(userId);

        if (Optional.ofNullable(user).isEmpty()) return new ResponseEntity(HttpStatus.NOT_FOUND);
        else return new ResponseEntity(user, HttpStatus.OK);
    }

}
