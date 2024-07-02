package com.elysiaptr.cemenghuiweb.controller;

import com.elysiaptr.cemenghuiweb.authentication.controller.CaptchaController;
import com.elysiaptr.cemenghuiweb.common.utils.CaptchaUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

@WebMvcTest(CaptchaController.class)
public class CaptchaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CaptchaUtils captchaUtils;


    @Test
    @WithMockUser
    public void testGetCaptcha() throws Exception {
        String uuid = "test-uuid";
        String expectedCaptcha = "data:image/png;base64,captcha-image-data";

        // Ensure that generateCaptcha returns a valid ResponseEntity
        when(captchaUtils.generateCaptcha(uuid)).thenReturn(new ResponseEntity<>(expectedCaptcha, HttpStatus.OK));

        mockMvc.perform(get("/open_api/captcha")
                        .param("uuid", uuid))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedCaptcha));
    }
}
