package com.hb0730.boot.admin.listener.menu;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hb0730.boot.admin.commons.enums.SortTypeEnum;
import com.hb0730.boot.admin.commons.utils.QueryWrapperUtils;
import com.hb0730.boot.admin.event.menu.MenuEvent;
import com.hb0730.boot.admin.project.system.menu.model.dto.TreeMenuDTO;
import com.hb0730.boot.admin.project.system.menu.model.entity.MenuEntity;
import com.hb0730.boot.admin.project.system.menu.model.query.MenuParams;
import com.hb0730.boot.admin.project.system.menu.service.IMenuService;
import com.hb0730.boot.admin.project.system.menu.service.cache.MenuCache;
import com.hb0730.boot.admin.project.system.permission.model.entity.PermissionEntity;
import com.hb0730.boot.admin.project.system.user.model.dto.UserDTO;
import com.hb0730.boot.admin.project.system.user.model.entity.UserAccountEntity;
import com.hb0730.boot.admin.project.system.user.service.IUserInfoService;
import com.hb0730.boot.admin.project.system.user.service.impl.UserInfoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * menu listener,用来支持在线刷新菜单路由
 *
 * @author bing_huang
 * @since 3.0.0
 */
@RequiredArgsConstructor
@Component
public class MenuEventListener implements ApplicationListener<MenuEvent> {
    private final IUserInfoService userInfoService;
    private final IMenuService menuService;
    private final MenuCache menuCache;
//    private final RedisTemplate<String, List<TreeMenuDTO>> redisTemplate;

    @Override
    public void onApplicationEvent(@Nonnull MenuEvent event) {
        Long userId = event.getUserId();
        if (null == userId) {
            return;
        }
        UserDTO user = findUserById(userId);
        if (null == user) {
            return;
        }
        //
        List<TreeMenuDTO> menu = findMenuByUser(user);
        if (CollectionUtil.isEmpty(menu)) {
            return;
        }
        menuCache.setMenuCache(userId + "", menu);

    }

    private List<TreeMenuDTO> findMenuByUser(UserDTO user) {
        if (null == user) {
            return null;
        }
        if (user.getIsAdmin() == 1) {
            MenuParams params = new MenuParams();
            params.setSortType(SortTypeEnum.ASC.getValue());
            params.setSortColumn(Collections.singletonList(MenuEntity.SORT));
            QueryWrapper<MenuEntity> query = QueryWrapperUtils.getQuery(params);
            List<MenuEntity> entities = menuService.list(query);
            return BeanUtil.copyToList(entities, TreeMenuDTO.class);
        }
        Collection<Long> permissionIds = user.getPermissionIds();
        if (CollectionUtil.isEmpty(permissionIds)) {
            return Lists.newArrayList();
        }
        LambdaQueryWrapper<PermissionEntity> queryWrapper = Wrappers.lambdaQuery(PermissionEntity.class)
            .in(PermissionEntity::getId, permissionIds)
            .select(PermissionEntity::getMenuId, PermissionEntity::getPermission);
        //权限
        List<PermissionEntity> permissionEntities = ((UserInfoServiceImpl) userInfoService.getThis()).getPermissionService().list(queryWrapper);
        if (CollectionUtil.isEmpty(permissionEntities)) {
            return Lists.newArrayList();
        }
        Map<Long, List<String>> permissionMapping = permissionEntities.stream().collect(Collectors.groupingBy(PermissionEntity::getMenuId,
            Collectors.mapping(PermissionEntity::getPermission, Collectors.toList())));
        // 菜单
        List<Long> menuIds = permissionEntities.parallelStream().map(PermissionEntity::getMenuId).collect(Collectors.toList());
        Set<MenuEntity> entities = Sets.newHashSet();
        for (Long menuId : menuIds) {
            entities.addAll(menuService.getSuperior(menuId, Lists.newArrayList()));
        }
        List<MenuEntity> menuEntities = entities.stream().sorted(Comparator.comparing(MenuEntity::getSort)).collect(Collectors.toList());
        List<TreeMenuDTO> treeMenu = BeanUtil.copyToList(menuEntities, TreeMenuDTO.class);
        for (TreeMenuDTO menu : treeMenu) {
            menu.setAuthority(permissionMapping.get(menu.getId()));
        }
        return treeMenu;
    }

    private UserDTO findUserById(Long userId) {
        if (null == userId) {
            return null;
        }
        UserAccountEntity entity = ((UserInfoServiceImpl) userInfoService.getThis()).getAccountService().userAccountByUserId(userId);
        if (null == entity) {
            return null;
        }
        return userInfoService.loadUserByUsername(entity.getUsername());

    }
}
