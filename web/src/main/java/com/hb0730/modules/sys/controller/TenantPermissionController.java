package com.hb0730.modules.sys.controller;

import com.hb0730.base.R;
import com.hb0730.commons.JR;
import com.hb0730.domain.MenuTreeVO;
import com.hb0730.rpc.sys.tenant.domain.TenantPermissionDto;
import com.hb0730.rpc.sys.tenant.domain.TenantPermissionSmallDto;
import com.hb0730.rpc.sys.tenant.domain.query.PermissionQuery;
import com.hb0730.rpc.sys.tenant.service.TenantPermissionRpcService;
import com.hb0730.security.util.SecurityUtil;
import com.hb0730.utils.PermissionUtil;
import com.hb0730.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
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

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/27
 */
@RestController
@RequestMapping("/tenant/permission")
@Tag(name = "管理端：商户权限管理")
@RequiredArgsConstructor
public class TenantPermissionController {
    private final TenantPermissionRpcService permissionRpcService;

    /**
     * 权限列表
     *
     * @return 权限列表
     */
    @GetMapping
    @Operation(summary = "权限列表")
    @PreAuthorize("hasAnyAuthority('tenant:permission:update')")
    public R<List<TenantPermissionDto>> listPermission(PermissionQuery query) {
        JR<List<TenantPermissionDto>> jr = permissionRpcService.listDefaultRootQueryOrderRank(query);
        return ResponseUtil.converter(jr);
    }

    /**
     * 菜单树
     *
     * @return .
     */
    @GetMapping("/menu/tree")
    @Operation(summary = "菜单树")
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "filterButton", description = "是否过滤按钮", required = false)
    })
    public R<List<MenuTreeVO>> menuTree(@RequestParam(required = false, defaultValue = "false", name = "filterButton") Boolean filterButton) {
        JR<List<TenantPermissionDto>> jr = permissionRpcService.findAllEnabled();
        if (!jr.isSuccess()) {
            return R.NG(jr.getMessage());
        }
        List<TenantPermissionDto> listData = jr.getResult();
        List<MenuTreeVO> res = PermissionUtil.buildBasMenuTree(listData, filterButton);
        return R.OK(res);
    }

    /**
     * 保存权限
     *
     * @param permissionDto 权限
     * @return 是否成功
     */
    @PostMapping
    @Operation(summary = "保存权限")
    @PreAuthorize("hasAnyAuthority('tenant:permission:update')")
    public R<String> savePermission(@RequestBody @Valid TenantPermissionSmallDto permissionDto) {
        String username = SecurityUtil.getUsername();
        permissionDto.setCreatedBy(username);
        permissionDto.setCreated(new java.util.Date());
        JR<String> jr = permissionRpcService.save(permissionDto);
        return ResponseUtil.converter(jr);
    }

    /**
     * 更新权限
     *
     * @param permissionDto 权限
     * @return 是否成功
     */
    @PutMapping
    @Operation(summary = "更新权限")
    @PreAuthorize("hasAnyAuthority('tenant:permission:update')")
    public R<String> updatePermission(@RequestBody @Valid TenantPermissionSmallDto permissionDto) {
        if (permissionDto.getId() == null) {
            return R.NG("id不能为空");
        }
        String username = SecurityUtil.getUsername();
        permissionDto.setModifiedBy(username);
        permissionDto.setModified(new java.util.Date());
        JR<String> jr = permissionRpcService.updateById(permissionDto);
        return ResponseUtil.converter(jr);
    }

    /**
     * 删除权限
     *
     * @param id 权限id
     * @return 是否成功
     */
    @DeleteMapping
    @Operation(summary = "删除权限")
    @PreAuthorize("hasAnyAuthority('tenant:permission:update')")
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "权限id", required = true, example = "1", schema = @io.swagger.v3.oas.annotations.media.Schema(type = "number"))
    })
    public R<String> deletePermission(Long id) {
        JR<String> jr = permissionRpcService.deleteById(id);
        return ResponseUtil.converter(jr);
    }
}
