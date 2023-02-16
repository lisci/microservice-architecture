package com.mrclsc.engineservice.repository;

import com.mrclsc.engineservice.entity.ERole;
import com.mrclsc.engineservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(ERole name);
}
