package com.hb0730.boot.admin.project.monitor.login.log.model.query;

import com.hb0730.boot.admin.domain.model.query.BaseParams;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 登录日志检索
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class LoginLogParams extends BaseParams {

    private String username;
    private String loginIp;
    private Integer status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
