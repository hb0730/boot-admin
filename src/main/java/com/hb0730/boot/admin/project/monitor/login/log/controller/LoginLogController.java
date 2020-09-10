package com.hb0730.boot.admin.project.monitor.login.log.controller;


import com.hb0730.boot.admin.annotation.ClassDescribe;
import com.hb0730.boot.admin.annotation.Log;
import com.hb0730.boot.admin.annotation.PreAuth;
import com.hb0730.boot.admin.commons.enums.BusinessTypeEnum;
import com.hb0730.boot.admin.domain.controller.AbstractBaseController;
import com.hb0730.boot.admin.domain.result.Result;
import com.hb0730.boot.admin.domain.result.Results;
import com.hb0730.boot.admin.project.monitor.login.log.model.dto.LoginLogDTO;
import com.hb0730.boot.admin.project.monitor.login.log.model.entity.LoginLogEntity;
import com.hb0730.boot.admin.project.monitor.login.log.model.query.LoginLogParams;
import com.hb0730.boot.admin.project.monitor.login.log.service.ILoginLogService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录日志  前端控制器
 *
 * @author bing_huang
 * @since 3.0.0
 */
@RestController
@RequestMapping("/api/v3/monitor/log/login")
@PreAuth("login:log")
@ClassDescribe("登录日志")
public class LoginLogController extends AbstractBaseController<Long, LoginLogDTO, LoginLogParams, LoginLogEntity> {

    private final ILoginLogService service;

    public LoginLogController(ILoginLogService service) {
        super(service);
        this.service = service;
    }

    /**
     * 清除
     *
     * @return 是否成功
     */
    @GetMapping("/clean")
    @Log(value = "清除", businessType = BusinessTypeEnum.CLEAN)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMINISTRATOR','login:log:clean')")
    public Result<String> clean() {
        service.clean();
        return Results.resultSuccess("清除成功");
    }


}

