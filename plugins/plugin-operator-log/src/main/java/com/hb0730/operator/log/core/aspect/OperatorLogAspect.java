package com.hb0730.operator.log.core.aspect;

import cn.hutool.core.thread.ExecutorBuilder;
import com.hb0730.base.meta.ICurrentUserService;
import com.hb0730.base.meta.IUserInfo;
import com.hb0730.base.utils.StrUtil;
import com.hb0730.operator.log.core.annotation.IgnoreParameter;
import com.hb0730.operator.log.core.annotation.OperatorLog;
import com.hb0730.operator.log.core.factory.OperatorTypeHolder;
import com.hb0730.operator.log.core.model.OperatorLogModel;
import com.hb0730.operator.log.core.model.OperatorLogModelBuilder;
import com.hb0730.operator.log.core.model.OperatorType;
import com.hb0730.operator.log.core.service.OperatorLogFrameworkService;
import com.hb0730.operator.log.core.util.OperatorLogs;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;

/**
 * 操作日志切面
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/9
 */
@Aspect
@Slf4j
public class OperatorLogAspect {

    private static final ExecutorService LOG_SAVER = ExecutorBuilder.create()
            .setCorePoolSize(1)
            .setMaxPoolSize(1)
            .setAllowCoreThreadTimeOut(true)
            .build();


    private final OperatorLogFrameworkService operatorLogFrameworkService;
    private final ICurrentUserService currentUserService;


    public OperatorLogAspect(OperatorLogFrameworkService operatorLogFrameworkService,
                             ICurrentUserService currentUserService) {
        this.operatorLogFrameworkService = operatorLogFrameworkService;
        this.currentUserService = currentUserService;
    }

    @Around("@annotation(o)")
    public Object around(ProceedingJoinPoint joinPoint, OperatorLog o) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            // 初始化参数
            this.initExtra(joinPoint, o);
            // 执行
            Object result = joinPoint.proceed();
            // 记录日志
            this.saveLog(start, o, result, null);
            return result;
        } catch (Throwable exception) {
            // 记录日志
            this.saveLog(start, o, null, exception);
            throw exception;
        } finally {
            // 清空上下文
            OperatorLogs.clear();
        }
    }

    /**
     * 初始化参数
     *
     * @param joinPoint joinPoint
     */
    private void initExtra(ProceedingJoinPoint joinPoint, OperatorLog o) {
        // 清空上下文
        OperatorLogs.clear();
        if (!o.parameter()) {
            return;
        }
        // 获取方法注解
        Annotation[][] methodAnnotations = Optional.ofNullable(joinPoint.getSignature())
                .filter(s -> (s instanceof MethodSignature))
                .map(s -> ((MethodSignature) s).getMethod())
                .map(Method::getParameterAnnotations)
                .orElse(null);
        if (methodAnnotations == null || methodAnnotations.length == 0) {
            return;
        }
        // 获取参数
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < methodAnnotations.length; i++) {
            Annotation[] annotations = methodAnnotations[i];
            if (null == annotations || annotations.length == 0) {
                continue;
            }
            // 检查是否有 IgnoreParameter 注解
            boolean ignore = Arrays.stream(annotations)
                    .anyMatch(s -> s instanceof IgnoreParameter);
            if (ignore) {
                continue;
            }
            // 获取需要记录的参数
            for (Annotation annotation : annotations) {
                if (annotation instanceof RequestBody) {
                    OperatorLogs.add(args[i]);
                    break;
                } else if (annotation instanceof RequestParam) {
                    Object arg = args[i];
                    if (arg instanceof MultipartFile) {
                        break;
                    }
                    OperatorLogs.add(
                            StrUtil.getStr(
                                    ((RequestParam) annotation).value(),
                                    ((RequestParam) annotation).name()
                            ),
                            args[i]
                    );
                    break;
                } else if (annotation instanceof RequestHeader) {
                    OperatorLogs.add(
                            StrUtil.getStr(((RequestHeader) annotation).value(),
                                    ((RequestHeader) annotation).name()),
                            args[i]);
                    break;
                } else if (annotation instanceof PathVariable) {
                    String name = ((PathVariable) annotation).name();
                    if (StrUtil.isBlank(name)) {
                        name = ((PathVariable) annotation).value();
                    }
                    if (StrUtil.isBlank(name)) {
                        //获取参数名
                        name = ((MethodSignature) joinPoint.getSignature()).getParameterNames()[i];
                    }
                    OperatorLogs.add(
                            name,
                            args[i]);
                    break;
                }
            }
        }
    }

    /**
     * 保存日志
     *
     * @param start     start
     * @param exception exception
     */
    private void saveLog(long start, OperatorLog o, Object ret, Throwable exception) {
        try {
            // 获取日志类型
            OperatorType type = OperatorTypeHolder.get(o.value());
            if (type == null) {
                return;
            }
            // 获取当前用户
            IUserInfo user = currentUserService.getCurrentUser();
            if (user == null) {
                return;
            }
            // 检查是否保存
            if (!OperatorLogs.isSave()) {
                return;
            }
            // 填充请求
            Map<String, Object> extra = OperatorLogs.get();
            OperatorLogModel model = OperatorLogModelBuilder.create()
                    // 填充使用时间
                    .fillUsedTime(start)
                    // 填充用户信息
                    .fillUserInfo(user)
                    // 填充请求信息
                    .fillRequest()
                    // 填充结果信息
                    .fillResult(o.ret(), ret, exception)
                    // 填充拓展信息
                    .fillExtra(extra)
                    // 填充日志
                    .fillLogInfo(extra, type)
                    .build();
            // 插入日志
            this.asyncSaveLog(model);
        } catch (Exception e) {
            log.error("操作日志保存失败", e);
        }
    }


    /**
     * 异步保存日志
     *
     * @param model model
     */
    private void asyncSaveLog(OperatorLogModel model) {
        LOG_SAVER.submit(() -> operatorLogFrameworkService.insert(model));
    }
}
