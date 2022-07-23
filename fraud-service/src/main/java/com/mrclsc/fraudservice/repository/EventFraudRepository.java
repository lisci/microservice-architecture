package com.mrclsc.fraudservice.repository;

import com.mrclsc.fraudservice.entity.EventFraud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventFraudRepository extends JpaRepository<EventFraud, Long> {

    Optional<EventFraud> findByNameAndType(String name, String type);


    List<EventFraud> findAll();
}

