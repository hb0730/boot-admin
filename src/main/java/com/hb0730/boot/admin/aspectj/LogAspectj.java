package com.hb0730.boot.admin.aspectj;

import com.google.common.collect.Lists;
import com.hb0730.boot.admin.annotation.ClassDescribe;
import com.hb0730.boot.admin.annotation.Log;
import com.hb0730.commons.json.utils.Jsons;
import com.hb0730.commons.spring.IpUtils;
import com.hb0730.commons.spring.ServletUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * 操作日志
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Aspect
@Component
public class LogAspectj {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspectj.class);

    /**
     * 配置织入点
     */
    @Pointcut("@annotation(com.hb0730.boot.admin.annotation.Log)")
    public void logPointCut() {
    }

//    /**
//     * 返回通知
//     *
//     * @param joinPoint 切点
//     * @param result    返回值
//     */
//    @AfterReturning(returning = "result", pointcut = "logPointCut()")
//    public void doAfterReturning(JoinPoint joinPoint, Object result) throws Exception {
//        handleLog(joinPoint, result, null);
//    }

    /**
     * 返回通知
     *
     * @param joinPoint 切点
     * @param result    返回值
     */
    @AfterReturning(returning = "result", pointcut = "logPointCut()")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        handleLog(joinPoint, result, null);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e         异常
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, null, e);
    }

    /**
     * 日志处理
     */
    void handleLog(final JoinPoint joinPoint, Object jsonResult, final Exception e) {
        tryCatch((val) -> {
            Log log = getAnnotationLog(joinPoint);
            if (check(joinPoint, log)) {
                return;
            }
            // 类描述
            String controllerDescription = "";
            ClassDescribe classDescribe = joinPoint.getTarget().getClass().getAnnotation(ClassDescribe.class);
            if (null != classDescribe) {
                controllerDescription = classDescribe.value();
            }
            //方法描述
            String controllerMethodDescription = getDescribe(log);

            // class
            String className = joinPoint.getTarget().getClass().getName();
            // method
            String methodName = joinPoint.getSignature().getName();
            // args
            HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
            assert log != null;
            String requestParamsValue = getRequestValue(joinPoint, request, log.paramsName());

            // 请求地址
            String requestUrl = ServletUtils.getRequest().getRequestURI();
            //请求方式
            String requestMethod = ServletUtils.getRequest().getMethod();
            // 返回参数

            //请求ip
            String ip = IpUtils.getIp(ServletUtils.getRequest());
            //操作类型

        });
    }

    /**
     * 获取请求参数
     *
     * @param joinPoint  切点
     * @param request    请求
     * @param paramsName 参数列表
     * @return 格式化后的请求参数
     */
    String getRequestValue(JoinPoint joinPoint, HttpServletRequest request, String[] paramsName) {
        String requestMethod = request.getMethod();
        if (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod)) {
            Object[] args = joinPoint.getArgs();
            String[] parameterNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
            return argsArrayToString(args, parameterNames, paramsName);
        } else {
            Map<?, ?> paramsMap = (Map<?, ?>) ServletUtils.getRequest().getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            return StringUtils.substring(paramsMap.toString(), 0, 2000);
        }
    }

    /**
     * 是否存在注解，如果存在就获取
     *
     * @param joinPoint 切点
     * @return {@link Log}注解
     */
    @Nullable
    private Log getAnnotationLog(JoinPoint joinPoint) {
        try {
            Log annotation = null;
            if (joinPoint.getSignature() instanceof MethodSignature) {
                Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
                if (method != null) {
                    annotation = method.getAnnotation(Log.class);
                }
            }
            return annotation;
        } catch (Exception e) {
            LOGGER.warn("获取 {}.{} 的 @Log 注解失败",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), e);
            e.printStackTrace();
            return null;
        }
    }

    private void tryCatch(Consumer<String> consumer) {
        try {
            consumer.accept("");
        } catch (Exception e) {
            LOGGER.warn("记录操作日志异常", e);
        }
    }

    /**
     * 检查是否不需要记录
     *
     * @param joinPoint 切点
     * @param logAnno   {@link Log}
     * @return true:不需要记录
     */
    private boolean check(JoinPoint joinPoint, Log logAnno) {
        if (null == logAnno || Boolean.FALSE.equals(logAnno.enabled())) {
            return false;
        }
        Log annotation = joinPoint.getTarget().getClass().getAnnotation(Log.class);
        // 不需要记录日志 true
        return null != annotation && !annotation.enabled();
    }

    /**
     * 获取描述
     *
     * @param log {@link Log}
     * @return 描述
     */
    private String getDescribe(Log log) {
        if (null == log) {
            return "";
        }
        return log.value();
    }

    /**
     * 参数拼装
     *
     * @param args             请求参数信息
     * @param parameterNames   请求参数名称
     * @param filterParamsName 需要拦截的参数名称
     * @return 格式化的参数信息
     */
    private String argsArrayToString(Object[] args, String[] parameterNames, String[] filterParamsName) {
        String params = "";
        try {

            if (((parameterNames != null && parameterNames.length > 0)
                    && (filterParamsName != null && filterParamsName.length > 0))) {
                List<Object> paramsObj = Lists.newArrayList();
                for (int i = 0; i < parameterNames.length; i++) {
                    for (String s : filterParamsName) {
                        if (s.equals(parameterNames[i])) {
                            paramsObj.add(args[i]);
                        }
                    }
                }
                params = Jsons.Utils.instance().objectToJson(paramsObj);
            }
        } catch (Exception e) {
            LOGGER.error("参数拼接异常");
            e.printStackTrace();
        }
        return params;
    }
}
