package com.hb0730.security.login;

import com.hb0730.basic.service.BasPermissionService;
import com.hb0730.security.domain.vo.RouteVO;
import com.hb0730.sys.domain.dto.PermissionDto;
import com.hb0730.sys.service.SysPermissionService;
import com.hb0730.util.RouteUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/28
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserRouterService implements com.hb0730.security.service.UserRouterService {
    private final SysPermissionService sysPermissionService;
    private final BasPermissionService basPermissionService;

    @Override
    public List<RouteVO> getRoutes(String userId) {
        List<PermissionDto> sysPermissions = sysPermissionService.userRoutes(userId);
        return getRoutes(sysPermissions);
    }

    @Override
    public List<RouteVO> getRoutes(String userId, String sysCode) {
        List<PermissionDto> permissions = basPermissionService.userRoutes(userId);
        return getRoutes(permissions);
    }

    /**
     * 获取路由
     *
     * @param permissions 权限
     * @return 路由
     */
    private List<RouteVO> getRoutes(List<PermissionDto> permissions) {
        if (null == permissions) {
            return List.of();
        }
        return RouteUtil.buildRoutes(permissions);
    }
}
