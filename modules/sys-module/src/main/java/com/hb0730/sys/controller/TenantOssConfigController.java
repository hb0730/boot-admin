package com.hb0730.sys.controller;

import com.hb0730.base.R;
import com.hb0730.sys.domain.dto.OssConfigDto;
import com.hb0730.sys.service.SysTenantOssConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/30
 */
@RestController
@RequestMapping("/sys/tenant/oss/config")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "管理端：商户oss配置")
public class TenantOssConfigController {
    private final SysTenantOssConfigService service;

    /**
     * 获取oss配置
     *
     * @param sysCode 系统编码
     * @return oss配置
     */
    @GetMapping
    @Operation(summary = "获取oss配置")
    @PreAuthorize("hasAnyAuthority('tenant:org:update')")
    public R<OssConfigDto> getOssConfig(String sysCode) {
        OssConfigDto res = service.findBySysCode(sysCode);
        return R.OK(res);
    }

    /**
     * 更新保存oss配置
     *
     * @param dto oss配置
     * @return 是否成功
     */
    @PutMapping
    @Operation(summary = "更新保存oss配置")
    @PreAuthorize("hasAnyAuthority('tenant:org:update')")
    public R<String> save(@Validated @RequestBody OssConfigDto dto) {
        service.save(dto);
        return R.OK();
    }
}
