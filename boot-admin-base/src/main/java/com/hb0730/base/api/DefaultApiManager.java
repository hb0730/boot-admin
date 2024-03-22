package com.hb0730.base.api;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.hb0730.base.AppUtil;
import com.hb0730.base.api.service.Api;
import com.hb0730.base.api.service.ApiService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 对外接口管理器默认实现
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/1/23
 */
@Slf4j
public abstract class DefaultApiManager implements ApiManager {
    /**
     * 对外接口注册表
     */
    private final Map<String, Api> apis = new ConcurrentHashMap<>();


    @Override
    public Api getApi(String apiName) {
        if (apis.isEmpty()) {
            log.warn("apis is empty, init apis");
            apis.putAll(AppUtil.getBeansWithAnnotation(ApiService.class, Api.class));
        }
        Api api = apis.get(apiName);
        if (null == api) {
            api = new Api() {
                @Override
                public TaskStatus execute(ApiAuth auth, String params) {
                    return TaskStatus.WARNING().setMessage(String.format("%s 无法识别当前接口名称", apiName));
                }

                @Override
                public String getName() {
                    return "UNKNOWN";
                }

                @Override
                public String getGroup() {
                    return "UNKNOWN";
                }

                @Override
                public String getChannel() {
                    return "UNKNOWN";
                }
            };
        }
        return api;
    }

    public final String getAppSec(String appKey) {
        return getAppSec(appKey, null);
    }

    @Override
    public String getAppSec(String appKey, String apiName) {
        return "02ICL9TriDC8fBYphNzkdcM2yrXrf5QIoTXJIfVL";
    }

    @Override
    public boolean checkSign(String appKey, String apiName, String bizParam, Map<String, String> allParams, String sign) {
        String signed = sign(appKey, apiName, bizParam, allParams);
        return signed.equalsIgnoreCase(sign);
    }

    @Override
    public String sign(String appKey, String apiName, String bizParam, Map<String, ?> allParams) {
        return genSign(allParams, null, getAppSec(appKey, apiName));
    }

    private String genSign(Map<String, ?> allParams, List<String> ignoreParamNames, String appSec) {
        try {
            StringBuilder sb = new StringBuilder();
            List<String> paramNames = new ArrayList<String>(allParams.size());
            paramNames.addAll(allParams.keySet());
            if (CollectionUtil.isNotEmpty(ignoreParamNames)) {
                paramNames.removeAll(ignoreParamNames);
            }

            Collections.sort(paramNames);
            sb.append(appSec);
            for (String paramName : paramNames) {
                sb.append(paramName).append(allParams.get(paramName));
            }
            return DigestUtil.sha1Hex(sb.toString());
        } catch (Exception e) {
            log.error("genSign error", e);
            return "";
        }
    }
}
