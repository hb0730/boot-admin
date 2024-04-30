package com.hb0730.sys.controller;

import com.hb0730.base.R;
import com.hb0730.base.utils.StrUtil;
import com.hb0730.sys.domain.dto.PermissionDto;
import com.hb0730.sys.domain.dto.PermissionSaveDto;
import com.hb0730.sys.domain.query.PermissionQuery;
import com.hb0730.sys.domain.vo.MenuTreeVO;
import com.hb0730.sys.service.SysPermissionService;
import com.hb0730.utils.MenuUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/sys/permission")
@Tag(name = "管理端：菜单与权限")
@RequiredArgsConstructor
public class PermissionController {
    private final SysPermissionService sysPermissionService;

    /**
     * 查询
     *
     * @param query .
     * @return .
     */
    @GetMapping
    @Operation(summary = "查询", description = "默认查询root节点，rank排序")
    @PreAuthorize("hasAnyAuthority('sys:permission:list')")
    public R<List<PermissionDto>> list(PermissionQuery query) {
        List<PermissionDto> res = sysPermissionService.list(query);
        return R.OK(res);
    }

    /**
     * 菜单树
     *
     * @param filterButton .
     * @return .
     */
    @GetMapping("/menu/tree")
    @Operation(summary = "菜单树")
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "filterButton", description = "是否过滤按钮", required = false)
    })
    public R<List<MenuTreeVO>> menuTree(@RequestParam(required = false, defaultValue = "false", name = "filterButton") Boolean filterButton) {
        List<PermissionDto> allEnabled = sysPermissionService.findAllEnabled();
        List<MenuTreeVO> res = MenuUtil.buildMenuTree(allEnabled, filterButton);
        return R.OK(res);
    }

    /**
     * 添加
     *
     * @param dto .
     * @return .
     */
    @PostMapping
    @Operation(summary = "添加")
    @PreAuthorize("hasAnyAuthority('sys:permission:add')")
    public R<String> save(@Valid @RequestBody PermissionSaveDto dto) {
        dto.setId(null);
        // 将空字符串转为null
        if (StrUtil.isNotBlank(dto.getParentId())
                && "0".equals(dto.getParentId())) {
            dto.setParentId(null);
        }
        if ("".equals(dto.getParentId())) {
            dto.setParentId(null);
        }
        sysPermissionService.save(dto);
        return R.OK();
    }

    /**
     * 修改
     *
     * @param dto .
     * @return .
     */
    @PutMapping
    @Operation(summary = "修改")
    @PreAuthorize("hasAnyAuthority('sys:permission:update','ROLE_ADMIN')")
    public R<String> update(@Validated @RequestBody PermissionSaveDto dto) {
        String id = dto.getId();
        if (StrUtil.isBlank(id)) {
            return R.NG("id不能为空");
        }
        if (StrUtil.isNotBlank(dto.getParentId()) && id.equals(dto.getParentId())) {
            return R.NG("上级不能是自己");
        }
        // 将空字符串转为null
        if (StrUtil.isNotBlank(dto.getParentId()) && "0".equals(dto.getParentId())) {
            dto.setParentId(null);
        }
        if ("".equals(dto.getParentId())) {
            dto.setParentId(null);
        }
        sysPermissionService.updateById(dto);
        return R.OK();
    }

    /**
     * 删除
     *
     * @param id .
     * @return .
     */
    @DeleteMapping
    @Operation(summary = "删除")
    @PreAuthorize("hasAnyAuthority('sys:permission:delete')")
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "id", required = true)
    })
    public R<String> delete(@RequestParam(name = "id") String id) {
        sysPermissionService.deleteById(id);
        return R.OK();
    }
}
