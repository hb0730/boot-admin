package com.hb0730.modules.sys.controller;

import com.hb0730.base.R;
import com.hb0730.commons.JR;
import com.hb0730.commons.JsfPage;
import com.hb0730.rpc.sys.tenant.domain.TenantOrgDto;
import com.hb0730.rpc.sys.tenant.domain.TenantSmallDto;
import com.hb0730.rpc.sys.tenant.domain.UpdateTenantOrgConfigDto;
import com.hb0730.rpc.sys.tenant.domain.query.TenantQuery;
import com.hb0730.rpc.sys.tenant.service.TenantOrgRpcService;
import com.hb0730.security.util.SecurityUtil;
import com.hb0730.utils.ResponseUtil;
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

import java.util.Date;
import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/29
 */
@RestController
@RequestMapping("/tenant/org")
@RequiredArgsConstructor
@Tag(name = "管理端：商户管理")
public class TenantOrgController {
    private final TenantOrgRpcService organizationRpcService;

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
        JR<Boolean> jr = organizationRpcService.existsByCode(sysCode, id);
        return ResponseUtil.converter(jr);
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
    public R<JsfPage<TenantOrgDto>> tenantPage(TenantQuery query) {
        JR<JsfPage<TenantOrgDto>> jr = organizationRpcService.queryTenantOrganization(query);
        return ResponseUtil.converter(jr);
    }

    /**
     * 租户机构列表
     *
     * @param query 查询条件
     * @return 机构列表
     */
    @GetMapping("/list")
    @Operation(summary = "租户机构列表")
    public R<List<TenantOrgDto>> list(TenantQuery query) {
        JR<List<TenantOrgDto>> jr = organizationRpcService.list(query);
        return ResponseUtil.converter(jr);
    }

    /**
     * 保存租户机构
     *
     * @param dto 机构信息
     * @return 保存结果
     */
    @PostMapping()
    @Operation(summary = "保存租户机构")
    @PreAuthorize("hasAnyAuthority('tenant:org:add')")
    public R<String> saveTenantOrganization(@Valid @RequestBody TenantSmallDto dto) {
        String username = SecurityUtil.getUsername();
        dto.setCreatedBy(username);
        dto.setCreated(new java.util.Date());
        JR<String> jr = organizationRpcService.saveTenantOrganization(dto);
        return ResponseUtil.converter(jr);
    }

    /**
     * 更新租户机构
     *
     * @param dto 机构信息
     * @return 更新结果
     */
    @PutMapping()
    @Operation(summary = "更新租户机构")
    @PreAuthorize("hasAnyAuthority('tenant:org:update')")
    public R<String> updateTenantOrganization(@RequestBody @Valid TenantSmallDto dto) {
        if (null == dto.getId()) {
            return R.NG("id不能为空");
        }
        String username = SecurityUtil.getUsername();
        dto.setModifiedBy(username);
        dto.setModified(new Date());
        JR<String> jr = organizationRpcService.updateTenantOrganization(dto);
        return ResponseUtil.converter(jr);
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
        String username = SecurityUtil.getUsername();
        JR<String> jr = organizationRpcService.resetPassword(id, username);
        return ResponseUtil.converter(jr);
    }

    /**
     * 更新租户机构配置
     *
     * @param dto .
     * @return .
     */
    @PutMapping("/config")
    @Operation(summary = "更新租户机构配置")
    @PreAuthorize("hasAnyAuthority('tenant:org:update')")
    public R<String> updateTenantOrgConfig(@Valid @RequestBody UpdateTenantOrgConfigDto dto) {
        String username = SecurityUtil.getUsername();
        dto.setModifiedBy(username);
        dto.setModified(new Date());
        JR<String> jr = organizationRpcService.updateTenantOrgConfig(dto);
        return ResponseUtil.converter(jr);
    }
}
