package com.hb0730.boot.admin.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

@SpringBootTest
@ActiveProfiles("local")
@AutoConfigureMockMvc
@Slf4j
class TestControllerTest {
    @Resource
    private MockMvc mvc;

    @Test
    void test1() throws Exception {
        MvcResult result = mvc.perform(
            MockMvcRequestBuilders.get(
                    "/test/test1"
                ).contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
        String content = result.getResponse().getContentAsString();
        log.info(content);
    }
}
