package com.hb0730.boot.admin.project.system.dict.controller;


import com.hb0730.boot.admin.domain.controller.AbstractBaseController;
import com.hb0730.boot.admin.project.system.dict.model.dto.DictDTO;
import com.hb0730.boot.admin.project.system.dict.model.entity.DictEntity;
import com.hb0730.boot.admin.project.system.dict.model.query.DictParams;
import com.hb0730.boot.admin.project.system.dict.service.IDictService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据字典  前端控制器
 *
 * @author bing_huang
 * @since 3.0.0
 */
@RestController
@RequestMapping("/api/v3/system/dict")
public class DictController extends AbstractBaseController<Long, DictDTO, DictParams, DictEntity> {
    private final IDictService service;

    public DictController(IDictService service) {
        super(service);
        this.service = service;
    }
}

