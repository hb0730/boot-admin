package com.hb0730.boot.admin.project.system.dept.controller;


import com.hb0730.boot.admin.domain.controller.AbstractBaseController;
import com.hb0730.boot.admin.project.system.dept.model.dto.DeptDTO;
import com.hb0730.boot.admin.project.system.dept.model.entity.DeptEntity;
import com.hb0730.boot.admin.project.system.dept.model.query.DeptParams;
import com.hb0730.boot.admin.project.system.dept.service.IDeptService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 部门  前端控制器
 *
 * @author bing_huang
 * @since 3.0.0
 */
@RestController
@RequestMapping("/api/v3/system/dept" )
public class DeptController extends AbstractBaseController<Long, DeptDTO, DeptParams, DeptEntity> {
    private final IDeptService service;

    public DeptController(IDeptService service) {
        super(service);
        this.service = service;
    }

}

