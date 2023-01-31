package com.mrclsc.fraudservice.controller;

import com.mrclsc.fraudservice.entity.EventFraud;
import com.mrclsc.fraudservice.service.EventFraudService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/fraud-service")
public class EventFraudController {

    private final EventFraudService eventFraudService;

    @GetMapping("/{typeEvent}/{nameEvent}")
    public ResponseEntity<?> getFraud(@PathVariable String typeEvent,
                                   @PathVariable String nameEvent) {
        Optional<EventFraud> eventFraud = eventFraudService.checkFraud(nameEvent, typeEvent);   
             
        if(eventFraud.isPresent())
            return new ResponseEntity(eventFraud, HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/frauds")
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> getAllFraud() {
        List<EventFraud> eventFraudList = eventFraudService.getAllFraud();

        if(eventFraudList.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity(eventFraudList, HttpStatus.OK);

    }

}
