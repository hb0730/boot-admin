package com.hb0730.boot.admin.project.system.org.controller;


import com.hb0730.boot.admin.commons.annotation.Log;
import com.hb0730.boot.admin.commons.constant.ModuleName;
import com.hb0730.boot.admin.commons.constant.RequestMappingNameConstants;
import com.hb0730.boot.admin.commons.constant.enums.BusinessTypeEnum;
import com.hb0730.boot.admin.commons.utils.bean.BeanUtils;
import com.hb0730.boot.admin.commons.web.controller.BaseController;
import com.hb0730.boot.admin.commons.web.response.ResponseResult;
import com.hb0730.boot.admin.commons.web.response.Result;
import com.hb0730.boot.admin.project.system.org.model.entity.SystemOrgEntity;
import com.hb0730.boot.admin.project.system.org.model.vo.OrgParams;
import com.hb0730.boot.admin.project.system.org.model.vo.SystemOrgVO;
import com.hb0730.boot.admin.project.system.org.model.vo.TreeOrgVO;
import com.hb0730.boot.admin.project.system.org.service.ISystemOrgService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class SystemOrgController extends BaseController<OrgParams, SystemOrgVO, Long, SystemOrgEntity> {
    private final ISystemOrgService systemOrgService;

    public SystemOrgController(ISystemOrgService systemOrgService) {
        super(systemOrgService);
        this.systemOrgService = systemOrgService;
    }

    /**
     * <p>
     * 保存组织信息
     * </p>
     *
     * @param org 组织参数信息
     * @return 是否成功
     */
    @Override
//    @PostMapping("/save")
    @Log(paramsName = "org", module = ModuleName.ORG, title = "保存组织", businessType = BusinessTypeEnum.INSERT)
    @PreAuthorize("hasAnyAuthority('org:save','ROLE_ADMINISTRATOR','ROLE_ADMIN_ORG')")
    public Result save(SystemOrgVO org) {
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
    @Override
//    @PostMapping("/update/{id}")
    @Log(paramsName = "vo", module = ModuleName.ORG, title = "修改组织", businessType = BusinessTypeEnum.UPDATE)
    @PreAuthorize("hasAnyAuthority('org:update','ROLE_ADMINISTRATOR','ROLE_ADMIN_ORG')")
    public Result updateById(Long id, SystemOrgVO vo) {
        vo.setId(id);
        SystemOrgEntity entity = BeanUtils.transformFrom(vo, SystemOrgEntity.class);
        systemOrgService.updateById(entity);
        return ResponseResult.resultSuccess("修改成功");
    }

    /**
     * 删除
     *
     * @param id 组织id
     * @return 是否成功
     */
    @Override
//    @GetMapping("/delete/{id}")
    @Log(module = ModuleName.ORG, title = "删除组织", businessType = BusinessTypeEnum.DELETE)
    @PreAuthorize("hasAnyAuthority('org:delete','ROLE_ADMINISTRATOR','ROLE_ADMIN_ORG')")
    public Result deleteById(Long id) {
        systemOrgService.removeById(id);
        return ResponseResult.resultSuccess("修改成功");
    }
}

