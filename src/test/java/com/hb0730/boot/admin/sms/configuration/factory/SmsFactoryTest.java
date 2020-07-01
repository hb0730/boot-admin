package com.hb0730.boot.admin.sms.configuration.factory;

import com.hb0730.boot.admin.commons.utils.json.GsonUtils;
import com.hb0730.boot.admin.sms.constant.SmsTypeEnum;
import com.hb0730.boot.admin.sms.model.params.AliyunSmsParams;
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
public class SmsFactoryTest {
    @Autowired
    private SmsFactory factory;

    @Test
    public void getService() {
        ISmsService service = factory.getService(SmsTypeEnum.ALIYUN);
        AliyunSmsParams params = new AliyunSmsParams();
        params.setPhoneNumbers(new String[]{"17673654989"});
        params.setTemplateCode("SMS_194610260");
        params.setTemplateParam("{\"code\":\"test\"}");
        params.setSignName("Blog微");
        Object o = service.sendSms(params);
        System.out.println(GsonUtils.json2String(o));

    }
}
