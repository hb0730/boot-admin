package com.hb0730.sys.base.util;

import com.hb0730.base.utils.CollectionUtil;
import com.hb0730.base.utils.TreeUtil;
import com.hb0730.sys.base.model.dto.PermissionDTO;
import com.hb0730.sys.base.model.vo.RouteMetaVO;
import com.hb0730.sys.base.model.vo.RouteVO;
import jakarta.annotation.Nonnull;

import java.util.ArrayList;
import java.util.List;

/**
 * 路由工具
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/3
 */
public class RouteUtil {
    /**
     * 构建路由
     *
     * @param list .
     * @return .
     */
    public static List<RouteVO> buildRoutes(List<PermissionDTO> list) {
        List<PermissionDTO> tree = buildTree(list);
        if (CollectionUtil.isEmpty(tree)) {
            return null;
        }
        return _buildRoutes(tree);
    }


    /**
     * 构建树
     *
     * @param dataList 数据
     * @return 树
     */
    private static List<PermissionDTO> buildTree(List<PermissionDTO> dataList) {
        if (CollectionUtil.isEmpty(dataList)) {
            return null;
        }
        return TreeUtil.build(dataList);
    }


    /**
     * 构建路由
     *
     * @param dtoList .
     * @return .x
     */
    private static List<RouteVO> _buildRoutes(@Nonnull List<PermissionDTO> dtoList) {
        List<RouteVO> routes = new ArrayList<>();
        dtoList.forEach(
                item -> {
                    RouteVO route = new RouteVO();
                    // 路由路径
                    route.setPath(item.getRoutePath());
                    // 路由名称
                    route.setName(item.getRouteName());
                    // 路由组件
                    route.setComponent(item.getComponent());
                    // 路由重定向
                    route.setRedirect(item.getRedirect());
                    // 路由元信息
                    route.setMeta(buildMeta(item));
                    // 子路由
                    List<PermissionDTO> children = item.getChildren();
                    if (CollectionUtil.isNotEmpty(children)) {
                        route.setChildren(_buildRoutes(children));
                    }
                    routes.add(route);
                }
        );
        return routes;
    }

    /**
     * 构建meta
     *
     * @param dto .
     * @return .
     */
    private static RouteMetaVO buildMeta(PermissionDTO dto) {
        RouteMetaVO meta = new RouteMetaVO();
        // 菜单标题
        meta.setTitle(dto.getTitle());
        // 菜单图标
        meta.setIcon(dto.getIcon());
        // 是否隐藏
        meta.setIsHidden(dto.getIsHidden());
        // 是否缓存
        meta.setIsKeepAlive(dto.getIsKeepAlive());
        // 是否固定在标签视图
        meta.setIsAffix(dto.getIsAffix());
        // 是否大屏
        meta.setIsFullScreen(dto.getIsFullScreen());
        // iframe
        meta.setFrameSrc(dto.getFrameSrc());
        // 是否需要登陆
        meta.setRequireLogin(dto.getIsRequireLogin());

        return meta;
    }
}
