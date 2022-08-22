package com.mrclsc.engineservice.service;

import com.mrclsc.engineservice.client.FraudClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EventFraudService {

    private final FraudClient fraudClient;

    @Cacheable
    public ResponseEntity<?> checkEventFraud(String nameEvent, String typeEvent) {
        
        try {
            ResponseEntity<?> responseEntity = fraudClient.checkEventFraud(typeEvent, nameEvent);
            return new ResponseEntity(responseEntity.getBody(), responseEntity.getStatusCode());
        }catch (FeignException feignException) {
            return new ResponseEntity(feignException.responseBody(), HttpStatus.valueOf(feignException.status()));
        }
    }

    @Cacheable
    public ResponseEntity<?> getAllFraud() {
        
        try {
            ResponseEntity<?> responseEntity = fraudClient.getAllFraud();
            return new ResponseEntity(responseEntity.getBody(), responseEntity.getStatusCode());
        }catch (FeignException feignException) {
            return new ResponseEntity(feignException.responseBody(), HttpStatus.valueOf(feignException.status()));
        }
    }

}
