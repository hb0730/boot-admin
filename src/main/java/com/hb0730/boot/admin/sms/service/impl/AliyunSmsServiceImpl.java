package com.hb0730.boot.admin.sms.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.hb0730.boot.admin.exception.sms.SmsException;
import com.hb0730.boot.admin.sms.constant.SmsTypeEnum;
import com.hb0730.boot.admin.sms.model.params.AliyunSmsParams;
import com.hb0730.boot.admin.sms.model.params.BaseParams;
import com.hb0730.boot.admin.sms.model.properties.SmsProperties;
import com.hb0730.boot.admin.sms.service.ISmsService;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * 阿里云 短信服务
 *
 * @author bing_huang
 * @date 2020/07/01 9:24
 * @since V1.0
 */
public class AliyunSmsServiceImpl implements ISmsService {
    private final IAcsClient iAcsClient;

    private final SmsProperties smsProperties;

    public AliyunSmsServiceImpl(SmsProperties smsProperties) {
        this.smsProperties = smsProperties;
        String accessKey = this.smsProperties.getAccessKey();
        String securityKey = this.smsProperties.getSecurityKey();
        String region = this.smsProperties.getRegion();
        Assert.hasText(accessKey, "access key can not be null");
        Assert.hasText(securityKey, "securityKey can not be null");
        Assert.hasText(region, "region can not be null");
        Assert.hasText(this.smsProperties.getDomain(), "domain can not be null");
        Assert.hasText(this.smsProperties.getVersion(), "version can not be null");
        DefaultProfile profile = DefaultProfile.getProfile(region, accessKey, securityKey);
        this.iAcsClient = new DefaultAcsClient(profile);
    }

    @Override
    public Object sendSms(@NonNull BaseParams params) {
        CommonRequest request = setCommonRequest();
        request.setSysAction("SendSms");
        if (params instanceof AliyunSmsParams) {
            AliyunSmsParams aliSmsRequest = (AliyunSmsParams) params;
            setSingleSmsParams(request, aliSmsRequest, aliSmsRequest.getTemplateCode());
            setOtherParams(request, aliSmsRequest);
            try {
                return iAcsClient.getCommonResponse(request);
            } catch (ClientException e) {
                throw new SmsException("发送短信异常", e);
            }
        }
        throw new SmsException();
    }

    @Override
    public Object sendTemplateSms(@NonNull String templateId, @NonNull BaseParams params) {
        CommonRequest request = setCommonRequest();
        request.setSysAction("SendSms");
        if (params instanceof AliyunSmsParams) {
            AliyunSmsParams aliSmsRequest = (AliyunSmsParams) params;
            setSingleSmsParams(request, aliSmsRequest, templateId);
            setOtherParams(request, aliSmsRequest);
            try {
                return iAcsClient.getCommonResponse(request);
            } catch (ClientException e) {
                throw new SmsException("发送模板短信异常", e);
            }
        }
        throw new SmsException();
    }

    @Override
    public Object sendBatchSms(@NonNull BaseParams params) {
        CommonRequest request = setCommonRequest();
        if (params instanceof AliyunSmsParams) {
            AliyunSmsParams aliSmsRequest = (AliyunSmsParams) params;
            String templateCode = aliSmsRequest.getTemplateCode();
            setMultiSmsParams(request, aliSmsRequest, templateCode);
            setOtherParams(request, aliSmsRequest);
            try {
                return iAcsClient.getCommonResponse(request);
            } catch (ClientException e) {
                throw new SmsException("批量发送异常", e);
            }
        }
        throw new SmsException();
    }

    @Override
    public Object sendBatchTemplateSms(@NonNull String templateId, @NonNull BaseParams params) {
        CommonRequest request = setCommonRequest();
        if (params instanceof AliyunSmsParams) {
            AliyunSmsParams aliSmsRequest = (AliyunSmsParams) params;
            setMultiSmsParams(request, aliSmsRequest, templateId);
            setOtherParams(request, aliSmsRequest);
            try {
                return iAcsClient.getCommonResponse(request);
            } catch (ClientException e) {
                throw new SmsException("批量发送模板异常", e);
            }
        }
        throw new SmsException();
    }

    @Override
    public boolean supportType(@NonNull SmsTypeEnum type) {
        return SmsTypeEnum.ALIYUN.equals(type);
    }

    private CommonRequest setCommonRequest() {
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(this.smsProperties.getDomain());
        request.setSysVersion(this.smsProperties.getVersion());
        return request;
    }

    /**
     * 批量发送填充参数
     *
     * @param request      请求
     * @param params       参数
     * @param templateCode 模板id
     */
    private void setMultiSmsParams(CommonRequest request, AliyunSmsParams params, @NonNull String templateCode) {
        String[] phoneNumbers = params.getPhoneNumbers();
        Assert.hasText(templateCode, "param templateCode can not be null");
        Assert.notEmpty(phoneNumbers, "param phoneNumbers can not be null");
        request.putQueryParameter("TemplateCode", templateCode);
        if (params.isSendBatchSms()) {
            request.setSysAction("SendBatchSms");
            request.putQueryParameter("PhoneNumbers", JSON.toJSONString(phoneNumbers));
        } else {
            request.setSysAction("SendSms");
            StringBuilder stringBuilder = new StringBuilder();
            for (String phoneNumber : phoneNumbers) {
                stringBuilder.append(phoneNumber).append(",");
            }
            request.putQueryParameter("PhoneNumbers", stringBuilder.substring(0, stringBuilder.length() - 1));
        }
    }

    /**
     * 设置必填参数
     *
     * @param request      请求
     * @param params       请求
     * @param templateCode 模板
     */
    private void setSingleSmsParams(CommonRequest request, AliyunSmsParams params, @NonNull String templateCode) {
        String[] phoneNumbers = params.getPhoneNumbers();
        Assert.hasText(templateCode, "param templateCode can not be null");
        request.putQueryParameter("TemplateCode", templateCode);
        Assert.notEmpty(phoneNumbers, "param phoneNumbers can not be null");
        request.putQueryParameter("PhoneNumbers", phoneNumbers[0]);
    }

    /**
     * 填充其他参数
     *
     * @param request 请求
     * @param params  参数
     * @see <a href="https://help.aliyun.com/document_detail/101414.html?spm=a2c4g.11186623.6.624.661052feqnvtUb">请求参数</a>
     */
    private void setOtherParams(CommonRequest request, AliyunSmsParams params) {
        Assert.hasText(params.getSignName(), "param signName can not be null");
        request.putQueryParameter("SignName", params.getSignName());

        if (StringUtils.hasText(smsProperties.getRegion())) {
            request.putQueryParameter("RegionId", smsProperties.getRegion());
        }

        if (StringUtils.hasText(params.getTemplateParam())) {
            request.putQueryParameter("TemplateParam", params.getTemplateParam());
        }

        if (StringUtils.hasText(params.getSmsUpExtendCode())) {
            request.putQueryParameter("SmsUpExtendCode", params.getSmsUpExtendCode());
        }

        if (StringUtils.hasText(params.getOutId())) {
            request.putQueryParameter("OutId", params.getOutId());
        }
    }
}
