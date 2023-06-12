package com.hb0730.boot.admin.modules.sys.system.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.hb0730.boot.admin.base.R;
import com.hb0730.boot.admin.data.domain.BasePage;
import com.hb0730.boot.admin.modules.sys.system.model.entity.SysRole;
import com.hb0730.boot.admin.modules.sys.system.model.entity.SysRolePermission;
import com.hb0730.boot.admin.modules.sys.system.model.query.RoleQuery;
import com.hb0730.boot.admin.modules.sys.system.service.SysRolePermissionService;
import com.hb0730.boot.admin.modules.sys.system.service.SysRoleService;
import com.hb0730.boot.admin.security.util.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 系统角色
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/2/28
 */
@RestController
@RequestMapping("/sys/role")
@Tag(name = "系统：系统角色")
@RequiredArgsConstructor
public class SysRoleController {
    private final SysRoleService sysRoleService;
    private final SysRolePermissionService sysRolePermissionService;

    @GetMapping("/query/page")
    @PreAuthorize("@permission.hashPermission('role:query')")
    @Operation(summary = "分页查询")
    public R<BasePage<SysRole>> queryPage(@ParameterObject RoleQuery query) {
        BasePage<SysRole> page = sysRoleService.queryPage(query);
        return R.OK(page);
    }

    @GetMapping("/query/list")
    @Operation(summary = "列表查询")
    public R<List<SysRole>> queryList(@ParameterObject RoleQuery query) {
        List<SysRole> roles = sysRoleService.queryList(query);
        return R.OK(roles);
    }

    @GetMapping("/check/code")
    @Operation(summary = "校验code是否重复", parameters = {
            @Parameter(name = "id", required = false, description = "id"),
            @Parameter(name = "code", required = true, description = "角色编码")
    })
    public R<Boolean> checkRole(@RequestParam(value = "id", required = false) String id,
                                @RequestParam(value = "code") String code) {
        boolean checkCode = sysRoleService.checkCode(code, id);
        return R.OK(checkCode);
    }

    @PostMapping("/save")
    @PreAuthorize("@permission.hashPermission('role:save')")
    @Operation(summary = "新增")
    public R<SysRole> save(@Valid @RequestBody SysRole role) {
        String username = SecurityUtil.getCurrentUsername();
        role.setCreated(LocalDateTime.now());
        role.setCreatedBy(username);
        return sysRoleService.saveRole(role);
    }

    @PutMapping("/update")
    @PreAuthorize("@permission.hashPermission('role:update')")
    @Operation(summary = "修改")
    public R<SysRole> update(@RequestParam("id") String id, @Valid @RequestBody SysRole role) {
        String username = SecurityUtil.getCurrentUsername();
        role.setId(id);
        role.setModified(LocalDateTime.now());
        role.setModifiedBy(username);
        return sysRoleService.updateRole(role);
    }

    @GetMapping("/permission")
    @Operation(summary = "获取权限ID", parameters = {
            @Parameter(required = true, description = "角色ID", name = "id")
    })
    public R<List<String>> getPermissionIdsByRoleId(@RequestParam("id") String id) {
        Optional<List<String>> optional = sysRolePermissionService.findPermissionIdByRole(id);
        return R.OK(optional.orElse(Collections.emptyList()));
    }

    @PutMapping("/permission")
    @Operation(summary = "更新角色权限")
    @PreAuthorize("@permission.hashPermission('role:permission')")
    public R<Boolean> updatePermissionByRoleId(@Parameter(description = "角色ID") @RequestParam("id") String id,
                                               @RequestBody List<String> permissionIds) {
        String username = SecurityUtil.getCurrentUsername();
        List<SysRolePermission> permissions = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(permissionIds)) {
            permissions = permissionIds.stream().map(permissionId -> {
                SysRolePermission rolePermission = new SysRolePermission();
                rolePermission.setPermissionId(permissionId);
                rolePermission.setRoleId(id);
                rolePermission.setCreated(LocalDateTime.now());
                rolePermission.setCreatedBy(username);
                return rolePermission;
            }).collect(Collectors.toList());
        }
        return R.OK(sysRolePermissionService.updatePermissionByRoles(id, permissions));
    }

    @DeleteMapping("/del")
    @Operation(summary = "删除角色")
    @PreAuthorize("@permission.hashPermission('role:del')")
    public R<String> delRole(HttpServletRequest request, @Valid @RequestBody List<String> ids) {
        return sysRoleService.deleteByIds(ids);
    }
}
