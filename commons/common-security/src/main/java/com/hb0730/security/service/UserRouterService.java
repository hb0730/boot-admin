package com.hb0730.security.service;

import com.hb0730.security.domain.vo.RouteVO;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/28
 */
public interface UserRouterService {

    /**
     * 租户端用户路由
     *
     * @param userId 用户id
     * @return 路由
     */
    List<RouteVO> getRoutes(String userId);

    /**
     * 业务端用户路由
     *
     * @param userId  用户id
     * @param sysCode 系统标识
     * @return 路由
     */
    List<RouteVO> getRoutes(String userId, String sysCode);
}
