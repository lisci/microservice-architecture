package com.mrclsc.fraudservice.service;

import com.mrclsc.fraudservice.entity.EventFraud;
import com.mrclsc.fraudservice.repository.EventFraudRepository;
import lombok.AllArgsConstructor;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EventFraudService {

    private final EventFraudRepository eventFraudRepository;

    @Cacheable
    public Optional<EventFraud> checkFraud(String nameEvent, String type) {
        return eventFraudRepository.findByNameAndType(nameEvent, type);
    }

    @Cacheable
    public List<EventFraud> getAllFraud() {
        return eventFraudRepository.findAll();
    }

}
