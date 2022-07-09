package com.hb0730.boot.admin.message.mail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("local")
class EmailSendMsgHandleTest {

    private JavaMailSender javaMailSender;

    @BeforeEach
    void beforeTest() {
        SpringMailProperties properties = new SpringMailProperties(true);
        properties.setHost("smtp.qq.com");
        properties.setProtocol("smtp");
        properties.setPort(465);
        properties.setUsername("");
        properties.setPassword("");
        javaMailSender = SpringMailSenderFactory.getMailSender(properties);
    }

    @Test
    void sendMsg() {
        EmailSendMsgHandle msgHandle = new EmailSendMsgHandle();
        msgHandle.setJavaMailSender(javaMailSender);
        msgHandle.setFromName("hb0730");
        msgHandle.sendMsg("huangbing0730@gmail.com", "测试", "<h1>cccccc</h1>");
    }
}
