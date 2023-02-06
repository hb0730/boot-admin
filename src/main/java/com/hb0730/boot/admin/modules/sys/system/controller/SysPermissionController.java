package com.hb0730.boot.admin.modules.sys.system.controller;

import com.hb0730.boot.admin.base.R;
import com.hb0730.boot.admin.modules.sys.system.cache.RouteCache;
import com.hb0730.boot.admin.modules.sys.system.model.vo.VueMenuRouteVO;
import com.hb0730.boot.admin.modules.sys.system.service.SysPermissionService;
import com.hb0730.boot.admin.security.model.UserInfo;
import com.hb0730.boot.admin.security.util.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/2/6
 */

@RestController
@RequestMapping("/sys/permission")
@Tag(name = "系统：菜单与权限")
@RequiredArgsConstructor
public class SysPermissionController {
    private final SysPermissionService sysPermissionService;
    private final RouteCache routeCache;

    /**
     * 获取当前用户路由
     *
     * @param request .
     * @return .
     */
    @GetMapping("/routes")
    @Operation(summary = "获取当前用户路由")
    public R<List<VueMenuRouteVO>> getCurrentRoute(HttpServletRequest request) {
        UserInfo user = SecurityUtil.getCurrentUser();
        Optional<List<VueMenuRouteVO>> routeCacheOptional = routeCache.getCache(user.getUserid());
        if (routeCacheOptional.isPresent()) {
            return R.OK(routeCacheOptional.get());
        }
        List<VueMenuRouteVO> menuRoute = null;
        if (user.isManger()) {
            Set<String> permissionIds = sysPermissionService.allPermissionIds();
            menuRoute = sysPermissionService.queryRouteByIds(List.copyOf(permissionIds));
        } else {
            menuRoute = sysPermissionService.queryRouteByUserid(user.getUserid());
        }
        routeCache.putCache(user.getUserid(), menuRoute);
        return R.OK(menuRoute);
    }
}
