package com.hb0730.base.api;

import com.hb0730.base.api.service.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 对外接口代理
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/1/23
 */
@Component
public class ApiAdapter implements ApiManager {
    @Autowired(required = false)
    @Lazy
    ApiManager apiManager;

    /**
     * 业务处理
     *
     * @param apiName  接口名称
     * @param bizParam 业务参数
     * @return 处理结果
     */
    @Deprecated
    public TaskStatus execute(String apiName, String bizParam) {
        return execute(null, apiName, bizParam);
    }

    /**
     * 业务处理
     *
     * @param apiName  接口名称
     * @param bizParam 业务参数
     * @return 处理结果
     */
    public TaskStatus execute(ApiAuth auth, String apiName, String bizParam) {
        if (apiManager != null) {
            return apiManager.getApi(apiName).execute(auth, bizParam);
        }
        return TaskStatus.WARNING().setMessage("没有默认接口管理器！");
    }


    @Override
    public Api getApi(String apiName) {
        return apiManager.getApi(apiName);
    }

    @Override
    public String getAppSec(String appKey, String apiName) {
        return apiManager.getAppSec(appKey, apiName);
    }

    @Override
    public boolean checkSign(String appKey, String apiName, String bizParam, Map<String, String> allParams, String sign) {
        return apiManager.checkSign(appKey, apiName, bizParam, allParams, sign);
    }

    @Override
    public String sign(String appKey, String apiName, String bizParam, Map<String, ?> allParams) {
        return apiManager.sign(appKey, apiName, bizParam, allParams);
    }
}
