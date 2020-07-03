package com.hb0730.boot.admin.sms.params;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * aliyun 请求参数
 *
 * @author bing_huang
 * @date 2020/07/03 10:46
 * @since V1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class AliyunSmsParams extends BaseSmsParams {
    /**
     * 手机号
     */
    private String[] phoneNumbers;

    /**
     * 签名名称
     */
    private String signName;

    /**
     * 模板参数
     */
    private String templateParam;

    /**
     * 上行短信扩展码
     */
    private String smsUpExtendCode;

    /**
     * 外部流水扩展字段
     */
    private String outId;

    /**
     * 是否批量发送
     */
    private boolean isSendBatchSms = false;


}
