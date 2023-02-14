package com.mrclsc.engineservice.unit.controller;


import com.mrclsc.engineservice.controller.UserController;
import com.mrclsc.engineservice.mocks.MockBuilder;
import com.mrclsc.engineservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@ActiveProfiles("unit")
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
        when(userService.getUserById(any(Long.class)))
                .thenReturn(MockBuilder.userEntityBuilder()); // Mocking service

        ResponseEntity<?> response = userController.getUserById(any(Long.class));

        assertNotNull(response);
        assertEquals(MockBuilder.userEntityBuilder(), response.getBody());
        verify(userService, times(1)).getUserById(any(Long.class));
    }

    @Test
    void shouldNotGetUserByIdTest() {
        when(userService.getUserById(any(Long.class)))
                .thenReturn(null); // Mocking service

        ResponseEntity<?> response = userController.getUserById(any(Long.class));

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(userService, times(1)).getUserById(any(Long.class));
    }
}