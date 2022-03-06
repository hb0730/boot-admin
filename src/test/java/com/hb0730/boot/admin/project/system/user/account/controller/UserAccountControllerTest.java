package com.hb0730.boot.admin.project.system.user.account.controller;

import com.hb0730.boot.admin.commons.utils.JsonUtils;
import com.hb0730.boot.admin.project.ProjectTest;
import com.hb0730.boot.admin.project.system.user.model.query.UserAccountParams;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
@AutoConfigureMockMvc
@Slf4j
@WithAnonymousUser
public class UserAccountControllerTest extends ProjectTest {
    @Autowired
    private MockMvc mvc;

    @Override
    @Test
    @WithMockUser("user")
    public void pageTest() {
        try {
            MvcResult result = mvc.perform(
                    MockMvcRequestBuilders
                            .post("/system/user/account/list/page")
                            .accept(MediaType.APPLICATION_JSON_UTF8)
                            .contentType(MediaType.APPLICATION_JSON_UTF8)
                            .content(JsonUtils.objectToJson(new UserAccountParams()))
            ).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertNotNull(result);
            String resultContent = result.getResponse().getContentAsString();
            log.info(resultContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void listTest() {

    }

    @Override
    public void save() {

    }

    @Override
    public void updateById() {

    }

    @Override
    protected void removeById() {

    }
}
