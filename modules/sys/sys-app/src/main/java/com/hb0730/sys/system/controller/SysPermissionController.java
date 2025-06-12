package com.hb0730.sys.system.controller;

import com.hb0730.base.R;
import com.hb0730.base.utils.TreeUtil;
import com.hb0730.operator.log.core.annotation.OperatorLog;
import com.hb0730.sys.system.define.operator.SysPermissionOperatorType;
import com.hb0730.sys.system.model.request.permission.SysPermissionCreateRequest;
import com.hb0730.sys.system.model.request.permission.SysPermissionQueryRequest;
import com.hb0730.sys.system.model.request.permission.SysPermissionTreeQueryRequest;
import com.hb0730.sys.system.model.vo.SysPermissionTreeVO;
import com.hb0730.sys.system.model.vo.SysPermissionVO;
import com.hb0730.sys.system.service.SysPermissionService;
import io.swagger.v3.oas.annotations.Operation;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 菜单权限
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/5
 */
@RestController
@RequestMapping("/sys/permission")
@Slf4j
@Tag(name = "基础设施: 菜单&权限管理")
@Validated
@RequiredArgsConstructor
public class SysPermissionController {
    private final SysPermissionService permissionService;

    /**
     * 菜单树
     *
     * @return 菜单树
     */
//    @GetMapping("/tree")
//    @Operation(description = "菜单树")
    public R<List<SysPermissionTreeVO>> tree(SysPermissionTreeQueryRequest query) {
        List<SysPermissionTreeVO> nodes = permissionService.tree(query);
        List<SysPermissionTreeVO> tree = TreeUtil.build(nodes);
        return R.OK(tree);
    }

    /**
     * 权限列表
     *
     * @param query 查询条件
     * @return 权限列表
     */
    @GetMapping("/list")
    @Operation(summary = "权限列表")
    public R<List<SysPermissionVO>> list(SysPermissionQueryRequest query) {
        List<SysPermissionVO> res = permissionService.list(query);
        return R.OK(res);
    }

    @PostMapping("/save")
    @Operation(summary = "保存权限")
    @OperatorLog(SysPermissionOperatorType.ADD)
    @PreAuthorize("hasAuthority('sys:permission:add')")
    public R<String> save(@RequestBody SysPermissionCreateRequest request) {
        permissionService.create(request);
        return R.OK();
    }

    @Operation(summary = "更新权限")
    @PutMapping("/update/{id}")
    @OperatorLog(SysPermissionOperatorType.EDIT)
    @PreAuthorize("hasAuthority('sys:permission:update')")
    public R<String> update(@PathVariable String id, @RequestBody SysPermissionCreateRequest request) {
        permissionService.updateById(id, request);
        return R.OK();
    }

    /**
     * 删除权限
     *
     * @param id 权限id
     * @return 是否成功
     */
    @Operation(summary = "删除权限")
    @DeleteMapping("/del")
    @OperatorLog(SysPermissionOperatorType.DELETE)
    @PreAuthorize("hasAuthority('sys:permission:delete')")
    public R<String> deletePermission(String id) {
        permissionService.deletePermission(id);
        return R.OK();
    }

}
