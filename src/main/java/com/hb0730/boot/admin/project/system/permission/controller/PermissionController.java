package com.hb0730.boot.admin.project.system.permission.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.domain.controller.AbstractBaseController;
import com.hb0730.boot.admin.domain.result.Result;
import com.hb0730.boot.admin.domain.result.Results;
import com.hb0730.boot.admin.project.system.permission.model.dto.PermissionDTO;
import com.hb0730.boot.admin.project.system.permission.model.entity.PermissionEntity;
import com.hb0730.boot.admin.project.system.permission.model.query.PermissionParams;
import com.hb0730.boot.admin.project.system.permission.service.IPermissionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 权限  前端控制器
 *
 * @author bing_huang
 * @since 3.0.0
 */
@RestController
@RequestMapping("/api/v3/system/permission" )
public class PermissionController extends AbstractBaseController<Long, PermissionDTO, PermissionParams, PermissionEntity> {
    private final IPermissionService service;

    public PermissionController(IPermissionService service) {
        super(service);
        this.service = service;
    }

    @PostMapping("/menu/{menuId}" )
    public Result<Page<PermissionDTO>> getPermissionByMenuId(@PathVariable("menuId" ) Long menuId, @RequestBody PermissionParams params) {
        params.setMenuId(menuId);
        Page<PermissionDTO> result = service.getPermissionByMenuId(menuId, params.getPageNum(), params.getPageSize());
        return Results.resultSuccess(result);
    }

    @GetMapping("/menu/{menuId}" )
    public Result<List<PermissionDTO>> getPermissionByMenuId(@PathVariable("menuId" ) Long menuId) {
        List<PermissionDTO> result = service.getPermissionByMenuId(menuId);
        return Results.resultSuccess(result);
    }

}

