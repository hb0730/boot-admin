package com.hb0730.boot.admin.security.test;

import com.hb0730.boot.admin.base.BootAdminConst;
import com.hb0730.boot.admin.security.Login;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class TestControllerTest extends Login {

    @Test
    void test1(@Autowired MockMvc mockMvc) throws Exception {
        String token = login(mockMvc);
        MvcResult result = mockMvc.perform(
            MockMvcRequestBuilders
                .get("/test/a1")
                .header(BootAdminConst.X_ACCESS_TOKEN, token)
        ).andReturn();
        String body = result.getResponse().getContentAsString();
        log.info(body);
    }

    @Test
    void test2(@Autowired MockMvc mockMvc) throws Exception {
        String token = login(mockMvc);
        MvcResult result = mockMvc.perform(
            MockMvcRequestBuilders
                .get("/test/a2")
                .header(BootAdminConst.X_ACCESS_TOKEN, token)
        ).andReturn();
        String body = result.getResponse().getContentAsString();
        log.info(body);
    }
}
