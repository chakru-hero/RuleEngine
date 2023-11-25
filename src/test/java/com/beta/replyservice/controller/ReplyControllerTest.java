package com.beta.replyservice.controller;

import com.beta.replyservice.exception.InvalidInputException;
import com.beta.replyservice.service.ReplyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest
class ReplyControllerTest {
    @Mock
    RestTemplate restTemplate;
    @MockBean
    ReplyService replyService;
    @InjectMocks
    ReplyController replyController;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void WhenDataIsMissing_V1_ThenReturnMessage() throws Exception {
        ResultActions response = mockMvc.perform(get("/reply/"));
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.message").value("Data is empty"));
    }
    @Test
    void WhenMessageHits_V1_ThenReturnData() throws Exception {
        ResultActions response = mockMvc.perform(get("/reply/{message}","chakru"));
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.data").value("chakru"));
    }
    @Test
    void WhenRules11_V2_ThenReturnSameString() throws Exception {
        when(replyService.processString(anyString(),any())).thenReturn("chakru");
        ResultActions response = mockMvc.perform(get("/v2/reply/{message}","11-chakru"));
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.data").value("chakru"));
    }
    @Test
    void WhenRules12_V2_ThenReturn() throws Exception {
        when(replyService.processString(anyString(),any())).thenReturn("e5cf5b65d8881ebf1e53c8b1d8a66c56");
        ResultActions response = mockMvc.perform(get("/v2/reply/{message}","12-chakru"));
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.data").value("e5cf5b65d8881ebf1e53c8b1d8a66c56"));
    }
    @Test
    void WhenRules21_ThenReturn() throws Exception {
        when(replyService.processString(anyString(),any())).thenReturn("28a8166ac293b6a0b6589508f896c21d");
        ResultActions response = mockMvc.perform(get("/v2/reply/{message}","21-chakru"));
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.data").value("28a8166ac293b6a0b6589508f896c21d"));
    }
    @Test
    void WhenRules22_ThenReturn() throws Exception {
        when(replyService.processString(anyString(),any())).thenReturn("fe53d0c43c4bd239b4a42ed38b03c210");
        ResultActions response = mockMvc.perform(get("/v2/reply/{message}","22-chakru"));
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.data").value("fe53d0c43c4bd239b4a42ed38b03c210"));
    }

    @Test
    void WhenInvalidInput_ThenReturnBadRequest() throws Exception {
        when(replyService.processString(anyString(),any())).thenThrow(new InvalidInputException("Invalid Input"));
        ResultActions response = mockMvc.perform(get("/v2/reply/{message}","12chakru"));
        response.andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.message").value("Invalid Input"));
    }
}