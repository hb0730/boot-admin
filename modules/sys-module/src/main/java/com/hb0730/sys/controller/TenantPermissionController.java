package com.hb0730.sys.controller;

import com.hb0730.base.R;
import com.hb0730.base.utils.StrUtil;
import com.hb0730.sys.domain.dto.PermissionDto;
import com.hb0730.sys.domain.dto.PermissionSaveDto;
import com.hb0730.sys.domain.query.PermissionQuery;
import com.hb0730.sys.domain.vo.MenuTreeVO;
import com.hb0730.sys.service.SysTenantPermissionService;
import com.hb0730.utils.MenuUtil;
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
 * 商户权限
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/29
 */
@RestController
@RequestMapping("/sys/tenant/permission")
@Tag(name = "管理端：商户权限管理")
@RequiredArgsConstructor
public class TenantPermissionController {
    private final SysTenantPermissionService sysTenantPermissionService;

    /**
     * 权限列表【管理端】
     *
     * @return 权限列表
     */
    @GetMapping
    @Operation(summary = "权限列表")
    @PreAuthorize("hasAnyAuthority('tenant:permission:update')")
    public R<List<PermissionDto>> listPermission(PermissionQuery query) {
        List<PermissionDto> list = sysTenantPermissionService.listByQuery(query);
        return R.OK(list);
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
    public R<List<MenuTreeVO>> menuTree(@RequestParam(required = false, defaultValue = "false",
            name = "filterButton") Boolean filterButton) {
        List<PermissionDto> allEnabled = sysTenantPermissionService.findAllEnabled();
        List<MenuTreeVO> res = MenuUtil.buildMenuTree(allEnabled, filterButton);
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
    public R<String> savePermission(@Valid @RequestBody PermissionSaveDto permissionDto) {
        permissionDto.setId(null);
        if (StrUtil.isNotBlank(permissionDto.getParentId())
                && "0".equals(permissionDto.getParentId())) {
            permissionDto.setParentId(null);
        }
        if ("".equals(permissionDto.getParentId())) {
            permissionDto.setIcon(null);
        }
        sysTenantPermissionService.save(permissionDto);
        return R.OK("保存成功");
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
    public R<String> updatePermission(@RequestBody @Valid PermissionSaveDto permissionDto) {
        if (permissionDto.getId() == null) {
            return R.NG("id不能为空");
        }
        if (StrUtil.isNotBlank(permissionDto.getParentId())
                && "0".equals(permissionDto.getParentId())) {
            permissionDto.setParentId(null);
        }
        if ("".equals(permissionDto.getParentId())) {
            permissionDto.setIcon(null);
        }
        sysTenantPermissionService.updateById(permissionDto);
        return R.OK("更新成功");
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
    public R<String> deletePermission(String id) {
        sysTenantPermissionService.deleteById(id);
        return R.OK("删除成功");
    }
}
