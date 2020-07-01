package com.hb0730.boot.admin.mail.service.impl;

import com.hb0730.boot.admin.mail.properties.EmailProperties;
import com.hb0730.boot.admin.mail.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class MailServiceImplTest {

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Test
    public void sendTextMail() {
    }

    @Test
    public void testConnection() {
    }
}
