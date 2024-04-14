package com.hb0730.modules.basic.controller;

import com.hb0730.base.R;
import com.hb0730.common.api.JR;
import com.hb0730.common.api.JsfPage;
import com.hb0730.rpc.basic.domain.BasRoleDto;
import com.hb0730.rpc.basic.domain.query.BasRoleQuery;
import com.hb0730.rpc.basic.service.BasRoleRpcService;
import com.hb0730.security.util.SecurityUtil;
import com.hb0730.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
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
 * @date 2024/3/31
 */
@RestController
@RequestMapping("/bas/role")
@Tag(name = "履约端：角色管理")
@RequiredArgsConstructor
@Slf4j
public class BasRoleController {
    private final BasRoleRpcService basRoleRpcService;

    /**
     * 分页查询
     *
     * @param query .
     * @return .
     */
    @GetMapping
    @Operation(summary = "分页查询")
    @PreAuthorize("hasAuthority('bas:role:query')")
    public R<JsfPage<BasRoleDto>> page(BasRoleQuery query) {
        String sysCode = SecurityUtil.getSysCode();
        query.setSysCode(sysCode);
        JR<JsfPage<BasRoleDto>> jr = basRoleRpcService.page(query);
        return ResponseUtil.converter(jr);
    }

    /**
     * 查询
     *
     * @param query .
     * @return .
     */
    @GetMapping("/list")
    @Operation(summary = "查询")
    public R<List<BasRoleDto>> list(BasRoleQuery query) {
        String sysCode = SecurityUtil.getSysCode();
        query.setSysCode(sysCode);
        JR<List<BasRoleDto>> jr = basRoleRpcService.list(query);
        return ResponseUtil.converter(jr);
    }

    /**
     * 保存
     *
     * @param dto .
     * @return .
     */
    @PostMapping
    @Operation(summary = "保存")
    @PreAuthorize("hasAuthority('bas:role:save')")
    public R<String> save(@Valid @RequestBody BasRoleDto dto) {
        String username = SecurityUtil.getUsername();
        String sysCode = SecurityUtil.getSysCode();
        dto.setCreatedBy(username);
        dto.setCreated(new Date());
        dto.setSysCode(sysCode);
        JR<String> jr = basRoleRpcService.save(dto);
        return ResponseUtil.converter(jr);
    }

    /**
     * 修改
     *
     * @param dto .
     * @return .
     */
    @PutMapping
    @Operation(summary = "修改")
    @PreAuthorize("hasAuthority('bas:role:update')")
    public R<String> update(@Valid @RequestBody BasRoleDto dto) {
        if (dto.getId() == null) {
            return R.NG("id不能为空");
        }
        String username = SecurityUtil.getUsername();
        String sysCode = SecurityUtil.getSysCode();
        dto.setSysCode(sysCode);
        dto.setModified(new Date());
        dto.setModifiedBy(username);
        JR<String> jr = basRoleRpcService.updateById(dto);
        return ResponseUtil.converter(jr);
    }

    /**
     * 删除
     *
     * @param id .
     * @return .
     */
    @DeleteMapping
    @Operation(summary = "删除")
    @PreAuthorize("hasAuthority('bas:role:delete')")
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "id", required = true)
    })
    public R<String> delete(@RequestParam String id) {
        JR<String> jr = basRoleRpcService.delete(id);
        return ResponseUtil.converter(jr);
    }

    /**
     * code是否存在
     *
     * @param code 角色标识
     * @param id   需要排除的id
     * @return .
     */
    @GetMapping("/existsByCode")
    @Operation(summary = "code是否存在")
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "code", description = "角色标识", required = true),
            @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "需要排除的id", required = false)
    })
    public R<Boolean> existsByCode(@RequestParam String code, @RequestParam(required = false) String id) {
        String sysCode = SecurityUtil.getSysCode();
        JR<Boolean> jr = basRoleRpcService.existsByCode(code, sysCode, id);
        return ResponseUtil.converter(jr);
    }

    /**
     * 授权
     *
     * @param id            角色id
     * @param permissionIds 权限id
     * @return .
     */
    @PutMapping("/grant")
    @Operation(summary = "授权")
    @PreAuthorize("hasAuthority('bas:role:grant')")
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "roleId", description = "角色id", required = true),
    })
    public R<String> grant(@RequestParam String id, @RequestBody List<Long> permissionIds) {
        JR<String> jr = basRoleRpcService.grant(id, permissionIds);
        return ResponseUtil.converter(jr);
    }
}
