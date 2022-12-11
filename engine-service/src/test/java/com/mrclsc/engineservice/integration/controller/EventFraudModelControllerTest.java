package com.mrclsc.engineservice.integration.controller;

import com.mrclsc.engineservice.config.security.WebSecurityConfig;
import com.mrclsc.engineservice.controller.EventFraudController;
import com.mrclsc.engineservice.mocks.MockBuilder;
import com.mrclsc.engineservice.service.EventFraudService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.concurrent.CompletableFuture;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = EventFraudController.class)
@Import({WebSecurityConfig.class, EventFraudController.class})
@ContextConfiguration(classes = {MockitoExtension.class})
@RequiredArgsConstructor
class EventFraudModelControllerTest {
    @MockBean
    private EventFraudService eventFraudService;
    private final MockMvc mockMvc;
    @InjectMocks
    private EventFraudController eventFraudController;

    @Test
    @WithMockUser(username = "spring", password = "secret", roles = "USER")
    void shouldGetFraud() throws Exception {
        {

            when(eventFraudService.checkEventFraud(any(String.class), any(String.class))).thenReturn(CompletableFuture.completedFuture(new ResponseEntity(MockBuilder.eventFraudBuilder(), OK)));

            MvcResult mvcResult = mockMvc.perform(get("/engine/fraud/{typeEvent}/{nameEvent}", "IP", "192.132.144.3").accept(APPLICATION_JSON).contentType(APPLICATION_JSON)).andExpect(request().asyncStarted()).andExpect(request().asyncResult(instanceOf(ResponseEntity.class))).andDo(print()).andExpect(status().is2xxSuccessful()).andReturn();

            mockMvc.perform(asyncDispatch(mvcResult)).andExpect(status().is2xxSuccessful()).andExpect(content().contentType(APPLICATION_JSON)).andExpect(jsonPath("name").value("192.132.144.3")).andExpect(jsonPath("type").value("IP")).andExpect(jsonPath("creationDate").value("2017-11-12"));
        }
    }

    @Test
    @WithMockUser(username = "spring", password = "secret", roles = "USER")
    void shouldGetNotFound400FraudAndRaiseException() throws Exception {
        {

            when(eventFraudService.checkEventFraud(any(String.class), any(String.class))).thenReturn(CompletableFuture.completedFuture(new ResponseEntity(HttpStatus.NOT_FOUND)));

            MvcResult mvcResult = mockMvc.perform(get("/engine/fraud/{typeEvent}/{nameEvent}", "IP", "192.132.144.3").accept(APPLICATION_JSON).contentType(APPLICATION_JSON)).andExpect(request().asyncStarted()).andExpect(request().asyncResult(instanceOf(ResponseEntity.class))).andDo(print()).andExpect(status().is2xxSuccessful()).andReturn();

            mockMvc.perform(asyncDispatch(mvcResult)).andExpect(status().isNotFound()).andExpect(status().is4xxClientError());
        }
    }

    @Test
    @WithMockUser(username = "spring", password = "secret", roles = "USER")
    void shouldGetInternalError500FraudAndRaiseException() throws Exception {
        {

            when(eventFraudService.checkEventFraud(any(String.class), any(String.class))).thenReturn(CompletableFuture.completedFuture(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)));

            MvcResult mvcResult = mockMvc.perform(get("/engine/fraud/{typeEvent}/{nameEvent}", "IP", "192.132.144.3").accept(APPLICATION_JSON).contentType(APPLICATION_JSON)).andExpect(request().asyncStarted()).andExpect(request().asyncResult(instanceOf(ResponseEntity.class))).andDo(print()).andExpect(status().is2xxSuccessful()).andReturn();

            mockMvc.perform(asyncDispatch(mvcResult)).andExpect(status().isInternalServerError()).andExpect(status().is5xxServerError());
        }
    }

    @Test
    @WithMockUser(username = "spring", password = "secret", roles = "USER")
    void shouldGetAllFraud() throws Exception {

        when(eventFraudService.getAllFraud()).thenReturn(CompletableFuture.completedFuture(new ResponseEntity(MockBuilder.eventFraudListBuilder(), OK)));

        MvcResult mvcResult = mockMvc.perform(get("/engine/fraud").accept(APPLICATION_JSON).contentType(APPLICATION_JSON)).andExpect(request().asyncStarted()).andExpect(request().asyncResult(instanceOf(ResponseEntity.class))).andDo(print()).andExpect(status().is2xxSuccessful()).andReturn();

        mockMvc.perform(asyncDispatch(mvcResult)).andExpect(status().is2xxSuccessful()).andExpect(content().contentType(APPLICATION_JSON)).andExpect(jsonPath("$[0].name").value("192.132.144.3")).andExpect(jsonPath("$[0].type").value("IP")).andExpect(jsonPath("$[0].creationDate").value("2017-11-12")).andExpect(jsonPath("$[1].name").value("192.132.144.4")).andExpect(jsonPath("$[1].type").value("IP")).andExpect(jsonPath("$[1].creationDate").value("2017-10-12"));
    }

    @Test
    @WithMockUser(username = "spring", password = "secret", roles = "USER")
    void shouldGetNotFound400AllFraudAndRaiseException() throws Exception {

        when(eventFraudService.getAllFraud()).thenReturn(CompletableFuture.completedFuture(new ResponseEntity(HttpStatus.NOT_FOUND)));

        MvcResult mvcResult = mockMvc.perform(get("/engine/fraud").accept(APPLICATION_JSON).contentType(APPLICATION_JSON)).andExpect(request().asyncStarted()).andExpect(request().asyncResult(instanceOf(ResponseEntity.class))).andDo(print()).andExpect(status().is2xxSuccessful()).andReturn();

        mockMvc.perform(asyncDispatch(mvcResult)).andExpect(status().isNotFound()).andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(username = "spring", password = "secret", roles = "USER")
    void shouldGetInternalError500AllFraudAndRaiseException() throws Exception {

        when(eventFraudService.getAllFraud()).thenReturn(CompletableFuture.completedFuture(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)));

        MvcResult mvcResult = mockMvc.perform(get("/engine/fraud").accept(APPLICATION_JSON).contentType(APPLICATION_JSON)).andExpect(request().asyncStarted()).andExpect(request().asyncResult(instanceOf(ResponseEntity.class))).andDo(print()).andExpect(status().is2xxSuccessful()).andReturn();

        mockMvc.perform(asyncDispatch(mvcResult)).andExpect(status().isInternalServerError()).andExpect(status().is5xxServerError());
    }
}