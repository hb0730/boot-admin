package com.hb0730.sys.system.controller;

import com.hb0730.base.R;
import com.hb0730.biz.dto.sys.system.PermissionDto;
import com.hb0730.biz.dto.sys.system.PermissionSaveDto;
import com.hb0730.commons.JR;
import com.hb0730.security.utils.SecurityUtil;
import com.hb0730.sys.bean.PermissionQuery;
import com.hb0730.sys.service.PermissionRpcService;
import com.hb0730.sys.system.model.vo.MenuTreeVO;
import com.hb0730.sys.system.model.vo.RouteVO;
import com.hb0730.utils.PermissionUtil;
import com.hb0730.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
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
 * 菜单与权限 controller
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/27
 */
@RestController
@RequestMapping("/sys/permission")
@Tag(name = "系统: 菜单与权限")
@RequiredArgsConstructor
public class PermissionController {
    private final PermissionRpcService permissionRpcService;

    /**
     * 当前用户路由
     *
     * @return .
     */
    @GetMapping("/current/routes")
    @Operation(summary = "当前用户路由")
    public R<List<RouteVO>> currentRoutes() {
        JR<List<PermissionDto>> jr = permissionRpcService.findByUserId(SecurityUtil.getUserId());
        if (!jr.isSuccess()) {
            return R.NG(jr.getMessage());
        }
        List<PermissionDto> listData = jr.getResult();
        List<RouteVO> res = PermissionUtil.buildRoutes(listData);
        return R.OK(res);
    }

    /**
     * 查询
     *
     * @param query .
     * @return .
     */
    @GetMapping
    @Operation(summary = "查询", description = "默认查询root节点，rank排序")
    @PreAuthorize("hasAnyAuthority('sys:permission:list','ROLE_ADMIN')")
    public R<List<PermissionDto>> list(PermissionQuery query) {
        JR<List<PermissionDto>> jr = permissionRpcService.listDefaultRootQueryOrderRank(query);
        if (!jr.isSuccess()) {
            return R.NG(jr.getMessage());
        }
        return R.OK(jr.getResult());
    }

    /**
     * 菜单树
     *
     * @return .
     */
    @GetMapping("/menu/tree")
    @Operation(summary = "菜单树")
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "filterButton", description = "是否过滤按钮", required = false)
    })
    public R<List<MenuTreeVO>> menuTree(@RequestParam(required = false, defaultValue = "false", name = "filterButton") Boolean filterButton) {
        JR<List<PermissionDto>> jr = permissionRpcService.findAllEnabled();
        if (!jr.isSuccess()) {
            return R.NG(jr.getMessage());
        }
        List<PermissionDto> listData = jr.getResult();
        List<MenuTreeVO> res = PermissionUtil.buildMenuTree(listData, filterButton);
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
    @PreAuthorize("hasAnyAuthority('sys:permission:add','ROLE_ADMIN')")
    public R<String> add(@Validated @RequestBody PermissionSaveDto dto) {
        dto.setId(null);
        String username = SecurityUtil.getUsername();
        dto.setCreatedBy(username);
        dto.setCreated(new java.util.Date());
        JR<String> res = permissionRpcService.add(dto);
        return ResponseUtil.converter(res);
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
        Integer id = dto.getId();
        if (null == id) {
            return R.NG("id不能为空");
        }
        String username = SecurityUtil.getUsername();
        dto.setModifyBy(username);
        dto.setModified(new java.util.Date());

        JR<String> res = permissionRpcService.updateById(dto);
        return ResponseUtil.converter(res);
    }

    /**
     * 删除
     *
     * @param id .
     * @return .
     */
    @DeleteMapping
    @Operation(summary = "删除")
    @PreAuthorize("hasAnyAuthority('sys:permission:delete','ROLE_ADMIN')")
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "id", required = true)
    })
    public R<String> delete(@RequestParam(name = "id") String id) {
        JR<String> res = permissionRpcService.delete(Integer.parseInt(id));
        return ResponseUtil.converter(res);
    }

}
