package com.hb0730.base.api;

import com.hb0730.base.api.service.Api;

import java.util.Map;

/**
 * 对外接口管理器接口
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/1/23
 */
public interface ApiManager {
    /**
     * 根据接口名称取得接口实现
     *
     * @param apiName 接口名称
     * @return 接口实现
     */
    Api getApi(String apiName);

    /**
     * 根据应用键值取得应用密钥
     *
     * @param appKey  应用键值
     * @param apiName 接口名称
     * @return 应用密钥
     */
    String getAppSec(String appKey, String apiName);

    /**
     * 验证签名
     *
     * @param appKey    应用键值
     * @param apiName   接口名称
     * @param bizParam  业务参数
     * @param allParams 请求参数
     * @param sign      签名
     * @return 签名是否有效
     */
    boolean checkSign(String appKey, String apiName, String bizParam, Map<String, String> allParams, String sign);

    /**
     * 取得签名
     *
     * @param appKey    应用键值
     * @param apiName   接口名称
     * @param bizParam  业务参数
     * @param allParams 待签名参数
     * @return 签名
     */
    String sign(String appKey, String apiName, String bizParam, Map<String, ?> allParams);
}
