package com.hb0730.boot.admin.project.system.role.controller;


import com.hb0730.boot.admin.annotation.ClassDescribe;
import com.hb0730.boot.admin.annotation.PreAuth;
import com.hb0730.boot.admin.domain.controller.AbstractBaseController;
import com.hb0730.boot.admin.domain.result.Result;
import com.hb0730.boot.admin.domain.result.Results;
import com.hb0730.boot.admin.project.system.role.model.dto.RoleExtDTO;
import com.hb0730.boot.admin.project.system.role.model.entity.RoleEntity;
import com.hb0730.boot.admin.project.system.role.model.query.RoleParams;
import com.hb0730.boot.admin.project.system.role.service.IRoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色  前端控制器
 *
 * @author bing_huang
 * @since 3.0.0
 */
@RestController
@RequestMapping("/api/v3/system/role")
@PreAuth("role")
@ClassDescribe("角色管理")
public class RoleController extends AbstractBaseController<Long, RoleExtDTO, RoleParams, RoleEntity> {
    private final IRoleService service;

    public RoleController(IRoleService service) {
        super(service);
        this.service = service;
    }

    /**
     * 修改角色分配的菜单
     *
     * @param id            角色id
     * @param permissionIds 权限id
     * @return 是否成功
     */
    @PostMapping("/update/permission/{id}")
    public Result<String> updateRolePermission(@PathVariable("id") Long id, @RequestBody List<Long> permissionIds) {
        service.updateRolePermission(id, permissionIds);
        return Results.resultSuccess("保存成功");
    }
}

