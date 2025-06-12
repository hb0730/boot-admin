package com.hb0730.operator.log.core.model;

import cn.hutool.core.builder.Builder;
import cn.hutool.core.text.StrFormatter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hb0730.base.exception.BasicException;
import com.hb0730.base.meta.IUserInfo;
import com.hb0730.base.utils.ServletUtil;
import com.hb0730.base.utils.StrUtil;
import com.hb0730.operator.log.configuration.config.OperatorLogConfig;
import com.hb0730.operator.log.core.define.Wrapper;
import com.hb0730.operator.log.core.enums.ReturnType;
import com.hb0730.operator.log.core.factory.OperatorTypeHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * 操作日志模型构建器
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/10
 */
public class OperatorLogModelBuilder implements Builder<OperatorLogModel> {

    private static OperatorLogConfig operatorLogConfig;
    private static ObjectMapper objectMapper;

    private final OperatorLogModel model;

    public OperatorLogModelBuilder(OperatorLogModel model) {
        this.model = model;
    }


    public static OperatorLogModelBuilder create() {
        return new OperatorLogModelBuilder(new OperatorLogModel());
    }

    /**
     * 创建填充器
     *
     * @param model model
     * @return filler
     */
    public static OperatorLogModelBuilder create(OperatorLogModel model) {
        return new OperatorLogModelBuilder(model);
    }

    /**
     * 填充使用时间
     *
     * @param start start
     * @return this
     */
    public OperatorLogModelBuilder fillUsedTime(long start) {
        long end = System.currentTimeMillis();
        model.setDuration((int) (end - start));
        model.setStartTime(new Date(start));
        model.setEndTime(new Date(end));
        return this;
    }

    /**
     * 填充用户信息
     *
     * @param userId   userId
     * @param username username
     * @return this
     */
    public OperatorLogModelBuilder fillUserInfo(String userId, String username) {
        model.setUserId(userId);
        model.setUsername(username);
        return this;
    }

    /**
     * 填充用户信息
     *
     * @param user user
     * @return this
     */
    public OperatorLogModelBuilder fillUserInfo(IUserInfo user) {
        model.setUserId(user.getId());
        model.setUsername(user.getUsername());
        return this;
    }

    /**
     * 填充 traceId
     *
     * @param traceId traceId
     * @return this
     */
    public OperatorLogModelBuilder fillTraceId(String traceId) {
        model.setTraceId(traceId);
        return this;
    }

    /**
     * 填充请求留痕信息
     *
     * @return this
     */
    public OperatorLogModelBuilder fillRequest() {
        // traceId
//        String traceId = TraceHolder.getTraceId();
//        model.setTraceId(traceId);
        // 填充请求信息
        Optional
                .ofNullable(RequestContextHolder.getRequestAttributes())
                .map(s -> (ServletRequestAttributes) s)
                .map(ServletRequestAttributes::getRequest)
                .ifPresent(request -> {
                    String address = ServletUtil.getClientIP(request);
                    model.setAddress(address);
                    model.setLocation(ServletUtil.getLocal(address));
                    String userAgent = ServletUtil.getUserAgent(request);
                    model.setUserAgent(StrUtil.brief(userAgent, operatorLogConfig.getUserAgentLength()));
                });
        return this;
    }

    /**
     * 填充结果
     *
     * @param ret ret
     * @return this
     */
    public OperatorLogModelBuilder fillResult(Object ret) {
        return this.fillResult(ReturnType.JSON, ret, null);
    }

    /**
     * 填充结果
     *
     * @param exception exception
     * @return this
     */
    public OperatorLogModelBuilder fillException(Throwable exception) {
        return this.fillResult(null, null, exception);
    }

    /**
     * 填充结果
     *
     * @param ret       ret
     * @param exception exception
     * @return this
     */
    public OperatorLogModelBuilder fillResult(Object ret, Throwable exception) {
        return this.fillResult(ReturnType.JSON, ret, exception);
    }

    /**
     * 填充结果
     *
     * @param retType   retType
     * @param ret       ret
     * @param exception exception
     * @return this
     */
    public OperatorLogModelBuilder fillResult(ReturnType retType, Object ret, Throwable exception) {
        try {
            if (null == exception) {
                model.setResult(1);
                // 填充结果信息
                if (null != ret) {
                    // 脱敏
                    if (ReturnType.JSON.equals(retType)) {
                        // 脱敏
                        String json = objectMapper.writeValueAsString(ret);
                        Object valueObj = objectMapper.readValue(json, Object.class);
                        Wrapper<Object> jsonObj = new Wrapper<>(valueObj);
                        model.setReturnValue(objectMapper.writeValueAsString(jsonObj));
                    } else if (ReturnType.TO_STRING.equals(retType)) {
                        // 字符串
                        Wrapper<String> jsonObj = new Wrapper<>(Objects.toString(ret));
                        model.setReturnValue(objectMapper.writeValueAsString(jsonObj));
                    }
                }
            } else {
                model.setResult(0);
                // 错误信息
                String errorMessage = StrUtil.brief(exception.getMessage(), operatorLogConfig.getErrorMessageLength());
                model.setErrorMessage(errorMessage);
            }
        } catch (Exception e) {
            throw new BasicException(e);
        }
        return this;
    }

    /**
     * 填充拓展信息
     *
     * @param extra extra
     * @return this
     */
    public OperatorLogModelBuilder fillExtra(Map<String, Object> extra) {
        try {
            model.setExtra(objectMapper.writeValueAsString(extra));
        } catch (Exception e) {
            throw new BasicException("extra is unmodified", e);
        }
        return this;
    }

    /**
     * 填充日志信息
     *
     * @param extra extra
     * @param type  type
     * @return this
     */
    public OperatorLogModelBuilder fillLogInfo(Map<String, Object> extra, String type) {
        return this.fillLogInfo(extra, OperatorTypeHolder.get(type));
    }

    /**
     * 填充日志信息
     *
     * @param extra extra
     * @param type  type
     * @return this
     */
    public OperatorLogModelBuilder fillLogInfo(Map<String, Object> extra, OperatorType type) {
        model.setRiskLevel(type.getRiskLevel().name());
        model.setModule(type.getModule());
        model.setType(type.getType());
        // 占位符替换 {key} -> value
        String logInfo = StrFormatter.format(type.getTemplate(), extra, false);
        model.setLogInfo(logInfo);
        return this;
    }

    @Override
    public OperatorLogModel build() {
        return model;
    }

    public static void setObjectMapper(ObjectMapper objectMapper) {
        if (OperatorLogModelBuilder.objectMapper != null) {
            // unmodified
            throw new BasicException("objectMapper is unmodified");
        }
        OperatorLogModelBuilder.objectMapper = objectMapper;
    }

    public static void setOperatorLogConfig(OperatorLogConfig operatorLogConfig) {
        if (OperatorLogModelBuilder.operatorLogConfig != null) {
            // unmodified
            throw new BasicException("operatorLogConfig is unmodified");
        }
        OperatorLogModelBuilder.operatorLogConfig = operatorLogConfig;
    }
}
