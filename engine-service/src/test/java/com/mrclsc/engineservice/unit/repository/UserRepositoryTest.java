package com.mrclsc.engineservice.unit.repository;

import com.mrclsc.engineservice.entity.User;
import com.mrclsc.engineservice.mocks.MockBuilder;
import com.mrclsc.engineservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("unit")
class UserRepositoryTest {

    final String username = "Tony99";
    final String email = "antonio.rossi@email.com";
    @Mock
    private UserRepository userRepository;

    @Test
    void givenFindByUsername_whenUsernameIsValid_thenReturnUser() {
        when(userRepository.findByUsername(any(String.class))).thenReturn(Optional.of(MockBuilder.userEntityBuilder()));

        Optional<User> foundByUsername = userRepository.findByUsername(username);

        assertEquals(MockBuilder.userEntityBuilder(), foundByUsername.get());
        verify(userRepository, times(1)).findByUsername(any(String.class));
    }

    @Test
    void givenFindByEmail_whenUsernameIsValid_thenReturnUser() {
        when(userRepository.findByEmail((any(String.class)))).thenReturn(Optional.of(MockBuilder.userEntityBuilder()));

        Optional<User> foundByEmail = userRepository.findByEmail(email);

        assertEquals(MockBuilder.userEntityBuilder(), foundByEmail.get());
        verify(userRepository, times(1)).findByEmail(any(String.class));
    }

}