package com.hb0730.boot.admin.manager.factory;

import com.hb0730.boot.admin.commons.utils.ServletUtils;
import com.hb0730.boot.admin.commons.utils.ip.IpUtils;
import com.hb0730.boot.admin.commons.utils.spring.SpringUtils;
import com.hb0730.boot.admin.project.monitor.job.model.entity.SystemJobLogEntity;
import com.hb0730.boot.admin.project.monitor.job.service.ISystemJobLogService;
import com.hb0730.boot.admin.project.monitor.logininfo.model.entity.SystemLoginInfoEntity;
import com.hb0730.boot.admin.project.monitor.logininfo.service.ISystemLoginInfoService;
import com.hb0730.boot.admin.project.monitor.operlog.model.entity.SystemOperLogEntity;
import com.hb0730.boot.admin.project.monitor.operlog.service.ISystemOperLogService;
import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

/**
 * <p>
 * 异步工厂（产生任务用）
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class AsyncFactory {
    private static final Logger logger = LoggerFactory.getLogger(AsyncFactory.class);

    /**
     * <p>
     * 登录日志
     * </p>
     *
     * @param username 用户账号
     * @param status   登录状态
     * @param message  提示信息
     * @param args     列表
     * @return 任务task
     */
    public static TimerTask recordLoginInfo(final String username, final Integer status, final String message,
                                            final Object... args) {
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        final String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        return new TimerTask() {
            @Override
            public void run() {
//                String address = AddressUtils.getRealAddressByIP(ip);
                StringBuilder s = new StringBuilder();
                s.append("[" + ip + "]");
//                s.append(address);
                s.append("[" + username + "]");
                s.append("[" + status + "]");
                s.append("[" + message + "]");
//                 打印信息到日志
                logger.info(s.toString(), args);
//                 获取客户端操作系统
                String os = userAgent.getOperatingSystem().getName();
//                 获取客户端浏览器
                String browser = userAgent.getBrowser().getName();
//                 封装对象
                SystemLoginInfoEntity entity = new SystemLoginInfoEntity();
                entity.setStatus(status);
                entity.setUsername(username);
                entity.setIpaddr(ip);
//                logininfor.setLoginLocation(address);
                entity.setBrowser(browser);
                entity.setOs(os);
                entity.setMessage(message);
//                 日志状态
                SpringUtils.getBean(ISystemLoginInfoService.class).save(entity);
            }
        };
    }

    /**
     * 操作日志记录
     *
     * @param entity 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOper(final SystemOperLogEntity entity) {
        return new TimerTask() {
            @Override
            public void run() {
                SpringUtils.getBean(ISystemOperLogService.class).save(entity);
            }
        };
    }

    /**
     * <p>
     * 定时任务日志记录
     * </p>
     *
     * @param entity 定时任务日志
     * @return 任务task
     */
    public static TimerTask recordJobLog(final SystemJobLogEntity entity) {
        return new TimerTask() {
            @Override
            public void run() {
                SpringUtils.getBean(ISystemJobLogService.class).save(entity);
            }
        };
    }
}
