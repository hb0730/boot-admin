package com.hb0730.sys.system.controller;

import com.hb0730.base.R;
import com.hb0730.biz.dto.sys.system.RoleDto;
import com.hb0730.commons.JsfPage;
import com.hb0730.security.utils.SecurityUtil;
import com.hb0730.sys.bean.RoleQuery;
import com.hb0730.sys.service.RoleRpcService;
import com.hb0730.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
 * @date 2024/2/28
 */
@RestController
@RequestMapping("/sys/role")
@Tag(name = "系统: 角色管理")
@RequiredArgsConstructor
public class RoleController {
    private final RoleRpcService roleRpcService;

    /**
     * 查询
     *
     * @param query 查询条件
     * @return 角色列表
     */
    @GetMapping("/list")
    @Operation(summary = "列表查询")
    public R<List<RoleDto>> list(RoleQuery query) {
        return ResponseUtil.converter(roleRpcService.list(query));
    }


    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return 角色列表
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询")
    @PreAuthorize("hasAnyAuthority('sys:role:list','ROLE_ADMIN')")
    public R<JsfPage<RoleDto>> page(RoleQuery query) {
        return ResponseUtil.converter(roleRpcService.page(query));
    }


    /**
     * 编码是否存在
     *
     * @param code 编码
     * @param id   角色ID
     * @return .
     */
    @GetMapping("/existsByCode")
    @Operation(summary = "编码是否存在")
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "code", description = "编码", required = true),
            @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "需要排除的编码", required = false)
    })
    public R<Boolean> existsByCode(@RequestParam(name = "code") String code, @RequestParam(name = "id", required = false) Integer id) {
        return ResponseUtil.converter(roleRpcService.existsByCode(code, id));
    }

    /**
     * 添加
     *
     * @param roleDto 角色
     * @return 是否成功
     */
    @PostMapping
    @Operation(summary = "添加")
    @PreAuthorize("hasAnyAuthority('sys:role:add','ROLE_ADMIN')")
    public R<String> add(@RequestBody RoleDto roleDto) {
        String username = SecurityUtil.getUsername();
        roleDto.setCreatedBy(username);
        roleDto.setCreated(new java.util.Date());
        return ResponseUtil.converter(roleRpcService.add(roleDto));
    }

    /**
     * 更新
     *
     * @param roleDto 角色
     * @return 是否成功
     */
    @PutMapping
    @Operation(summary = "更新")
    @PreAuthorize("hasAnyAuthority('sys:role:update','ROLE_ADMIN')")
    public R<String> updateById(@RequestBody RoleDto roleDto) {
        if (roleDto.getId() == null) {
            return R.NG("角色ID不能为空");
        }
        String username = SecurityUtil.getUsername();
        roleDto.setModifyBy(username);
        roleDto.setModified(new java.util.Date());

        return ResponseUtil.converter(roleRpcService.updateById(roleDto));
    }

    /**
     * 删除
     *
     * @param id 角色id
     * @return 是否成功
     */
    @DeleteMapping
    @Operation(summary = "删除")
    @PreAuthorize("hasAnyAuthority('sys:role:delete','ROLE_ADMIN')")
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "角色id", required = true)
    })
    public R<String> deleteById(@RequestParam(name = "id") Integer id) {
        return ResponseUtil.converter(roleRpcService.deleteById(id));
    }


    /**
     * 分配权限
     *
     * @param roleId  角色id
     * @param menuIds 菜单id
     * @return 是否成功
     */
    @PutMapping("/assignPermission")
    @Operation(summary = "分配权限")
    @PreAuthorize("hasAnyAuthority('sys:role:assignPermission','ROLE_ADMIN')")
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "roleId", description = "角色id", required = true),
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "菜单与权限ID", required = true)
    public R<String> assignPermission(@RequestParam(name = "roleId") Integer roleId,
                                      @RequestBody List<Integer> menuIds) {
        return ResponseUtil.converter(roleRpcService.assignPermission(roleId, menuIds));
    }
}
