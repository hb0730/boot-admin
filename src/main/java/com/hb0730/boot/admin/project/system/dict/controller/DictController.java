package com.hb0730.boot.admin.project.system.dict.controller;


import com.hb0730.boot.admin.annotation.ClassDescribe;
import com.hb0730.boot.admin.annotation.PreAuth;
import com.hb0730.boot.admin.domain.controller.SuperSimpleBaseController;
import com.hb0730.boot.admin.domain.result.Result;
import com.hb0730.boot.admin.domain.result.R;
import com.hb0730.boot.admin.project.system.dict.model.dto.DictDTO;
import com.hb0730.boot.admin.project.system.dict.model.entity.DictEntity;
import com.hb0730.boot.admin.project.system.dict.model.query.DictParams;
import com.hb0730.boot.admin.project.system.dict.model.vo.DictVO;
import com.hb0730.boot.admin.project.system.dict.service.IDictService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 数据字典  前端控制器
 *
 * @author bing_huang
 * @since 3.0.0
 */
@RestController
@RequestMapping("/api/v3/system/dict")
@ClassDescribe("数据字典")
@PreAuth("dict")
public class DictController extends SuperSimpleBaseController<Long, DictDTO, DictParams, DictEntity> {
    private final IDictService service;

    public DictController(IDictService service) {
        super(service);
        this.service = service;
    }


    /**
     * 获取字典缓存
     *
     * @return 字典值
     */
    @GetMapping("/cache/get")
    public Result<List<DictVO>> getCache() {
        List<DictVO> cache = service.getCache();
        return R.success(cache);
    }

    /**
     * 更新缓存
     *
     * @return 是否成功
     */
    @GetMapping("/cache/update")
    public Result<String> updateCache() {
        service.updateCache();
        return R.success("更新成功");
    }
}

