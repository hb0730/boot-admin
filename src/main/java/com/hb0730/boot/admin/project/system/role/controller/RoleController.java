package com.hb0730.boot.admin.project.system.role.controller;


import com.hb0730.boot.admin.domain.controller.AbstractBaseController;
import com.hb0730.boot.admin.project.system.role.model.dto.RoleDTO;
import com.hb0730.boot.admin.project.system.role.model.entity.RoleEntity;
import com.hb0730.boot.admin.project.system.role.model.query.RoleParams;
import com.hb0730.boot.admin.project.system.role.service.IRoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色  前端控制器
 *
 * @author bing_huang
 * @since 3.0.0
 */
@RestController
@RequestMapping("/api/v3/system/role")
public class RoleController extends AbstractBaseController<Long, RoleDTO, RoleParams, RoleEntity> {
    private final IRoleService service;

    public RoleController(IRoleService service) {
        super(service);
        this.service = service;
    }



}

