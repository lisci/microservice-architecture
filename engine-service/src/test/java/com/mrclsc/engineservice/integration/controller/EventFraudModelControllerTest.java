package com.mrclsc.engineservice.integration.controller;

import com.mrclsc.engineservice.controller.EventFraudController;
import com.mrclsc.engineservice.mocks.MockBuilder;
import com.mrclsc.engineservice.model.EventFraud;
import com.mrclsc.engineservice.service.EventFraudService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EventFraudController.class)
@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@ActiveProfiles("integration")
class EventFraudModelControllerTest {
    private final MockMvc mockMvc;
    @MockBean
    private EventFraudService eventFraudService;
    final String typeEvent = "IP";
    final String nameEvent = "192.168.0.1";
    final String urlTemplateGetFraud = "/engine/fraud/{typeEvent}/{nameEvent}";
    final String urlTemplateGetAllFraud = "/engine/frauds";
    final List<EventFraud> eventFraudList = MockBuilder.eventFraudListBuilder();

    @Test
    @WithMockUser(username = "spring", password = "secret", roles = "USER")
    void shouldGetFraud() throws Exception {
        {
            when(eventFraudService.checkEventFraud(any(String.class), any(String.class)))
                    .thenReturn(new ResponseEntity(MockBuilder.eventFraudBuilder(), OK));

            mockMvc.perform(get(urlTemplateGetFraud, typeEvent, nameEvent)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());

            verify(eventFraudService).checkEventFraud(any(String.class), any(String.class));
        }
    }

    @Test
    @WithMockUser(username = "spring", password = "secret", roles = "USER")
    void shouldGetNotFound400FraudAndRaiseException() throws Exception {
        {
            when(eventFraudService.checkEventFraud(any(String.class), any(String.class)))
                    .thenReturn(new ResponseEntity(HttpStatus.NOT_FOUND));

            mockMvc.perform(get(urlTemplateGetFraud, typeEvent, nameEvent)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());

            verify(eventFraudService).checkEventFraud(any(String.class), any(String.class));
        }
    }

    @Test
    @WithMockUser(username = "spring", password = "secret", roles = "USER")
    void shouldGetInternalError500FraudAndRaiseException() throws Exception {
        {
            when(eventFraudService.checkEventFraud(any(String.class), any(String.class)))
                    .thenReturn(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR));

            mockMvc.perform(get(urlTemplateGetFraud, typeEvent, nameEvent)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is5xxServerError());

            verify(eventFraudService).checkEventFraud(any(String.class), any(String.class));
        }
    }

    @Test
    @WithMockUser(username = "spring", password = "secret", roles = "USER")
    void shouldGetAllFraud() throws Exception {

        when(eventFraudService.getAllFraud())
                .thenReturn(new ResponseEntity(eventFraudList, OK));

        mockMvc.perform(get(urlTemplateGetAllFraud)
                .accept(APPLICATION_JSON).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(eventFraudList.get(0).name()))
                .andExpect(jsonPath("$[0].type").value(eventFraudList.get(0).type()))
                .andExpect(jsonPath("$[0].creationDate").value(eventFraudList.get(0).creationDate().toString()))
                .andExpect(jsonPath("$[1].name").value(eventFraudList.get(1).name()))
                .andExpect(jsonPath("$[1].type").value(eventFraudList.get(1).type()))
                .andExpect(jsonPath("$[1].creationDate").value(eventFraudList.get(1).creationDate().toString()));

        verify(eventFraudService).getAllFraud();
    }

    @Test
    @WithMockUser(username = "spring", password = "secret", roles = "USER")
    void shouldGetNotFound400AllFraudAndRaiseException() throws Exception {

        when(eventFraudService.getAllFraud())
                .thenReturn(new ResponseEntity(HttpStatus.NOT_FOUND));

        mockMvc.perform(MockMvcRequestBuilders.get(urlTemplateGetAllFraud)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(eventFraudService).getAllFraud();
    }

    @Test
    @WithMockUser(username = "spring", password = "secret", roles = "USER")
    void shouldGetInternalError500AllFraudAndRaiseException() throws Exception {

        when(eventFraudService.getAllFraud())
                .thenReturn(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR));

        mockMvc.perform(MockMvcRequestBuilders.get(urlTemplateGetAllFraud)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());

        verify(eventFraudService).getAllFraud();
    }
}