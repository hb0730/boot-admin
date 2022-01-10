package com.hb0730.boot.admin.security.controller;

import com.hb0730.boot.admin.commons.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
@AutoConfigureMockMvc
@Slf4j
public class LoginControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void loginTest() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("username", "Administrator");
        map.put("password", "123456");
        MvcResult result = mvc.perform(
                MockMvcRequestBuilders
                        .post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(JsonUtils.objectToJson(map))
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
        Assert.assertNotNull(result);
        String resultContent = result.getResponse().getContentAsString();
        log.info(resultContent);
    }

    @Test
    public void loginTest2() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("username", "Administrator");
        map.put("password", "1234567");
        MvcResult result = mvc.perform(
                MockMvcRequestBuilders
                        .post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(JsonUtils.objectToJson(map))
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
        Assert.assertNotNull(result);
        String resultContent = result.getResponse().getContentAsString();
        log.info(resultContent);
    }
}
