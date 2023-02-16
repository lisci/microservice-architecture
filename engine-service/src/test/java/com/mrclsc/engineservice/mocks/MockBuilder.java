package com.mrclsc.engineservice.mocks;

import com.mrclsc.engineservice.entity.ERole;
import com.mrclsc.engineservice.entity.Role;
import com.mrclsc.engineservice.entity.User;
import com.mrclsc.engineservice.model.EventFraud;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MockBuilder {
    public final static EventFraud eventFraudBuilder() {
        return new EventFraud("192.132.144.3", "IP", LocalDate.of(2017, 11, 12));
    }
    public final static List<EventFraud> eventFraudListBuilder() {
        return Stream.of(new EventFraud("192.132.144.3", "IP", LocalDate.of(2017, 11, 12)), new EventFraud("192.132.144.4", "IP", LocalDate.of(2017, 10, 12)), new EventFraud("192.132.144.4", "IP", LocalDate.of(2017, 10, 12))).collect(Collectors.toList());
    }

    public final static User userEntityBuilder() {
        Role role = Role.builder().id(1L).name(ERole.ROLE_USER).build();
        Set<Role> roles = Collections.singleton(role);

        return User.builder().id(1L).firstName("Antonio").lastName("Rossi").password("password").username("Tony99").email("antonio.rossi@email.com").roles(roles).build();
    }

    public final static List<User> userEntityListBuilder() {
        return Stream.of(User.builder().id(0L).firstName("Antonio").lastName("Rossi").email("antonio.rossi@email.com").build(), User.builder().id(1L).firstName("Paolo").lastName("Bianchi").email("paolo.bianchi@email.com").build(), User.builder().id(2L).firstName("Mario").lastName("Tozzi").email("mario.tozzi@email.com").build()).collect(Collectors.toList());
    }
}
