package com.mrclsc.engineservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mrclsc.engineservice.model.EventFraud;
import com.mrclsc.engineservice.service.EventFraudService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@PreAuthorize("isAuthenticated()")  
@RestController
@RequestMapping("/engine")
@AllArgsConstructor
@Slf4j
public class EventFraudController {

    private final EventFraudService eventFraudService;

    @GetMapping("/fraud/{typeEvent}/{nameEvent}")
    public ResponseEntity<?> getFraud(@PathVariable String typeEvent,
                                                     @PathVariable String nameEvent) throws JsonProcessingException {
        return eventFraudService.checkEventFraud(nameEvent, typeEvent);
    }

    @GetMapping("/fraud")
    public ResponseEntity<?> getAllFraud() throws JsonProcessingException {
        return eventFraudService.getAllFraud();
    }

}
