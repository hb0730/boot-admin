package com.hb0730.sys.controller;

import com.hb0730.base.R;
import com.hb0730.common.api.JsfPage;
import com.hb0730.sys.domain.dto.RoleDto;
import com.hb0730.sys.domain.query.RoleQuery;
import com.hb0730.sys.service.SysRoleService;
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
 * @date 2024/4/29
 */
@RestController
@RequestMapping("/sys/role")
@Tag(name = "管理端：角色管理")
@RequiredArgsConstructor
public class RoleController {
    private final SysRoleService sysRoleService;


    /**
     * 查询
     *
     * @param query 查询条件
     * @return 角色列表
     */
    @GetMapping("/list")
    @Operation(summary = "列表查询")
    public R<List<RoleDto>> list(RoleQuery query) {
        List<RoleDto> res = sysRoleService.list(query);
        return R.OK(res);
    }

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return 角色列表
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询")
    @PreAuthorize("hasAnyAuthority('sys:role:list')")
    public R<JsfPage<RoleDto>> page(RoleQuery query) {
        JsfPage<RoleDto> res = sysRoleService.page(query);
        return R.OK(res);
    }


    /**
     * 编码是否存在
     *
     * @param code 编码
     * @param id   角色ID
     * @return .
     */
    @GetMapping("/existsByCode")
    @Operation(summary = "编码是否存在")
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "code", description = "编码", required = true),
            @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "需要排除的编码", required = false)
    })
    public R<Boolean> existsByCode(@RequestParam(name = "code") String code, @RequestParam(name = "id", required =
            false) String id) {
        Boolean res = sysRoleService.existsCode(code, id);
        return R.OK(res);
    }

    /**
     * 添加
     *
     * @param roleDto 角色
     * @return 是否成功
     */
    @PostMapping
    @Operation(summary = "添加")
    @PreAuthorize("hasAnyAuthority('sys:role:add')")
    public R<String> save(@Valid @RequestBody RoleDto roleDto) {
        sysRoleService.save(roleDto);
        return R.OK();
    }

    /**
     * 更新
     *
     * @param roleDto 角色
     * @return 是否成功
     */
    @PutMapping
    @Operation(summary = "更新")
    @PreAuthorize("hasAnyAuthority('sys:role:update')")
    public R<String> updateById(@RequestBody RoleDto roleDto) {
        if (roleDto.getId() == null) {
            return R.NG("角色ID不能为空");
        }
        sysRoleService.updateById(roleDto);
        return R.OK();
    }

    /**
     * 删除
     *
     * @param id 角色id
     * @return 是否成功
     */
    @DeleteMapping
    @Operation(summary = "删除")
    @PreAuthorize("hasAnyAuthority('sys:role:delete')")
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "角色id", required = true)
    })
    public R<String> deleteById(@RequestParam(name = "id") String id) {
        sysRoleService.deleteById(id);
        return R.OK();
    }

    /**
     * 查询角色权限
     *
     * @param roleId 角色id
     * @return 权限id
     */
    @GetMapping("/permission")
    @Operation(summary = "查询角色权限")
    @PreAuthorize("hasAnyAuthority('sys:role:assignPermission')")
    public R<List<String>> getPermissionIds(@RequestParam(name = "roleId") String roleId) {
        List<String> res = sysRoleService.getPermissionIds(roleId);
        return R.OK(res);
    }

    /**
     * 分配权限
     *
     * @param roleId  角色id
     * @param menuIds 菜单id
     * @return 是否成功
     */
    @PutMapping("/assignPermission")
    @Operation(summary = "分配权限")
    @PreAuthorize("hasAnyAuthority('sys:role:assignPermission')")
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "roleId", description = "角色id", required = true),
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "菜单与权限ID", required = true)
    public R<String> assignPermission(@RequestParam(name = "roleId") String roleId,
                                      @RequestBody List<String> menuIds) {
        sysRoleService.assign(roleId, menuIds);
        return R.OK();
    }
}
