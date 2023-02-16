package com.mrclsc.engineservice.unit.service;

import com.mrclsc.engineservice.client.FraudClient;
import com.mrclsc.engineservice.service.EventFraudService;
import feign.FeignException;
import feign.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("unit")
class EventFraudServiceTest {

    @Mock
    private FraudClient fraudClient;
    @InjectMocks
    private EventFraudService eventFraudService;
    final String typeEvent = "IP";
    final String nameEvent = "192.168.0.1";

//    @BeforeEach
//    public void init() {
//        eventFraudService = new EventFraudService(fraudClient);
//    }

    @Test
    void testCheckEventFraudSuccess() throws Exception {
        when(fraudClient.checkEventFraud(typeEvent, nameEvent))
                .thenReturn(new ResponseEntity<>(HttpStatus.OK));

        ResponseEntity<?> result = eventFraudService.checkEventFraud(nameEvent, typeEvent);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void testCheckEventFraudFailure() throws Exception {
        String message = "Bad Request";
        Request request = Mockito.mock(Request.class);

        when(fraudClient.checkEventFraud(typeEvent, nameEvent))
                .thenThrow(new FeignException.BadRequest(message, request, null, null));

        ResponseEntity<?> result = eventFraudService.checkEventFraud(nameEvent, typeEvent);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
   }

    @Test
    void givenGetAllFraud_thenReturnHTTPStatusOk() throws Exception {
        when(fraudClient.getAllFraud())
                .thenReturn(new ResponseEntity<>(HttpStatus.OK));

        ResponseEntity<?> result = eventFraudService.getAllFraud();

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void whenCallGetAllFraud_testGetAllFraudFailure() throws Exception {
        String message = "Bad Request";
        Request request = Mockito.mock(Request.class);

        when(fraudClient.getAllFraud())
                .thenThrow(new FeignException.NotFound(message, request, null, null));

        ResponseEntity<?> result = eventFraudService.getAllFraud();

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

}