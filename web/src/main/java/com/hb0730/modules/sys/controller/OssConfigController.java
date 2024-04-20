package com.hb0730.modules.sys.controller;

import com.hb0730.base.R;
import com.hb0730.common.api.JR;
import com.hb0730.modules.sys.rpcservice.OssConfigRemoteRpcService;
import com.hb0730.rpc.sys.system.domain.OssConfigDto;
import com.hb0730.security.util.SecurityUtil;
import com.hb0730.utils.ResponseUtil;
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

import java.util.Date;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/20
 */
@RestController
@RequestMapping("/sys/oss/config")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "管理端：商户oss配置")
public class OssConfigController {
    private final OssConfigRemoteRpcService ossConfigRemoteRpcService;

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
        JR<OssConfigDto> res = ossConfigRemoteRpcService.getOssConfig(sysCode);
        return ResponseUtil.converter(res);
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
        String username = SecurityUtil.getUsername();
        dto.setCreated(new Date());
        dto.setCreatedBy(username);
        dto.setModified(new Date());
        dto.setModifiedBy(username);
        JR<String> res = ossConfigRemoteRpcService.saveOssConfig(dto);
        return ResponseUtil.converter(res);
    }
}
