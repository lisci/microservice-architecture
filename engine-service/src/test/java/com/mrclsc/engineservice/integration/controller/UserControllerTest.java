package com.mrclsc.engineservice.integration.controller;

import com.mrclsc.engineservice.controller.UserController;
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
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = UserController.class)
@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class UserControllerTest {
    @MockBean
    private UserService userService;
    private final MockMvc mockMvc;

    @Test
    @WithMockUser(username = "spring", password = "secret", roles = "USER")
    void shouldGetUserById() throws Exception {

        when(userService.getUserById(any(Long.class))).thenReturn(MockBuilder.userEntityBuilder());

        mockMvc.perform(get("/user/{userId}", any(Long.class))
                .accept(APPLICATION_JSON).contentType(APPLICATION_JSON))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("firstName").value("Antonio"))
                .andExpect(jsonPath("lastName").value("Rossi"))
                .andExpect(jsonPath("email").value("antonio.rossi@email.com"))
                .andDo(print())
                .andReturn();

        verify(userService, times(1)).getUserById(0L);
    }

    @Test
    @WithMockUser(username = "spring", password = "secret", roles = "USER")
    void shouldNotFoundUserById() throws Exception {
        {
            when(userService.getUserById(any(Long.class))).thenReturn(null);

            mockMvc.perform(get("/user/{userId}", any(Long.class))
                    .accept(APPLICATION_JSON).contentType(APPLICATION_JSON))
                    .andExpect(status().isNotFound())
                    .andExpect(status().is4xxClientError())
                    .andDo(print())
                    .andReturn();

            verify(userService, times(1)).getUserById(0L);
        }
    }
}