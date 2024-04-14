package com.hb0730.modules.sys.controller;

import com.hb0730.base.R;
import com.hb0730.base.utils.PasswordUtil;
import com.hb0730.common.api.JR;
import com.hb0730.common.api.JsfPage;
import com.hb0730.rpc.sys.system.domain.UserDto;
import com.hb0730.rpc.sys.system.domain.UserRestPwdDto;
import com.hb0730.rpc.sys.system.domain.query.UserQuery;
import com.hb0730.rpc.sys.system.service.UserRpcService;
import com.hb0730.security.util.SecurityUtil;
import com.hb0730.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/23
 */
@RestController
@RequestMapping("/sys/user")
@RequiredArgsConstructor
@Tag(name = "管理端：用户管理")
public class UserController {
    private final UserRpcService userRpcService;


    /**
     * 分页查询用户
     *
     * @param query 查询条件
     * @return 用户分页数据
     */
    @GetMapping
    @Operation(summary = "分页查询用户")
    @PreAuthorize("hasAnyAuthority('sys:user:list')")
    public R<JsfPage<UserDto>> page(UserQuery query) {
        JR<JsfPage<UserDto>> res = userRpcService.page(query);
        return ResponseUtil.converter(res);
    }

    /**
     * 查询用户列表
     *
     * @param query 查询条件
     * @return 用户列表
     */
    @GetMapping("/list")
    @Operation(summary = "查询用户列表")
    public R<List<UserDto>> list(UserQuery query) {
        JR<List<UserDto>> jr = userRpcService.list(query);
        return ResponseUtil.converter(jr);
    }

    /**
     * 用户名是否存在
     *
     * @param username .
     * @param id       .
     * @return .
     */
    @GetMapping("/existsByUsername")
    @Operation(summary = "用户名是否存在")
    @Parameters({
            @Parameter(name = "username", description = "用户名", required = true, example = "admin"),
            @Parameter(name = "id", description = "需要排除的用户ID", required = false, example = "1")
    })
    public R<Boolean> existsByUsernameAndIdNot(@RequestParam("username") String username,
                                               @RequestParam(name = "id", required = false) Long id) {
        JR<Boolean> jr = userRpcService.existsByUsernameAndIdNot(username, id);
        return ResponseUtil.converter(jr);
    }

    /**
     * 保存用户
     *
     * @param dto 用户信息
     * @return 是否成功
     */
    @PostMapping
    @Operation(summary = "保存用户")
    @PreAuthorize("hasAnyAuthority('sys:user:save')")
    public R<String> save(@Valid @RequestBody UserDto dto) {
        String username = SecurityUtil.getUsername();
        // 默认密码
        dto.setPassword(PasswordUtil.encoder("123456"));
        dto.setCreatedBy(username);
        dto.setCreated(new Date());
        JR<String> jr = userRpcService.save(dto);
        return ResponseUtil.converter(jr);
    }

    /**
     * 更新用户
     *
     * @param dto 用户信息
     * @return 是否成功
     */
    @PutMapping
    @Operation(summary = "更新用户")
    @PreAuthorize("hasAnyAuthority('sys:user:update')")
    public R<String> update(@Valid @RequestBody UserDto dto) {
        String username = SecurityUtil.getUsername();
        dto.setModified(new Date());
        dto.setModifiedBy(username);

        JR<String> jr = userRpcService.updateById(dto);
        return ResponseUtil.converter(jr);
    }

    /**
     * 删除用户
     *
     * @param id 用户ID
     * @return 是否成功
     */
    @DeleteMapping
    @Operation(summary = "删除用户")
    @PreAuthorize("hasAnyAuthority('sys:user:delete','ROLE_ADMIN')")
    @Parameters({
            @Parameter(name = "id", description = "用户ID", required = true, example = "1")
    })
    public R<String> delete(@RequestParam("id") Long id) {
        JR<String> jr = userRpcService.deleteById(id);
        return ResponseUtil.converter(jr);
    }


    /**
     * 重置密码
     *
     * @param dto 数据
     * @return 是否成功
     */
    @PutMapping("/resetPassword")
    @Operation(summary = "重置密码")
    @PreAuthorize("hasAnyAuthority('sys:user:resetPassword','ROLE_ADMIN')")
    public R<String> resetPassword(@Valid @RequestBody UserRestPwdDto dto) {
        String password = dto.getPassword();
        String pwd = PasswordUtil.encoder(password);
        dto.setPassword(pwd);
        String username = SecurityUtil.getUsername();
        dto.setOperator(username);
        JR<String> jr = userRpcService.resetPassword(dto);
        return ResponseUtil.converter(jr);
    }
}
