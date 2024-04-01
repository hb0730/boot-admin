package com.hb0730.modules.basic.controller;

import com.hb0730.base.R;
import com.hb0730.base.utils.TreeUtil;
import com.hb0730.commons.JR;
import com.hb0730.domain.RouteVO;
import com.hb0730.rpc.basic.domain.BasPermissionDto;
import com.hb0730.rpc.basic.domain.query.BasPermissionQuery;
import com.hb0730.rpc.basic.service.BasPermissionRpcService;
import com.hb0730.rpc.sys.system.domain.PermissionDto;
import com.hb0730.security.util.SecurityUtil;
import com.hb0730.utils.PermissionUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
@RestController
@RequestMapping("/bas/permission")
@Tag(name = "履约端：权限管理")
@RequiredArgsConstructor
public class BasPermissionController {
    private final BasPermissionRpcService basPermissionRpcService;

    /**
     * 当前用户路由
     *
     * @return .
     */
    @GetMapping("/current/routes")
    @Operation(summary = "当前用户路由")
    public R<List<RouteVO>> currentRoutes() {
        JR<List<PermissionDto>> jr = basPermissionRpcService.findByUserId(SecurityUtil.getUserId(), SecurityUtil.getSysCode());
        if (!jr.isSuccess()) {
            return R.NG(jr.getMessage());
        }
        List<PermissionDto> listData = jr.getResult();
        List<RouteVO> res = PermissionUtil.buildRoutes(listData);
        return R.OK(res);
    }

    /**
     * 菜单权限树
     *
     * @param query 查询条件
     * @return 权限列表
     */
    @GetMapping("/tree")
    @Operation(summary = "菜单权限树")
    public R<List<BasPermissionDto>> tree(BasPermissionQuery query) {
        JR<List<BasPermissionDto>> jr = basPermissionRpcService.list(query);
        if (!jr.isSuccess()) {
            return R.NG(jr.getMessage());
        }
        return R.OK(TreeUtil.buildTree(jr.getResult()));
    }
}
