package com.hb0730.boot.admin.security.controller;

import com.hb0730.boot.admin.security.model.Login;
import com.hb0730.jsons.SimpleJsonProxy;
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

@SpringBootTest
@ActiveProfiles("local")
@AutoConfigureMockMvc
@Slf4j
class LoginControllerTest {
    @Resource
    private MockMvc mvc;

    @Test
    void login() throws Exception {
        Login login = new Login();
        login.setUsername("Administrator");
        login.setPassword("123456");
        MvcResult result = mvc.perform(
            MockMvcRequestBuilders.post(
                    "/auth/login"
                ).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(SimpleJsonProxy.json.toJson(login))
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
        String content = result.getResponse().getContentAsString();
        log.info(content);
    }
}
