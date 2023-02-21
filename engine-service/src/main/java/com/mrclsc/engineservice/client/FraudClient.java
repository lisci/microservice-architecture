package com.mrclsc.engineservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "fraud-microservice", url = "${clients.fraud.url}")
@Service
public interface FraudClient {

        @RequestMapping(method = RequestMethod.GET, value = "/fraud/{typeEvent}/{nameEvent}", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<?> checkEventFraud(@PathVariable(value = "typeEvent") String typeEvent, @PathVariable(value = "nameEvent") String nameEvent);

        @RequestMapping(method = RequestMethod.GET, value = "/frauds", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<?> getAllFraud();
}
