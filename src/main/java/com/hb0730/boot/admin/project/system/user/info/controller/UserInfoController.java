package com.hb0730.boot.admin.project.system.user.info.controller;


import com.hb0730.boot.admin.commons.enums.ResponseStatusEnum;
import com.hb0730.boot.admin.domain.controller.AbstractBaseController;
import com.hb0730.boot.admin.domain.result.Result;
import com.hb0730.boot.admin.domain.result.Results;
import com.hb0730.boot.admin.project.system.user.account.service.IUserAccountService;
import com.hb0730.boot.admin.project.system.user.info.model.entity.UserInfoEntity;
import com.hb0730.boot.admin.project.system.user.info.model.query.UserAccount;
import com.hb0730.boot.admin.project.system.user.info.model.query.UserInfoParams;
import com.hb0730.boot.admin.project.system.user.info.model.vo.UserInfoVO;
import com.hb0730.boot.admin.project.system.user.info.service.IUserInfoService;
import com.hb0730.boot.admin.project.system.user.info.service.impl.UserInfoServiceImpl;
import com.hb0730.boot.admin.security.model.User;
import com.hb0730.boot.admin.security.utils.SecurityUtils;
import com.hb0730.commons.lang.StringUtils;
import com.hb0730.commons.spring.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户信息  前端控制器
 *
 * @author bing_huang
 * @since 3.0.0
 */
@RestController
@RequestMapping("/api/v3/system/user/info")
public class UserInfoController extends AbstractBaseController<Long, UserInfoVO, UserInfoParams, UserInfoEntity> {
    private final IUserInfoService service;

    public UserInfoController(IUserInfoService service) {
        super(service);
        this.service = service;
    }

    /**
     * 获取用户详情
     *
     * @param id 用户id
     * @return 用户详情(不包含相关信息)
     */
    @GetMapping("/{id}")
    public Result<UserInfoVO> getUserInfoById(@PathVariable("id") Long id) {
        UserInfoEntity entity = service.getById(id);
        UserInfoVO info = BeanUtils.transformFrom(entity, UserInfoVO.class);
        return Results.resultSuccess(info);
    }

    /**
     * 获取当前请求认证的用户
     *
     * @param request 请求
     * @return 用户详情(不包含相关信息)
     */
    @GetMapping
    public Result<UserInfoVO> getCurrentInfo(HttpServletRequest request) {
        User user = SecurityUtils.getCurrentUser();
        UserInfoVO info = BeanUtils.transformFrom(user, UserInfoVO.class);
        return Results.resultSuccess(info);
    }

    /**
     * 修改密码
     *
     * @param id      用户id
     * @param account 账号信息
     * @return 是否成功
     */
    @PostMapping("/update/password/{id}")
    public Result<String> updatePassword(@PathVariable("id") Long id, @RequestBody @Validated UserAccount account) {
        String newPassword = account.getNewPassword();
        String newPassword2 = account.getNewPassword2();
        if (!StringUtils.equals(newPassword, newPassword2)) {
            Results.result(ResponseStatusEnum.USER_PASSWORD_V_FAIL, "确认密码错误");
        }
        IUserAccountService accountService = ((UserInfoServiceImpl) service.getThis()).getAccountService();
        accountService.updatePassword(id, account.getOldPassword(), newPassword);
        return Results.resultSuccess("修改成功");
    }

}

