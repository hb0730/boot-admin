package com.hb0730.sys.base.controller;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.SecureUtil;
import com.hb0730.base.R;
import com.hb0730.base.meta.IUserInfo;
import com.hb0730.base.utils.AesCryptoUtil;
import com.hb0730.base.utils.StrUtil;
import com.hb0730.core.data.Page;
import com.hb0730.operator.log.core.annotation.OperatorLog;
import com.hb0730.security.SecurityUtils;
import com.hb0730.sys.base.define.operator.AuthenticationOperatorType;
import com.hb0730.sys.base.granter.TokenGranterBuilder;
import com.hb0730.sys.base.model.request.RestPasswordRequest;
import com.hb0730.sys.base.model.vo.UserInfoVO;
import com.hb0730.sys.base.service.AuthUserService;
import com.hb0730.sys.system.model.request.log.SysOperatorLogQueryRequest;
import com.hb0730.sys.system.model.vo.SysOperatorLogVO;
import com.hb0730.sys.system.service.SysOperatorLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * 用户认证
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/9
 */
@RestController
@RequestMapping("/auth/user")
@RequiredArgsConstructor
@Slf4j
@Validated
@Tag(name = "认证服务")
public class AuthUserController {
    private final TokenGranterBuilder tokenGranterBuilder;
    private final AuthUserService authUserService;
    private final SysOperatorLogService operatorLogService;
    private static final List<String> USER_INFO = List.of("email", "phone");


    /**
     * 获取当前用户信息
     */
    @Operation(summary = "获取当前用户信息")
    @GetMapping("")
    public R<UserInfoVO> currentUser(HttpServletRequest request) {
        // 获取登录 token
        Optional<String> tokenOptional = SecurityUtils.obtainAuthorization(request);
        if (tokenOptional.isEmpty()) {
            return R.NG("获取用户信息失败,token为空");
        }
        String token = tokenOptional.get();
        return tokenGranterBuilder.defaultGranter().currentUser(token);
    }

    /**
     * 获取用户相关信息，比如 邮件，手机等
     *
     * @param name .
     * @return .
     */
    @Operation(summary = "获取用户相关信息,比如 邮件，手机等")
    @GetMapping("/info")
    @Parameter(name = "name", description = "获取用户信息的字段 email,phone", required = true)
    public R<String> getUserInfo(String name) {
        Optional<IUserInfo> loginUsername = SecurityUtils.getLoginUser();
        if (loginUsername.isEmpty()) {
            return R.NG("获取用户信息失败,token为空");
        }
        if (!USER_INFO.contains(name)) {
            return R.NG("不支持的操作");
        }
        IUserInfo userInfo = loginUsername.get();
        return switch (name) {
            case "email" -> R.OK(userInfo.getEmail());
            case "phone" -> R.OK(userInfo.getPhone());
            default -> R.NG("不支持的操作");
        };
    }

    /**
     * 重置密码
     */
    @Operation(summary = "重置当前用户密码")
    @PutMapping("/reset_password")
    @OperatorLog(AuthenticationOperatorType.UPDATE_PASSWORD)
    public R<String> resetPassword(HttpServletRequest request,
                                   @RequestBody RestPasswordRequest data) {

        // 解密
        String iv = data.getCaptchaKey();
        String key = SecureUtil.sha256(data.getCaptchaKey() + data.getTimestamp());
        byte[] _key = HexUtil.decodeHex(key);
        byte[] _iv = iv.getBytes();
        // 解密旧密码
        String oldPassword = AesCryptoUtil.decrypt(data.getOldPassword(), AesCryptoUtil.mode, _key, _iv);
        // 解密新密码
        String newPassword = AesCryptoUtil.decrypt(data.getNewPassword(), AesCryptoUtil.mode, _key, _iv);
        data.setOldPassword(oldPassword);
        data.setNewPassword(newPassword);
        // 获取登录 token
        Optional<String> tokenOptional = SecurityUtils.obtainAuthorization(request);
        if (tokenOptional.isEmpty()) {
            return R.NG("获取用户信息失败,token为空");
        }
        String token = tokenOptional.get();
        // 重置密码
        return authUserService.restPassword(token, data);
    }

    @Operation(summary = "查询操作日志")
    @GetMapping("/operator_log/page")
    public R<Page<SysOperatorLogVO>> queryOperatorLog(SysOperatorLogQueryRequest request) {
        SecurityUtils.getLoginUserId().ifPresent(request::setOperatorId);
        if (StrUtil.isBlank(request.getOperatorId())) {
            return R.NG("获取用户信息失败,token为空");
        }
        Page<SysOperatorLogVO> page = operatorLogService.page(request);
        return R.OK(page);
    }
}
