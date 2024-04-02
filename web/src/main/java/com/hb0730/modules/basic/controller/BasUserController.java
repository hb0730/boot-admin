package com.hb0730.modules.basic.controller;

import com.hb0730.base.R;
import com.hb0730.base.utils.StrUtil;
import com.hb0730.commons.JR;
import com.hb0730.commons.JsfPage;
import com.hb0730.rpc.basic.domain.BasUserDto;
import com.hb0730.rpc.basic.domain.BasUserRestPasswordDto;
import com.hb0730.rpc.basic.domain.BasUserSaveDto;
import com.hb0730.rpc.basic.domain.query.BasUserQuery;
import com.hb0730.rpc.basic.service.BasUserRpcService;
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

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/1
 */
@RestController
@RequestMapping("/bas/user")
@Tag(name = "履约端：用户管理")
@RequiredArgsConstructor
public class BasUserController {
    private final BasUserRpcService basUserRpcService;


    /**
     * 账号是否存在
     *
     * @param username 用户名
     * @param id       需要排除的id
     * @return .
     */
    @GetMapping("/existsByUsername")
    @Operation(summary = "账号是否存在")
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "username", description = "用户名", required = true),
            @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "需要排除的id", required = false)
    })
    public R<Boolean> existsByUsername(String username, @RequestParam(required = false) String id) {
        String sysCode = SecurityUtil.getSysCode();
        JR<Boolean> jr = basUserRpcService.existsByUsername(username, sysCode, id);
        return ResponseUtil.converter(jr);
    }

    @GetMapping
    @Operation(summary = "分页查询")
    @PreAuthorize("hasAnyAuthority('bas:user:query')")
    public R<JsfPage<BasUserDto>> page(BasUserQuery query) {
//        String sysCode = SecurityUtil.getSysCode();
//        query.setSysCode(sysCode);
        JR<JsfPage<BasUserDto>> jr = basUserRpcService.page(query);
        return ResponseUtil.converter(jr);
    }


    @PostMapping
    @Operation(summary = "保存")
    @PreAuthorize("hasAnyAuthority('bas:user:save')")
    public R<String> save(@Valid @RequestBody BasUserSaveDto dto) {
        if (StrUtil.isBlank(dto.getPassword())) {
            return R.NG("密码不能为空");
        }
//        String sysCode = SecurityUtil.getSysCode();
        String username = SecurityUtil.getUsername();
        dto.setCreatedBy(username);
        dto.setCreated(new Date());
//        dto.setSysCode(sysCode);
        JR<String> jr = basUserRpcService.save(dto);
        return ResponseUtil.converter(jr);
    }

    @PutMapping
    @Operation(summary = "更新")
    @PreAuthorize("hasAnyAuthority('bas:user:update')")
    public R<String> update(@Valid @RequestBody BasUserSaveDto dto) {
        String id = dto.getId();
        if (id == null) {
            return R.NG("id不能为空");
        }
        String username = SecurityUtil.getUsername();
        dto.setModifiedBy(username);
        dto.setModified(new Date());
//        dto.setSysCode(SecurityUtil.getSysCode());
        JR<String> jr = basUserRpcService.updateById(dto);
        return ResponseUtil.converter(jr);
    }

    /**
     * 重置密码
     *
     * @param dto .
     * @return .
     */
    @PutMapping("/restPassword")
    @Operation(summary = "重置密码")
    @PreAuthorize("hasAnyAuthority('bas:user:restPassword')")
    public R<String> restPassword(@Valid @RequestBody BasUserRestPasswordDto dto) {
        String username = SecurityUtil.getUsername();
        dto.setOperator(username);
        JR<String> jr = basUserRpcService.restPassword(dto);
        return ResponseUtil.converter(jr);
    }
}
