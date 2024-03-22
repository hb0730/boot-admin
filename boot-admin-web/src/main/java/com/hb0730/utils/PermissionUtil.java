package com.hb0730.utils;

import cn.hutool.core.collection.CollectionUtil;
import com.hb0730.base.enums.sys.MenuTypeEnums;
import com.hb0730.base.utils.TreeUtil;
import com.hb0730.biz.dto.sys.system.PermissionDto;
import com.hb0730.sys.system.model.vo.MenuTreeVO;
import com.hb0730.sys.system.model.vo.RouteMetaVO;
import com.hb0730.sys.system.model.vo.RouteVO;
import jakarta.annotation.Nonnull;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限工具类
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/18
 */
@UtilityClass
public class PermissionUtil {

    /**
     * 构建路由
     *
     * @param list .
     * @return .
     */
    public List<RouteVO> buildRoutes(List<PermissionDto> list) {
        List<PermissionDto> tree = buildTree(list, true);
        if (CollectionUtil.isEmpty(tree)) {
            return null;
        }
        return _buildRoutes(tree);
    }

    /**
     * 构建菜单树
     *
     * @param dtoList .
     *                * @param filterButton 是否过滤按钮
     * @return .
     */
    public List<MenuTreeVO> buildMenuTree(List<PermissionDto> dtoList, boolean filterButton) {
        if (CollectionUtil.isEmpty(dtoList)) {
            return null;
        }
        List<PermissionDto> tree = buildTree(dtoList, filterButton);
        if (CollectionUtil.isEmpty(tree)) {
            return null;
        }
        return _buildMenuTree(tree);
    }


    /**
     * 构建树
     *
     * @param dataList     数据
     * @param filterButton 是否过滤按钮
     * @return 树
     */
    private List<PermissionDto> buildTree(List<PermissionDto> dataList, boolean filterButton) {
        if (CollectionUtil.isEmpty(dataList)) {
            return null;
        }
        if (filterButton) {
            dataList.removeIf(permissionDto -> MenuTypeEnums.Menu_4.getValue().equals(permissionDto.getMenuType()));
        }
        if (CollectionUtil.isEmpty(dataList)) {
            return null;
        }
        return TreeUtil.buildTree(dataList);
    }

    /**
     * 构建路由
     *
     * @param dtoList .
     * @return .x
     */
    private List<RouteVO> _buildRoutes(@Nonnull List<PermissionDto> dtoList) {
        List<RouteVO> routes = new ArrayList<>();
        dtoList.forEach(
                item -> {
                    RouteVO route = new RouteVO();
                    // 路由路径
                    route.setPath(item.getPath());
                    // 路由名称
                    route.setName(item.getRouteName());
                    // 路由组件
                    route.setComponent(item.getComponent());
                    // 路由重定向
                    route.setRedirect(item.getRedirect());
                    // 路由元信息
                    route.setMeta(buildMeta(item));
                    // 子路由
                    List<PermissionDto> children = item.getChildren();
                    if (CollectionUtil.isNotEmpty(children)) {
                        route.setChildren(_buildRoutes(children));
                    }
                    routes.add(route);
                }
        );
        return routes;
    }

    private List<MenuTreeVO> _buildMenuTree(@Nonnull List<PermissionDto> dtoList) {
        List<MenuTreeVO> menuTree = new ArrayList<>();
        dtoList.forEach(
                item -> {
                    MenuTreeVO tree = new MenuTreeVO();
                    tree.setId(item.getId());
                    tree.setTitle(item.getTitle());
                    tree.setId(item.getId());
                    //子类
                    List<PermissionDto> children = item.getChildren();
                    if (CollectionUtil.isNotEmpty(children)) {
                        tree.setChildren(_buildMenuTree(children));
                    }
                    menuTree.add(tree);
                }
        );
        return menuTree;
    }

    /**
     * 构建meta
     *
     * @param dto .
     * @return .
     */
    private RouteMetaVO buildMeta(PermissionDto dto) {
        RouteMetaVO meta = new RouteMetaVO();
        // 菜单标题
        meta.setTitle(dto.getTitle());
        // 菜单图标
        meta.setIcon(dto.getIcon());
        // 顶级路由
        if (isRoot(dto)) {
            // 菜单排序
            meta.setRank(dto.getRank());
        } else {
            // 是否显示父菜单
            meta.setShowParent(dto.getShowParent());
        }
        // 是否展示
        meta.setShowLink(dto.getShowLink());
        // 是否缓存
        meta.setKeepAlive(dto.getKeepAlive());
        // 需要内嵌的iframe链接地址
        meta.setFrameSrc(dto.getFrameSrc());
        return meta;
    }

    /**
     * 是否是根节点
     *
     * @param dto .
     * @return .
     */
    private boolean isRoot(PermissionDto dto) {
        return null == dto.getParentId() || dto.getParentId() == 0;
    }
}
