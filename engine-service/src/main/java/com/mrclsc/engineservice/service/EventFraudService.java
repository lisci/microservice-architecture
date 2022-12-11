package com.mrclsc.engineservice.service;

import com.mrclsc.engineservice.client.FraudClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;


@Service
@RequiredArgsConstructor
public class EventFraudService {

    private final FraudClient fraudClient;

    @Cacheable
    public CompletableFuture<ResponseEntity<?>> checkEventFraud(String nameEvent, String typeEvent) {
        try {
            return CompletableFuture.supplyAsync(() -> fraudClient.checkEventFraud(typeEvent, nameEvent));
        }catch (FeignException feignException) {
            return CompletableFuture.supplyAsync(() -> new ResponseEntity(feignException.responseBody(), HttpStatus.valueOf(feignException.status())));
        }
    }

    @Cacheable
    public CompletableFuture<ResponseEntity<?>> getAllFraud() {

        try {
            return CompletableFuture.supplyAsync(() -> fraudClient.getAllFraud());
        }catch (FeignException feignException) {
            return CompletableFuture.supplyAsync(() -> new ResponseEntity(feignException.responseBody(), HttpStatus.valueOf(feignException.status())));
        }

    }

}