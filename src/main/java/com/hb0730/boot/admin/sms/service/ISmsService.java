package com.hb0730.boot.admin.sms.service;

import com.hb0730.boot.admin.sms.params.BaseSmsParams;
import org.springframework.lang.NonNull;

/**
 * 短信顶级接口
 *
 * @author bing_huang
 * @date 2020/07/03 10:48
 * @since V1.0
 */
public interface ISmsService {
    /**
     * 单个短信发送
     *
     * @param params 短信供应商所需参数
     * @return 短信供应商返回信息
     */
    Object sendSms(@NonNull BaseSmsParams params);

    /**
     * 发送模板消息
     *
     * @param templateId 短信模板id
     * @param params     短信供应商所需参数
     * @return 短信供应商返回信息
     */
    Object sendTemplateSms(@NonNull String templateId, @NonNull BaseSmsParams params);

    /**
     * 批量发送
     *
     * @param params 短信供应商所需参数
     * @return 短信供应商返回信息
     */
    Object sendBatchSms(@NonNull BaseSmsParams params);

    /**
     * 批量模板短信
     *
     * @param templateId 模板id
     * @param params     短信供应商所需参数
     * @return 短信供应商返回信息
     */
    Object sendBatchTemplateSms(@NonNull String templateId, @NonNull BaseSmsParams params);

}
