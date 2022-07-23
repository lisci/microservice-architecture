package com.mrclsc.engineservice.service;

import com.mrclsc.engineservice.client.FraudClient;
import com.mrclsc.engineservice.model.EventFraud;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class EventFraudService {

    private final FraudClient fraudClient;

    public ResponseEntity<List<EventFraud>> checkEventFraud(String nameEvent, String typeEvent) {
        try {
            ResponseEntity<List<EventFraud>> responseEntity = fraudClient.checkEventFraud(typeEvent, nameEvent);
            return new ResponseEntity(responseEntity.getBody(), responseEntity.getStatusCode());
        }catch (FeignException feignException) {
            return new ResponseEntity(feignException.responseBody(), HttpStatus.valueOf(feignException.status()));
        }
    }

}
