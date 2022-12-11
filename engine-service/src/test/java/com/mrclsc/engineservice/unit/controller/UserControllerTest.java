package com.mrclsc.engineservice.unit.controller;


import com.mrclsc.engineservice.controller.UserController;
import com.mrclsc.engineservice.entity.User;
import com.mrclsc.engineservice.mocks.MockBuilder;
import com.mrclsc.engineservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    /*
    @Mock creates a mock. @InjectMocks creates an instance of the class and injects the mocks that are created with the @Mock
    * */
    @Mock
    private UserService userService;
    @InjectMocks
    private UserController userController;

    @Test
    void shouldGetUserByIdTest() {
        when(userService.getUserById(any(Long.class))).thenReturn(MockBuilder.userEntityBuilder()); // Mocking service

        ResponseEntity<?> response = userController.getUserById(any(Long.class));
        User userEntity = (User) response.getBody();

        assertNotNull(response);
        assertEquals("Antonio", userEntity.getFirstName());
        assertEquals("Rossi", userEntity.getLastName());
        assertEquals("antonio.rossi@email.com", userEntity.getEmail());
    }
}