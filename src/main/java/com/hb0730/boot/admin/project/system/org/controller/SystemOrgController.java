package com.hb0730.boot.admin.project.system.org.controller;


import com.hb0730.boot.admin.annotation.Log;
import com.hb0730.boot.admin.domain.controller.AbstractBaseController;
import com.hb0730.boot.admin.domain.result.ResponseResult;
import com.hb0730.boot.admin.domain.result.Result;
import com.hb0730.boot.admin.model.constants.ModuleName;
import com.hb0730.boot.admin.model.constants.RequestMappingNameConstants;
import com.hb0730.boot.admin.model.enums.BusinessTypeEnum;
import com.hb0730.boot.admin.project.system.org.model.entity.SystemOrgEntity;
import com.hb0730.boot.admin.project.system.org.model.vo.OrgParams;
import com.hb0730.boot.admin.project.system.org.model.vo.SystemOrgVO;
import com.hb0730.boot.admin.project.system.org.model.vo.TreeOrgVO;
import com.hb0730.boot.admin.project.system.org.service.ISystemOrgService;
import org.springframework.security.access.prepost.PreAuthorize;
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
@Validated
public class SystemOrgController extends AbstractBaseController<Long, SystemOrgVO, OrgParams, SystemOrgEntity> {
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
    @Log(paramsName = "org", module = ModuleName.ORG, title = "保存组织", businessType = BusinessTypeEnum.INSERT)
    @PreAuthorize("hasAnyAuthority('org:save','ROLE_ADMINISTRATOR','ROLE_ADMIN_ORG')")
    public Result<String> save(@RequestBody SystemOrgVO org) {
        return super.save(org);
    }

    /**
     * 获取组织树(包含已停用)
     *
     * @return 组织树
     */
    @GetMapping("/tree/{isAll}")
    public Result<List<TreeOrgVO>> getTreeAll(@PathVariable Integer isAll) {
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
    @Log(paramsName = "vo", module = ModuleName.ORG, title = "修改组织", businessType = BusinessTypeEnum.UPDATE)
    @PreAuthorize("hasAnyAuthority('org:update','ROLE_ADMINISTRATOR','ROLE_ADMIN_ORG')")
    public Result<String> updateById(@PathVariable("id") Long id, @RequestBody SystemOrgVO vo) {
        vo.setId(id);
        return super.updateById(id, vo);
    }

    /**
     * 删除
     *
     * @param id 组织id
     * @return 是否成功
     */
    @Override
    @Log(module = ModuleName.ORG, title = "删除组织", businessType = BusinessTypeEnum.DELETE)
    @PreAuthorize("hasAnyAuthority('org:delete','ROLE_ADMINISTRATOR','ROLE_ADMIN_ORG')")
    public Result<String> deleteById(@PathVariable("id") Long id) {
        return super.deleteById(id);
    }
}

