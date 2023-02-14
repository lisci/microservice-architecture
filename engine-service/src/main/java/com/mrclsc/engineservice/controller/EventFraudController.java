package com.mrclsc.engineservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mrclsc.engineservice.service.EventFraudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/engine")
@AllArgsConstructor
@Slf4j
public class EventFraudController {

    private final EventFraudService eventFraudService;

    @Async
    @GetMapping("/fraud/{typeEvent}/{nameEvent}")
    @Operation(summary = "It provides a specific event fraud")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The fraud was not found")
    })
    @Parameters( value = {
            @Parameter(name = "typeEvent", description = "Event type can be for instance 'IP'"),
            @Parameter(name = "nameEvent", description = "Event name can be for instance an ip")
    })
    public ResponseEntity<?> getFraud(@PathVariable String typeEvent,
                                              @PathVariable String nameEvent) throws JsonProcessingException {
        return eventFraudService.checkEventFraud(nameEvent, typeEvent);
    }

    @Async
    @GetMapping("/frauds")
    @Operation(summary = "It provides a event fraud list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The fraud list was not found")
    })
    public ResponseEntity<?> getAllFraud() throws JsonProcessingException {
        return eventFraudService.getAllFraud();
    }

}
