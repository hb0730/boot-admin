package com.hb0730.boot.admin.manager.factory;

import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.hb0730.boot.admin.commons.enums.StatusEnum;
import com.hb0730.boot.admin.project.monitor.login.log.model.entity.LoginLogEntity;
import com.hb0730.boot.admin.project.monitor.login.log.service.ILoginLogService;
import com.hb0730.boot.admin.project.monitor.operation.model.entity.OperLogEntity;
import com.hb0730.boot.admin.project.monitor.operation.service.IOperLogService;
import com.hb0730.boot.admin.project.system.quartz.model.entity.JobLogEntity;
import com.hb0730.boot.admin.project.system.quartz.service.IJobLogService;
import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.TimerTask;

/**
 * 异步工厂（产生任务用）
 *
 * @author bing_huang
 * @since 3.0.0
 */
public class AsyncFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncFactory.class);

    /**
     * 记录定时任务日志
     *
     * @param entity 定时任务信息
     * @return {@link TimerTask}
     */
    public static TimerTask recordJobLog(JobLogEntity entity) {
        return new TimerTask() {
            @Override
            public void run() {
                SpringUtil.getBean(IJobLogService.class).save(entity);
            }
        };
    }

    /**
     * 记录操作日志
     *
     * @param entity 操作日志
     * @return {@link TimerTask}
     */
    public static TimerTask recordOperLog(OperLogEntity entity) {
        return new TimerTask() {
            @Override
            public void run() {
                SpringUtil.getBean(IOperLogService.class).save(entity);
            }
        };
    }

    /**
     * 记录登陆信息
     *
     * @param username 用户名
     * @param status   状态
     * @param message  消息
     * @param args     列表
     * @return 任务task
     */
    public static TimerTask recordLoginLog(final String username, final StatusEnum status, final String message,
                                           final Object... args) {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if (null == attributes) {
            return new TimerTask() {
                @Override
                public void run() {

                }
            };
        }
        if (attributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes) attributes).getRequest();
            final UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
            String ip = ServletUtil.getClientIP(request);
            return new TimerTask() {
                @Override
                public void run() {
                    // 打印信息到日志
                    String s = "[" + ip + "]" +
                            "[" + username + "]" +
                            "[" + status.getName() + "]" +
                            "[" + message + "]";
                    LOGGER.info(s, args);
                    // 获取客户端操作系统
                    String os = userAgent.getOperatingSystem().getName();
                    // 获取客户端浏览器
                    String browser = userAgent.getBrowser().getName();

                    LoginLogEntity entity = new LoginLogEntity();
                    entity.setUsername(username);
                    entity.setLoginIp(ip);
                    entity.setBrowser(browser);
                    entity.setOs(os);
                    entity.setMessage(message);
                    if (StatusEnum.SUCCESS.equals(status)) {
                        entity.setStatus(StatusEnum.SUCCESS.getValue());
                    } else {
                        entity.setStatus(StatusEnum.FAIL.getValue());
                    }
                    SpringUtil.getBean(ILoginLogService.class).save(entity);
                }
            };
        }else {
            return new TimerTask() {
                @Override
                public void run() {

                }
            };
        }

    }
}
