package com.hb0730.boot.admin.modules.sys.system.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.hb0730.boot.admin.base.core.service.BaseServiceImpl;
import com.hb0730.boot.admin.data.enums.MenuTypeEnums;
import com.hb0730.boot.admin.modules.sys.system.mapper.SysPermissionMapper;
import com.hb0730.boot.admin.modules.sys.system.model.dto.PermissionTree;
import com.hb0730.boot.admin.modules.sys.system.model.entity.SysPermission;
import com.hb0730.boot.admin.modules.sys.system.model.vo.VueMenuRouteMeta;
import com.hb0730.boot.admin.modules.sys.system.model.vo.VueMenuRouteVO;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/2/4
 */
@Service
@Slf4j
public class SysPermissionService extends BaseServiceImpl<SysPermissionMapper, SysPermission> {
    @Resource
    @Lazy
    private SysUserService sysUserService;

    /**
     * 获取权限ID集合 BY 角色ID结合
     *
     * @param roleIds 角色ID集合
     * @return 权限ID集合
     */
    @Nonnull
    public Set<String> listPermissionIdsByRoleIds(@Nonnull List<String> roleIds) {
        Set<String> permissionIds = this.baseMapper.listPermissionIdsByRoleIds(roleIds);
        return permissionIds == null ? Collections.emptySet() : permissionIds;
    }

    /**
     * 根据权限ID获取ID代码
     *
     * @param permissionIds 权限ID
     * @return 权限代码
     */
    @Nonnull
    public Set<String> listPermissionPreByIds(@Nonnull List<String> permissionIds) {
        Set<String> preCodes = this.baseMapper.listPermissionPreByIds(permissionIds);
        return preCodes == null ? Collections.emptySet() : preCodes;
    }

    /**
     * 获取全部的权限信息
     *
     * @return .
     */
    @Nonnull
    public Set<String> allPermissionPre() {
        Set<String> preCodes = this.baseMapper.allPermissionPre();
        return preCodes == null ? Collections.emptySet() : preCodes;
    }

    /**
     * 获取全部的ID
     *
     * @return .
     */
    @Nonnull
    public Set<String> allPermissionIds() {
        Set<String> preCodes = this.baseMapper.allPermissionIds();
        return preCodes == null ? Collections.emptySet() : preCodes;
    }

    /**
     * 根据用户获取菜单路由
     *
     * @param userid 用户ID
     * @return 菜单路由
     */
    public List<VueMenuRouteVO> queryRouteByUserid(String userid) {
        Set<String> roleIds = sysUserService.queryRoleIdsByUserId(userid);
        Set<String> permissionsIds = listPermissionIdsByRoleIds(List.copyOf(roleIds));
        return queryRouteByIds(List.copyOf(permissionsIds));
    }

    /**
     * 根据权限ID获取菜单路由
     *
     * @param ids 权限ID
     * @return 菜单路由
     */
    @Nonnull
    public List<VueMenuRouteVO> queryRouteByIds(@Nonnull List<String> ids) {
        List<SysPermission> sysPermissions = this.baseMapper.listEnabledPermission(ids);
        if (null == sysPermissions) {
            return Collections.emptyList();
        }
        List<PermissionTree> trees = buildTree(sysPermissions);
        return buildRoute(trees);
    }

    /**
     * 将菜单路由权限集合构建为树形
     *
     * @param permissions 菜单路由权限
     * @return .
     */
    public List<PermissionTree> buildTree(@Nonnull List<SysPermission> permissions) {
        List<PermissionTree> permissionTrees = BeanUtil.copyToList(permissions, PermissionTree.class);
        List<PermissionTree> trees = new ArrayList<>();
        Set<String> ids = new HashSet<>();
        for (PermissionTree permissionTree : permissionTrees) {
            if (StrUtil.isBlank(permissionTree.getParentId())) {
                trees.add(permissionTree);
            }
            for (PermissionTree tree : permissionTrees) {
                if (permissionTree.getId().equals(tree.getParentId())) {
                    if (permissionTree.getChildren() == null) {
                        permissionTree.setChildren(new ArrayList<>());
                    }
                    permissionTree.getChildren().add(tree);

                    ids.add(tree.getId());
                }
            }
        }
        if (trees.size() == 0) {
            trees = permissionTrees.stream().filter(s -> !ids.contains(s.getId())).collect(Collectors.toList());
        }
        return trees;
    }

    /**
     * 构建菜单路由
     *
     * @param permissionTrees 菜单路由权限树集合
     * @return 菜单路由
     */
    public List<VueMenuRouteVO> buildRoute(@Nonnull List<PermissionTree> permissionTrees) {
        List<VueMenuRouteVO> menuRouteList = new ArrayList<>();
        for (PermissionTree permissionTree : permissionTrees) {
            menuRouteList.add(buildRoute(permissionTree));
        }
        return menuRouteList;
    }


    /**
     * 构建菜单路由
     *
     * @param permissionTree 菜单路由权限树
     * @return 菜单路由
     */
    private VueMenuRouteVO buildRoute(PermissionTree permissionTree) {
        VueMenuRouteVO menuRoute = new VueMenuRouteVO();
        Integer menuType = permissionTree.getMenuType();
        //  路由地址 必须有个 `/`
        menuRoute.setPath(StrUtil.addPrefixIfNot(permissionTree.getPath(), "/"));
        // 路由名字（必须保持唯一）
        menuRoute.setName(permissionTree.getName());
        // 元信息
        VueMenuRouteMeta routeMeta = new VueMenuRouteMeta();
        // 菜单名称
        routeMeta.setTitle(permissionTree.getTitle());
        // 菜单图标
        routeMeta.setIcon(permissionTree.getIcon());
        // 是否显示
        routeMeta.setShowLink(permissionTree.getShowLink() == 1);
        // 目录
        if (MenuTypeEnums.dir.getValue().equals(menuType)) {
            // 菜单排序，值越高排的越后（只针对顶级路由）
            routeMeta.setRank(permissionTree.getRank());
        }
        //菜单
        else if (MenuTypeEnums.menu.getValue().equals(menuType)) {
            // 按需加载需要展示的页面 不需要 '/'
            menuRoute.setComponent(StrUtil.removePrefix(permissionTree.getComponent(), "/"));
            //  是否显示父级菜单
            routeMeta.setShowParent(permissionTree.getShowParent() == 1);
            // 是否缓存该路由页面
            routeMeta.setKeepAlive(permissionTree.getKeepAlive() == 1);
            // 需要内嵌的iframe链接地址
            if (permissionTree.getIsFrame() == 1) {
                routeMeta.setFrameSrc(permissionTree.getFrameSrc());
            }
        }
        menuRoute.setMeta(routeMeta);

        if (CollectionUtil.isNotEmpty(permissionTree.getChildren())) {
            menuRoute.setChildren(buildRoute(permissionTree.getChildren()));
        }
        return menuRoute;
    }
}
