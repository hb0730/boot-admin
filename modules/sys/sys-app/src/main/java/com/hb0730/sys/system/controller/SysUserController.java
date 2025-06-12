package com.hb0730.sys.system.controller;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.SecureUtil;
import com.hb0730.base.R;
import com.hb0730.base.utils.AesCryptoUtil;
import com.hb0730.base.utils.StrUtil;
import com.hb0730.core.data.Page;
import com.hb0730.operator.log.core.annotation.OperatorLog;
import com.hb0730.sys.system.define.operator.SysUserOperatorType;
import com.hb0730.sys.system.model.request.user.SysUserCreateRequest;
import com.hb0730.sys.system.model.request.user.SysUserQueryRequest;
import com.hb0730.sys.system.model.request.user.SysUserRestPasswordRequest;
import com.hb0730.sys.system.model.request.user.SysUserRoleUpdateRequest;
import com.hb0730.sys.system.model.request.user.SysUserUpdateRequest;
import com.hb0730.sys.system.model.vo.SysUserRoleVO;
import com.hb0730.sys.system.model.vo.SysUserVO;
import com.hb0730.sys.system.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/9
 */
@RestController
@RequestMapping("/sys/user")
@Tag(name = "基础设施: 用户管理")
@RequiredArgsConstructor
@Validated
public class SysUserController {
    private final SysUserService sysUserService;

    @Operation(summary = "分页查询用户")
    @GetMapping("/page")
    public R<Page<SysUserVO>> page(SysUserQueryRequest request) {
        Page<SysUserVO> page = sysUserService.page(request);
        return R.OK(page);
    }

    /**
     * 查询用户列表
     *
     * @param request 查询条件
     * @return 用户列表
     */
    @Operation(summary = "查询用户列表")
    @GetMapping("/list")
    public R<List<SysUserVO>> list(SysUserQueryRequest request) {
        List<SysUserVO> list = sysUserService.list(request);
        return R.OK(list);
    }

    @GetMapping("/has/{type}")
    @Operation(summary = "校验是否存在", description = "type: user_name 用户名,phone 手机号,email 邮箱")
    public R<String> hasExist(@PathVariable String type, String value, String id) {
        return sysUserService.hasExists(type, value, id);
    }

    /**
     * 保存用户
     *
     * @param request 用户
     * @return 是否成功
     */
    @Operation(summary = "保存用户")
    @PostMapping("/save")
    @OperatorLog(SysUserOperatorType.ADD)
    @PreAuthorize("hasAuthority('sys:user:add')")
    public R<String> save(@Validated @RequestBody SysUserCreateRequest request) {
        // 解密
        String iv = request.getCaptchaKey();
        String key = SecureUtil.sha256(request.getCaptchaKey() + request.getTimestamp());

        byte[] _key = HexUtil.decodeHex(key);
        byte[] _iv = iv.getBytes();
        if (StrUtil.isNotBlank(request.getPhone())) {
            String phone = AesCryptoUtil.decrypt(request.getPhone(), AesCryptoUtil.mode, _key, _iv);
            request.setPhone(phone);
        }

        if (StrUtil.isNotBlank(request.getEmail())) {
            String email = AesCryptoUtil.decrypt(request.getEmail(), AesCryptoUtil.mode, _key, _iv);
            request.setEmail(email);
        }

        if (StrUtil.isNotBlank(request.getPassword())) {
            String password = AesCryptoUtil.decrypt(request.getPassword(), AesCryptoUtil.mode, _key, _iv);
            request.setPassword(password);
        }

        sysUserService.create(request);
        return R.OK("保存成功");
    }

    /**
     * 更新用户
     *
     * @param id      用户ID
     * @param request 更新用户
     * @return 是否成功
     */
    @Operation(summary = "更新用户")
    @PutMapping("/update/{id}")
    @OperatorLog(SysUserOperatorType.EDIT)
    @PreAuthorize("hasAuthority('sys:user:update')")
    public R<String> update(@PathVariable String id, @RequestBody SysUserUpdateRequest request) {
        // 解密
        String iv = request.getCaptchaKey();
        String key = SecureUtil.sha256(request.getCaptchaKey() + request.getTimestamp());

        byte[] _key = HexUtil.decodeHex(key);
        byte[] _iv = iv.getBytes();
        if (StrUtil.isNotBlank(request.getPhone())) {
            String phone = AesCryptoUtil.decrypt(request.getPhone(), AesCryptoUtil.mode, _key, _iv);
            request.setPhone(phone);
        }

        if (StrUtil.isNotBlank(request.getEmail())) {
            String email = AesCryptoUtil.decrypt(request.getEmail(), AesCryptoUtil.mode, _key, _iv);
            request.setEmail(email);
        }

        sysUserService.updateById(id, request);
        return R.OK("更新成功");
    }


    /**
     * 修改密码
     *
     * @param id      用户ID
     * @param request 修改密码
     * @return 是否成功
     */
    @Operation(summary = "重置密码")
    @PutMapping("/rest_password/{id}")
    @OperatorLog(SysUserOperatorType.RESET_PASSWORD)
    @PreAuthorize("hasAuthority('sys:user:reset_password')")
    public R<String> resetPassword(@PathVariable String id, @Validated @RequestBody SysUserRestPasswordRequest request) {
        // 解密
        String iv = request.getCaptchaKey();
        String key = SecureUtil.sha256(request.getCaptchaKey() + request.getTimestamp());

        byte[] _key = HexUtil.decodeHex(key);
        byte[] _iv = iv.getBytes();

        String password = AesCryptoUtil.decrypt(request.getPassword(), AesCryptoUtil.mode, _key, _iv);

        return sysUserService.resetPassword(id, password);
    }


    @DeleteMapping("/del")
    @Operation(summary = "删除用户")
    @OperatorLog(SysUserOperatorType.DELETE)
    @PreAuthorize("hasAuthority('sys:user:delete')")
    public R<String> delete(String id) {
        sysUserService.deleteById(id);
        return R.OK("删除成功");
    }


    /**
     * 获取用户角色
     *
     * @param id 用户ID
     * @return 角色
     */
    @Operation(summary = "获取用户角色")
    @GetMapping("/roles")
    public R<List<SysUserRoleVO>> roles(String id) {
        List<SysUserRoleVO> roles = sysUserService.findRoles(id);
        return R.OK(roles);
    }

    @Operation(summary = "保存用户角色")
    @PutMapping("/roles/{id}")
    @PreAuthorize("hasAuthority('sys:user:assign_role')")
    @OperatorLog(SysUserOperatorType.GRANT_ROLE)
    public R<String> saveRoles(@PathVariable String id, @RequestBody List<SysUserRoleUpdateRequest> roles) {
        sysUserService.saveRoles(id, roles);
        return R.OK("保存成功");
    }
}
