package com.mrclsc.engineservice.integration.controller;

import com.mrclsc.engineservice.config.security.WebSecurityConfig;
import com.mrclsc.engineservice.controller.UserController;
import com.mrclsc.engineservice.mocks.MockBuilder;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = UserController.class)
@Import({WebSecurityConfig.class, UserController.class})
@ContextConfiguration(classes = {MockitoExtension.class})
@RequiredArgsConstructor
class UserControllerTest {
    @MockBean
    private UserController userController;
    private final MockMvc mockMvc;

    @Test
    @WithMockUser(username = "spring", password = "secret", roles = "USER")
    void shouldGetUserById() throws Exception {

        when(userController.getUserById(any(Long.class))).thenReturn(new ResponseEntity(MockBuilder.userEntityBuilder(), HttpStatus.OK));

        mockMvc.perform(get("/user/{userId}", any(Long.class)).accept(APPLICATION_JSON).contentType(APPLICATION_JSON)).andExpect(content().contentType(APPLICATION_JSON)).andExpect(status().is2xxSuccessful()).andExpect(jsonPath("firstName").value("Antonio")).andExpect(jsonPath("lastName").value("Rossi")).andExpect(jsonPath("email").value("antonio.rossi@email.com")).andDo(print()).andReturn();
    }

    @Test
    @WithMockUser(username = "spring", password = "secret", roles = "USER")
    void shouldNotFoundUserById() throws Exception {
        {
            when(userController.getUserById(any(Long.class))).thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));

            mockMvc.perform(get("/user/{userId}", any(Long.class)).accept(APPLICATION_JSON).contentType(APPLICATION_JSON)).andExpect(status().isNotFound()).andExpect(status().is4xxClientError()).andDo(print()).andReturn();
        }
    }
}