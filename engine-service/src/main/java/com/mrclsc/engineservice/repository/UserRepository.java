package com.mrclsc.engineservice.repository;

import com.mrclsc.engineservice.entity.ERole;
import com.mrclsc.engineservice.entity.Role;
import com.mrclsc.engineservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<Role> findByName(ERole name);
    Optional<User> findByUsername(String username);

}
