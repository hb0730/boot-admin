package com.hb0730.basic.controller;

import com.hb0730.base.R;
import com.hb0730.basic.domain.dto.BasRoleDto;
import com.hb0730.basic.domain.query.BasRoleQuery;
import com.hb0730.basic.service.BasRoleService;
import com.hb0730.common.api.JsfPage;
import com.hb0730.security.utils.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/30
 */
@RestController
@RequestMapping("/bas/role")
@Tag(name = "履约端：角色管理")
@RequiredArgsConstructor
@Slf4j
public class BasRoleController {
    private final BasRoleService service;

    /**
     * 分页查询
     *
     * @param query .
     * @return .
     */
    @GetMapping
    @Operation(summary = "分页查询")
    @PreAuthorize("hasAuthority('bas:role:query')")
    public R<JsfPage<BasRoleDto>> page(BasRoleQuery query) {
        String sysCode = SecurityUtil.getSysCode();
        query.setSysCode(sysCode);
        JsfPage<BasRoleDto> res = service.page(query);
        return R.OK(res);
    }

    /**
     * 查询
     *
     * @param query .
     * @return .
     */
    @GetMapping("/list")
    @Operation(summary = "查询")
    public R<List<BasRoleDto>> list(BasRoleQuery query) {
        String sysCode = SecurityUtil.getSysCode();
        query.setSysCode(sysCode);
        List<BasRoleDto> res = service.list(query);
        return R.OK(res);
    }

    /**
     * 保存
     *
     * @param dto .
     * @return .
     */
    @PostMapping
    @Operation(summary = "保存")
    @PreAuthorize("hasAuthority('bas:role:save')")
    public R<String> save(@Valid @RequestBody BasRoleDto dto) {
        String sysCode = SecurityUtil.getSysCode();
        dto.setSysCode(sysCode);
        service.save(dto);
        return R.OK();
    }

    /**
     * 修改
     *
     * @param dto .
     * @return .
     */
    @PutMapping
    @Operation(summary = "修改")
    @PreAuthorize("hasAuthority('bas:role:update')")
    public R<String> update(@Valid @RequestBody BasRoleDto dto) {
        if (dto.getId() == null) {
            return R.NG("id不能为空");
        }
        String sysCode = SecurityUtil.getSysCode();
        dto.setSysCode(sysCode);
        service.updateById(dto);
        return R.OK();
    }

    /**
     * 删除
     *
     * @param id .
     * @return .
     */
    @DeleteMapping
    @Operation(summary = "删除")
    @PreAuthorize("hasAuthority('bas:role:delete')")
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "id", required = true)
    })
    public R<String> delete(@RequestParam String id) {
        service.deleteById(id);
        return R.OK();
    }

    /**
     * code是否存在
     *
     * @param code 角色标识
     * @param id   需要排除的id
     * @return .
     */
    @GetMapping("/existsByCode")
    @Operation(summary = "code是否存在")
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "code", description = "角色标识", required = true),
            @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "需要排除的id", required = false)
    })
    public R<Boolean> existsByCode(@RequestParam String code, @RequestParam(required = false) String id) {
        String sysCode = SecurityUtil.getSysCode();
        Boolean res = service.existsCode(code, sysCode, id);
        return R.OK(res);
    }

    /**
     * 获取角色权限
     *
     * @param id 角色id
     * @return .
     */
    @GetMapping("/permission")
    @Operation(summary = "获取角色权限")
    @PreAuthorize("hasAuthority('bas:role:grant')")
    public R<List<String>> getPermission(String id) {
        List<String> res = service.getPermissionIdsByRoleId(id);
        return R.OK(res);
    }

    /**
     * 授权
     *
     * @param id            角色id
     * @param permissionIds 权限id
     * @return .
     */
    @PutMapping("/grant")
    @Operation(summary = "授权")
    @PreAuthorize("hasAuthority('bas:role:grant')")
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "roleId", description = "角色id", required = true),
    })
    public R<String> grant(@RequestParam String id, @RequestBody List<String> permissionIds) {
        service.grantPermission(id, permissionIds);
        return R.OK();
    }
}
