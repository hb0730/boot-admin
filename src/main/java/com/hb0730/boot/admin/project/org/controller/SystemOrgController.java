package com.hb0730.boot.admin.project.org.controller;


import com.hb0730.boot.admin.commons.utils.bean.BeanUtils;
import com.hb0730.boot.admin.commons.constant.RequestMappingNameConstants;
import com.hb0730.boot.admin.commons.web.controller.BaseController;
import com.hb0730.boot.admin.commons.web.response.ResponseResult;
import com.hb0730.boot.admin.commons.web.response.Result;
import com.hb0730.boot.admin.project.org.model.entity.SystemOrgEntity;
import com.hb0730.boot.admin.project.org.model.vo.SystemOrgVO;
import com.hb0730.boot.admin.project.org.model.vo.TreeOrgVO;
import com.hb0730.boot.admin.project.org.service.ISystemOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 系统组织  前端控制器
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-26
 */
@RestController
@RequestMapping(RequestMappingNameConstants.REQUEST_ORG)
public class SystemOrgController extends BaseController {
    @Autowired
    private ISystemOrgService systemOrgService;

    /**
     * <p>
     * 保存组织信息
     * </p>
     *
     * @param org 组织参数信息
     * @return 是否成功
     */
    @PostMapping("/save")
    public Result save(@Validated @RequestBody SystemOrgVO org) {
        SystemOrgEntity entity = BeanUtils.transformFrom(org, SystemOrgEntity.class);
        systemOrgService.save(entity);
        return ResponseResult.resultSuccess("保存成功");
    }

    /**
     * 获取组织树(包含已停用)
     *
     * @return 组织树
     */
    @GetMapping("/tree/{isAll}")
    public Result getTreeAll(@PathVariable Integer isAll) {
        List<TreeOrgVO> treeAll = systemOrgService.getTreeAll(isAll);
        return ResponseResult.resultSuccess(treeAll);
    }

    /**
     * 修改
     *
     * @param id 组织id
     * @param vo 组织信息
     * @return 是否成功
     */
    @PostMapping("/update/{id}")
    public Result updateById(@PathVariable Long id, @Validated @RequestBody SystemOrgVO vo) {
        vo.setId(id);
        SystemOrgEntity entity = BeanUtils.transformFrom(vo, SystemOrgEntity.class);
        systemOrgService.updateById(entity);
        return ResponseResult.resultSuccess("修改成功");
    }

    /**
     * 删除
     * @param id 组织id
     * @return 是否成功
     */
    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable Long id){
        systemOrgService.removeById(id);
        return  ResponseResult.resultSuccess("修改成功");
    }
}

