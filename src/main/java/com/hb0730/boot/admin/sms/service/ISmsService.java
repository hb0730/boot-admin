package com.hb0730.boot.admin.sms.service;

import com.hb0730.boot.admin.sms.constant.SmsTypeEnum;
import com.hb0730.boot.admin.sms.model.params.BaseParams;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

/**
 * 短信顶级接口
 *
 * @author bing_huang
 * @date 2020/07/01 8:54
 * @since V1.0
 */
public interface ISmsService {

    /**
     * 单个短信发送
     *
     * @param params 短信供应商所需参数
     * @return 短信供应商返回信息
     */
    Object sendSms(@NonNull BaseParams params);

    /**
     * 发送模板消息
     *
     * @param templateId 短信模板id
     * @param params     短信供应商所需参数
     * @return 短信供应商返回信息
     */
    Object sendTemplateSms(@NonNull String templateId, @NonNull BaseParams params);

    /**
     * 批量发送
     *
     * @param params 短信供应商所需参数
     * @return 短信供应商返回信息
     */
    Object sendBatchSms(@NonNull BaseParams params);

    /**
     * 批量模板短信
     *
     * @param templateId 模板id
     * @param params     短信供应商所需参数
     * @return 短信供应商返回信息
     */
    Object sendBatchTemplateSms(@NonNull String templateId, @NonNull BaseParams params);

    /**
     * 检查是否支持给定类型
     *
     * @param type sms类型
     * @return true支持类型
     */
    boolean supportType(@NonNull SmsTypeEnum type);
}
