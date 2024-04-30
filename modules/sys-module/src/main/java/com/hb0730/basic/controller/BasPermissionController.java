package com.hb0730.basic.controller;

import com.hb0730.base.R;
import com.hb0730.base.utils.TreeUtil;
import com.hb0730.basic.domain.dto.BasPermissionDto;
import com.hb0730.basic.service.BasPermissionService;
import com.hb0730.security.utils.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/29
 */
@RestController
@RequestMapping("/bas/permission")
@Tag(name = "履约端：权限管理")
@RequiredArgsConstructor
public class BasPermissionController {
    private final BasPermissionService basPermissionService;

    /**
     * 菜单权限树
     *
     * @return 权限列表
     */
    @GetMapping("/tree")
    @Operation(summary = "菜单权限树")
    public R<List<BasPermissionDto>> tree() {
        String sysCode = SecurityUtil.getSysCode();
        List<BasPermissionDto> res = basPermissionService.list(sysCode);
        res = TreeUtil.buildTree(res);
        return R.OK(res);
    }
}
