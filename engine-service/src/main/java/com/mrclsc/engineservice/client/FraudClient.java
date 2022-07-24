package com.mrclsc.engineservice.client;

import com.mrclsc.engineservice.model.EventFraud;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@FeignClient(
        name = "fraud-microservice",
        url = "${clients.fraud.url}"
)
@Service
public interface FraudClient {

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/fraud/{typeEvent}/{nameEvent}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<EventFraud>> checkEventFraud(@RequestParam String typeEvent,
                                                            @RequestParam String nameEvent);


}
