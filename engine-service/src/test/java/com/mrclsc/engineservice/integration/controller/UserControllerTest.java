package com.mrclsc.engineservice.integration.controller;

import com.mrclsc.engineservice.controller.UserController;
import com.mrclsc.engineservice.entity.User;
import com.mrclsc.engineservice.mocks.MockBuilder;
import com.mrclsc.engineservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = UserController.class)
@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@ActiveProfiles("integration")
class UserControllerTest {
    @MockBean
    private UserService userService;
    private final MockMvc mockMvc;
    final String urlTemplate = "/user/{userId}";
    final User user = MockBuilder.userEntityBuilder();

    @Test
    @WithMockUser(username = "spring", password = "secret", roles = "USER")
    void shouldGetUserById() throws Exception {

        when(userService.getUserById(any(Long.class))).thenReturn(user);

        mockMvc.perform(get(urlTemplate, any(Long.class))
                .accept(APPLICATION_JSON).contentType(APPLICATION_JSON))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("firstName").value(user.getFirstName()))
                .andExpect(jsonPath("lastName").value(user.getLastName()))
                .andExpect(jsonPath("email").value(user.getEmail()));

        verify(userService, times(1)).getUserById(any(Long.class));
    }

    @Test
    @WithMockUser(username = "spring", password = "secret", roles = "USER")
    void shouldNotFoundUserById() throws Exception {

            when(userService.getUserById(any(Long.class))).thenReturn(null);

            mockMvc.perform(get(urlTemplate, any(Long.class))
                    .accept(APPLICATION_JSON).contentType(APPLICATION_JSON))
                    .andExpect(status().isNotFound())
                    .andExpect(status().is4xxClientError());

            verify(userService, times(1)).getUserById(any(Long.class));
    }

}