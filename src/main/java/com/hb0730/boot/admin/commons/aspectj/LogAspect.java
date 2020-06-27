package com.hb0730.boot.admin.commons.aspectj;

import com.google.common.collect.Lists;
import com.hb0730.boot.admin.commons.annotation.Log;
import com.hb0730.boot.admin.commons.constant.enums.SystemStatusEnum;
import com.hb0730.boot.admin.commons.utils.MessageUtils;
import com.hb0730.boot.admin.commons.utils.ServletUtils;
import com.hb0730.boot.admin.commons.utils.ip.IpUtils;
import com.hb0730.boot.admin.commons.utils.json.GsonUtils;
import com.hb0730.boot.admin.commons.utils.spring.SpringUtils;
import com.hb0730.boot.admin.configuration.properties.BootAdminProperties;
import com.hb0730.boot.admin.exception.BaseException;
import com.hb0730.boot.admin.exception.DemoException;
import com.hb0730.boot.admin.manager.AsyncManager;
import com.hb0730.boot.admin.manager.factory.AsyncFactory;
import com.hb0730.boot.admin.project.monitor.operlog.model.entity.SystemOperLogEntity;
import com.hb0730.boot.admin.security.model.LoginUser;
import com.hb0730.boot.admin.security.service.ITokenService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 操作日志记录处理
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Aspect
@Component
@AllArgsConstructor
public class LogAspect {
    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);
    private final BootAdminProperties properties;

    /**
     * 配置织入点
     */
    @Pointcut("@annotation(com.hb0730.boot.admin.commons.annotation.Log)")
    public void logPointCut() {
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointCut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult) {
        handleLog(joinPoint, null, jsonResult);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e         异常
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, e, null);

    }

    /**
     * 日志处理
     */
    void handleLog(final JoinPoint joinPoint, final Exception e, Object jsonResult) {
        try {
            Log log = getAnnotationLog(joinPoint);
            boolean demoEnabled = properties.isDemoEnabled();
            if (log == null) {
                return;
            } else if (demoEnabled) {
                throw new DemoException(MessageUtils.message("demo"));
            }
            // 获取当前的用户
            LoginUser loginUser = SpringUtils.getBean(ITokenService.class).getLoginUser(ServletUtils.getRequest());
            // 日志存储

            SystemOperLogEntity entity = new SystemOperLogEntity();
            entity.setStatus(SystemStatusEnum.SUCCESS.getValue());
            // 操作类型
            entity.setBusinessType(log.businessType().getValue());
            // 请求的地址
            String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
            entity.setOperIp(ip);
            // 返回参数
            entity.setJsonResult(GsonUtils.json2String(jsonResult));
            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            entity.setMethod(className + "." + methodName + "()");
            // 异常
            if (e != null) {
                entity.setStatus(SystemStatusEnum.FAIL.getValue());
                entity.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
            }
            if (loginUser != null) {
                entity.setCreateUserId(loginUser.getId());
                entity.setUsername(loginUser.getUsername());
            }
            entity.setOperUrl(ServletUtils.getRequest().getRequestURI());
            // 设置请求方式
            entity.setRequestMethod(ServletUtils.getRequest().getMethod());
            getControllerMethodDescription(joinPoint, log, entity);
            // 保存数据库
            AsyncManager.me().execute(AsyncFactory.recordOper(entity));
        } catch (Exception e1) {
            e1.printStackTrace();
            // 记录本地异常日志
            log.error("==前置通知异常==");
            log.error("异常信息:{}", e1.getMessage());
            throw new BaseException(e1.getMessage());
        }
    }

    /**
     * <p>
     * 获取controller方法信息
     * </p>
     *
     * @param log    日志注解
     * @param entity 日志实体信息
     */
    void getControllerMethodDescription(JoinPoint joinPoint, Log log, SystemOperLogEntity entity) {
        entity.setBusinessType(log.businessType().getValue());
        entity.setModule(log.module());
        entity.setTitle(log.title());
        //保存请求参数
        if (log.isSaveRequestData()) {
            setRequestValue(joinPoint, entity, log.paramsName());
        }
    }

    /**
     * 保存请求参数
     *
     * @param entity     日志实体信息
     * @param paramsName 请求参数
     */
    void setRequestValue(JoinPoint joinPoint, SystemOperLogEntity entity, String[] paramsName) {
        String requestMethod = entity.getRequestMethod();
        if (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod)) {
            Object[] args = joinPoint.getArgs();
            String[] parameterNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
            String params = argsArrayToString(args, parameterNames, paramsName);
            entity.setOperParam(StringUtils.substring(params, 0, 2000));
        } else {
            Map<?, ?> paramsMap = (Map<?, ?>) ServletUtils.getRequest().getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            entity.setOperParam(StringUtils.substring(paramsMap.toString(), 0, 2000));
        }
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private Log getAnnotationLog(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(Log.class);
        }
        return null;
    }

    /**
     * <p>
     * 参数拼装
     * </p>
     *
     * @param args          请求参数信息
     * @param paramsName    请求参数名称
     * @param logParamsName 需要拦截的参数名称
     * @return 格式化的参数信息
     */
    private String argsArrayToString(Object[] args, String[] paramsName, String[] logParamsName) {
        String params = "";
        if ((paramsName != null && paramsName.length > 0) && (logParamsName != null && logParamsName.length > 0)) {
            List<Object> paramsObj = Lists.newArrayList();
            for (int i = 0; i < paramsName.length; i++) {
                for (String s : logParamsName) {
                    if (s.equals(paramsName[i])) {
                        paramsObj.add(args[i]);
                    }
                }
            }
            params = GsonUtils.gson2String(paramsObj);
        }
        return params;
    }
}
