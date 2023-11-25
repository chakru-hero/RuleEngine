package com.beta.replyservice.service.impl;

import com.beta.replyservice.exception.InvalidInputException;
import com.beta.replyservice.service.ReplyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ContextConfiguration(classes = {ReplyServiceImpl.class})
@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = {"Hash.Algo.Value=MD5"})
class ReplyServiceImplTest {
    @Value("${Hash.Algo.Value}")
    private String DEFAULTALGO;
    @Autowired
    private ReplyService replyService;

    @Test
    void WhenRules11_V2_ThenReturnSameString() throws Exception {
        String string = replyService.processString("11-chakru",null);
        assertEquals("chakru", string);
    }
    @Test
    void WhenRules12_V2_ThenReturn() throws Exception {
        String string = replyService.processString("12-chakru",null);
        assertEquals("e5cf5b65d8881ebf1e53c8b1d8a66c56", string);
    }
    @Test
    void WhenRules21_ThenReturn() throws Exception {
        String string = replyService.processString("21-chakru",null);
        assertEquals("28a8166ac293b6a0b6589508f896c21d", string);
    }
    @Test
    void WhenRules22_ThenReturn() throws Exception {
        String string = replyService.processString("22-chakru",null);
        assertEquals("fe53d0c43c4bd239b4a42ed38b03c210", string);
    }

    @Test
    void WhenInvalidInput_ThenThrowException() throws Exception {
        assertThrows(InvalidInputException.class,
                ()->{
                    replyService.processString("13-chakru",null);
                });
    }
}

