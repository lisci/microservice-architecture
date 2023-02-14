package com.mrclsc.engineservice.integration.repository;

import com.mrclsc.engineservice.entity.User;
import com.mrclsc.engineservice.mocks.MockBuilder;
import com.mrclsc.engineservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Transactional
@ActiveProfiles("integration")
class UserRepositoryIntegrationTest {
@Autowired
    private UserRepository userRepository;
    final User user = MockBuilder.userEntityBuilder();

    @BeforeEach
    void init() {
        userRepository.deleteAll();
        userRepository.save(user);
    }

    @Test
    void findByUsernameTest() {
        Optional<User> foundUser = userRepository.findByUsername(user.getUsername());

        assertTrue(foundUser.isPresent());
        assertEquals(user.getEmail(), foundUser.get().getEmail());
        assertEquals(user.getFirstName(), foundUser.get().getFirstName());
        assertEquals(user.getLastName(), foundUser.get().getLastName());
        assertEquals(user.getUsername(), foundUser.get().getUsername());
        assertEquals(user.getPassword(), foundUser.get().getPassword());
        assertEquals(user.getRoles(), foundUser.get().getRoles());
    }

    @Test
    void findByEmailTest() {
        Optional<User> foundUser = userRepository.findByEmail(user.getEmail());

        assertTrue(foundUser.isPresent());
        assertEquals(user.getEmail(), foundUser.get().getEmail());
        assertEquals(user.getFirstName(), foundUser.get().getFirstName());
        assertEquals(user.getLastName(), foundUser.get().getLastName());
        assertEquals(user.getUsername(), foundUser.get().getUsername());
        assertEquals(user.getPassword(), foundUser.get().getPassword());
        assertEquals(user.getRoles(), foundUser.get().getRoles());
    }
}
