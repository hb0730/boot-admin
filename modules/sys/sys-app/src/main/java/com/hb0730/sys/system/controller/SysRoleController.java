package com.hb0730.sys.system.controller;

import com.hb0730.base.R;
import com.hb0730.core.data.Page;
import com.hb0730.operator.log.core.annotation.OperatorLog;
import com.hb0730.sys.system.define.operator.SysRoleOperatorType;
import com.hb0730.sys.system.model.request.role.SysRoleCreateRequest;
import com.hb0730.sys.system.model.request.role.SysRoleQueryRequest;
import com.hb0730.sys.system.model.request.role.SysRoleUpdateRequest;
import com.hb0730.sys.system.model.vo.SysRoleVO;
import com.hb0730.sys.system.service.SysRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色 controller
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/8
 */
@RestController
@RequestMapping("/sys/role")
@Slf4j
@RequiredArgsConstructor
@Validated
@Tag(name = "基础设施: 角色管理")
public class SysRoleController {
    private final SysRoleService sysRoleService;


    /**
     * 是否存在
     *
     * @param id id
     * @return 是否存在
     */
    @GetMapping("/has_code")
    @Operation(summary = "code是否存在", description = "code是否存在,id存在则排除id")
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "需要排除的id", required = false),
            @io.swagger.v3.oas.annotations.Parameter(name = "code", description = "角色标识")
    })
    public R<String> hasCode(
            @RequestParam(required = false) String id,
            @RequestParam String code) {
        return sysRoleService.hasCode(id, code);
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询")
    public R<Page<SysRoleVO>> page(SysRoleQueryRequest query) {
        log.info("分页查询角色:{}", query);
        return R.OK(sysRoleService.page(query));
    }

    @GetMapping("/list")
    @Operation(summary = "查询所有")
    public R<List<SysRoleVO>> list(SysRoleQueryRequest query) {
        return R.OK(sysRoleService.list(query));
    }

    @Operation(summary = "保存")
    @PostMapping("/save")
    @OperatorLog(SysRoleOperatorType.ADD)
    @PreAuthorize("hasAuthority('sys:role:add')")
    public R<String> save(@RequestBody SysRoleCreateRequest request) {
        sysRoleService.create(request);
        return R.OK();
    }

    @Operation(summary = "更新")
    @PutMapping("/update/{id}")
    @OperatorLog(SysRoleOperatorType.EDIT)
    @PreAuthorize("hasAuthority('sys:role:update')")
    public R<String> update(@PathVariable String id, @RequestBody SysRoleUpdateRequest request) {
        sysRoleService.updateById(id, request);
        return R.OK();
    }

    /**
     * 获取权限
     *
     * @param id 角色id
     * @return 权限
     */
    @GetMapping("/perms")
    @Operation(summary = "获取权限")
    public R<List<String>> getPerms(String id) {
        List<String> perms = sysRoleService.getPerms(id);
        return R.OK(perms);
    }

    /**
     * 赋权
     *
     * @param id            角色id
     * @param permissionIds 权限id
     * @return 是否成功
     */
    @Operation(summary = "赋权")
    @PutMapping("/perms/{id}")
    @OperatorLog(SysRoleOperatorType.GRANT)
    @PreAuthorize("hasAuthority('sys:role:grant')")
    public R<String> grant(@PathVariable String id, @RequestBody List<String> permissionIds) {
        log.info("角色赋权:{}", id);
        sysRoleService.grant(id, permissionIds);
        return R.OK();
    }

    @Operation(summary = "删除")
    @DeleteMapping("/del")
    @OperatorLog(SysRoleOperatorType.DELETE)
    @PreAuthorize("hasAuthority('sys:role:delete')")
    public R<String> del(String id) {
        sysRoleService.deleteById(id);
        return R.OK();
    }

}
