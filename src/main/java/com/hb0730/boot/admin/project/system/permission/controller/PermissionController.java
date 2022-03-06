package com.hb0730.boot.admin.project.system.permission.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.annotation.ClassDescribe;
import com.hb0730.boot.admin.annotation.PreAuth;
import com.hb0730.boot.admin.domain.controller.SuperSimpleBaseController;
import com.hb0730.boot.admin.domain.result.Result;
import com.hb0730.boot.admin.domain.result.R;
import com.hb0730.boot.admin.project.system.permission.model.dto.PermissionDTO;
import com.hb0730.boot.admin.project.system.permission.model.entity.PermissionEntity;
import com.hb0730.boot.admin.project.system.permission.model.query.PermissionParams;
import com.hb0730.boot.admin.project.system.permission.service.IPermissionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 权限  前端控制器
 *
 * @author bing_huang
 * @since 3.0.0
 */
@RestController
@RequestMapping("/system/permission")
@Validated
@ClassDescribe("权限管理")
@PreAuth("permission")
public class PermissionController extends SuperSimpleBaseController<Long, PermissionDTO, PermissionParams, PermissionEntity> {
    private final IPermissionService service;

    public PermissionController(IPermissionService service) {
        super(service);
        this.service = service;
    }

    @PostMapping("/menu/page/{menuId}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMINISTRATOR','permission:query')")
    public Result<Page<PermissionDTO>> getPermissionPageByMenuId(@PathVariable("menuId") Long menuId, @RequestBody PermissionParams params) {
        params.setMenuId(menuId);
        Page<PermissionDTO> result = service.page(params);
        return R.success(result);
    }

    @PostMapping("/menu/list/{menuId}")
    public Result<List<PermissionDTO>> getPermissionListByMenuId(@PathVariable("menuId") Long menuId, @RequestBody PermissionParams params) {
        params.setMenuId(menuId);
        List<PermissionDTO> result = service.list(params);
        return R.success(result);
    }

}

