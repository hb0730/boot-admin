package com.hb0730.boot.admin.sms.configuration;

import com.hb0730.boot.admin.sms.params.AliyunSmsParams;
import com.hb0730.boot.admin.sms.service.ISmsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class SmsAutoConfigureTest {
    @Autowired
    private ISmsService smsService;

    @Test
    public void smsService() {
        AliyunSmsParams params = new AliyunSmsParams();
        params.setSignName("Blog微");
        params.setTemplateId("SMS_194910435");
        params.setPhoneNumbers(new String[]{""});
        params.setTemplateParam("{\"code\":\"測試\"}");
        params.setOutId("hhhass");
        Object o = smsService.sendBatchSms(params);
    }
}
