package com.mrclsc.engineservice.service;

import com.mrclsc.engineservice.client.FraudClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EventFraudService {

    private final FraudClient fraudClient;


    public ResponseEntity<?> checkEventFraud(String nameEvent, String typeEvent) {
        try {
            return fraudClient.checkEventFraud(typeEvent, nameEvent);
        }catch (FeignException feignException) {
            return new ResponseEntity(feignException.responseBody(), HttpStatus.valueOf(feignException.status()));
        }
    }


    public ResponseEntity<?> getAllFraud() {

        try {
            return fraudClient.getAllFraud();
        }catch (FeignException feignException) {
            return new ResponseEntity(feignException.responseBody(), HttpStatus.valueOf(feignException.status()));
        }

    }

}