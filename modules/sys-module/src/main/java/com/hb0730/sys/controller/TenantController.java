package com.hb0730.sys.controller;

import com.hb0730.base.R;
import com.hb0730.base.utils.StrUtil;
import com.hb0730.common.api.JsfPage;
import com.hb0730.sys.domain.dto.TenantBasicConfigDto;
import com.hb0730.sys.domain.dto.TenantDto;
import com.hb0730.sys.domain.dto.TenantSmallDto;
import com.hb0730.sys.domain.query.TenantQuery;
import com.hb0730.sys.service.SysTenantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/29
 */
@RestController
@RequestMapping("/sys/tenant/org")
@RequiredArgsConstructor
@Tag(name = "管理端：商户管理")
public class TenantController {
    private final SysTenantService tenantService;


    /**
     * 商户识别码是否存在
     *
     * @param sysCode .
     * @param id      需要排除的ID
     * @return .
     */
    @GetMapping("/existsBySysCode")
    @Operation(summary = "商户识别码是否存在")
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "sysCode", description = "商户识别码", required = true),
            @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "需要排除的ID", required = false)
    })
    public R<Boolean> existsBySysCode(String sysCode, @RequestParam(name = "id", required = false) String id) {
        Boolean res = tenantService.existsCode(sysCode, id);
        return R.OK(res);
    }

    /**
     * 租户机构分页
     *
     * @param query 查询条件
     * @return 机构分页
     */
    @GetMapping()
    @Operation(summary = "租户机构分页")
    @PreAuthorize("hasAnyAuthority('tenant:org:query')")
    public R<JsfPage<TenantDto>> tenantPage(TenantQuery query) {
        JsfPage<TenantDto> res = tenantService.page(query);
        return R.OK(res);
    }

    /**
     * 租户机构列表
     *
     * @param query 查询条件
     * @return 机构列表
     */
    @GetMapping("/list")
    @Operation(summary = "租户机构列表")
    public R<List<TenantDto>> list(TenantQuery query) {
        List<TenantDto> res = tenantService.list(query);
        return R.OK(res);
    }

    /**
     * 保存租户
     *
     * @param dto 租户机构
     * @return 保存结果
     */
    @PostMapping()
    @Operation(summary = "保存租户")
    @PreAuthorize("hasAnyAuthority('tenant:org:add')")
    public R<String> save(@Valid @RequestBody TenantSmallDto dto) {
        tenantService.save(dto);
        return R.OK("保存成功");
    }

    /**
     * 更新租户
     *
     * @param dto 租户机构
     * @return 更新结果
     */
    @PutMapping()
    @Operation(summary = "更新租户机构")
    @PreAuthorize("hasAnyAuthority('tenant:org:update')")
    public R<String> updateTenantOrganization(@RequestBody @Valid TenantSmallDto dto) {
        if (StrUtil.isBlank(dto.getId())) {
            return R.NG("id不能为空");
        }
        tenantService.updateById(dto);
        return R.OK("更新成功");
    }

    /**
     * 重置密码
     *
     * @param id .
     * @return .
     */
    @PutMapping("/resetPassword")
    @Operation(summary = "重置密码")
    @PreAuthorize("hasAnyAuthority('tenant:org:update')")
    @Parameters({@io.swagger.v3.oas.annotations.Parameter(name = "id", description = "租户ID", required = true)})
    public R<String> resetPassword(@RequestParam String id) {
        tenantService.resetPassword(id);
        return R.OK("重置成功");
    }

    /**
     * 获取租户机构基础配置
     *
     * @param id .
     * @return .
     */
    @GetMapping("/basic/config")
    @Operation(summary = "获取租户机构基础配置")
    @Parameters({@io.swagger.v3.oas.annotations.Parameter(name = "id", description = "租户ID", required = true)})
    public R<TenantBasicConfigDto> getTenantOrgConfig(@RequestParam String id) {
        TenantBasicConfigDto res = tenantService.getTenantBasicConfig(id);
        return R.OK(res);
    }

    /**
     * 更新租户机构基础配置
     *
     * @param dto .
     * @return .
     */
    @PutMapping("/basic/config")
    @Operation(summary = "更新租户机构基础配置")
    @PreAuthorize("hasAnyAuthority('tenant:org:update')")
    public R<String> updateTenantOrgConfig(@Valid @RequestBody TenantBasicConfigDto dto) {
        tenantService.updateTenantOrgBasicConfig(dto);
        return R.OK("更新成功");
    }

}
