package com.hb0730.api;

import com.hb0730.base.api.ApiAdapter;
import com.hb0730.base.api.TaskStatus;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.TreeMap;

/**
 * open endpoint
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/1/23
 */
@RestController
@RequestMapping
@Slf4j
public class RouterController {
    @Autowired
    ApiAdapter apiAdapter;

    /**
     * 对外接口
     *
     * @param appKey    应用键值
     * @param apiName   接口名称
     * @param timestamp 时间戳
     * @param sign      签名
     * @param params    业务参数
     * @return 处理结果
     */
    @RequestMapping("/router")
    public ResponseEntity<TaskStatus> router(
            @RequestParam("a") String appKey,
            @RequestParam("m") String apiName,
            @RequestParam("t") String timestamp,
            @RequestParam("s") String sign,
            @RequestParam("p") String params,
            HttpServletRequest request
    ) {
        //待签名信息
        Map<String, String> allParams = new TreeMap<String, String>();
        allParams.put("a", appKey);
        allParams.put("m", apiName);
        allParams.put("i", timestamp);
        allParams.put("p", params);
        TaskStatus execute = execute(appKey, apiName, params, allParams, sign);
        log.info("~~~ open endpoint result:{}", execute);
        return ResponseEntity.ok(execute);
    }

    /**
     * ping
     *
     * @return pong
     */
    @RequestMapping("/ping")
    public ResponseEntity<TaskStatus> ping() {
        return ResponseEntity.ok(TaskStatus.SUCCESS().setMessage("pong"));
    }


    /**
     * 对外接口
     *
     * @param appKey    应用键值
     * @param apiName   接口名称
     * @param bizParam  业务参数
     * @param allParams 请求参数
     * @param sign      签名
     * @return 处理结果
     */
    public TaskStatus execute(String appKey, String apiName, String bizParam, Map<String, String> allParams, String sign) {
        log.info("~~~ open endpoint appKey:{},apiName:{},bizParam:{},allParams:{},sign:{}", appKey, apiName, bizParam,
                allParams, sign);
        try {
            if (!checkSign(appKey, apiName, bizParam, allParams, sign)) {
                return TaskStatus.WARNING().setMessage("签名无效");
            }
            return execute(apiName, bizParam);
        } catch (Exception e) {
            log.error("~~~ open endpoint error", e);
            return TaskStatus.WARNING().setMessage("系统异常");
        }
    }

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
    protected boolean checkSign(String appKey, String apiName, String bizParam, Map<String, String> allParams, String sign) {
        return apiAdapter.checkSign(appKey, apiName, bizParam, allParams, sign);
    }

    /**
     * 业务处理
     *
     * @param apiName  接口名称
     * @param bizParam 业务参数
     * @return 处理结果
     */
    protected TaskStatus execute(String apiName, String bizParam) {
        return apiAdapter.execute(apiName, bizParam);
    }
}
