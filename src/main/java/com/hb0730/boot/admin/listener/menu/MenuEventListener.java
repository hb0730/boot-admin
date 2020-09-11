package com.hb0730.boot.admin.listener.menu;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hb0730.boot.admin.commons.constant.RedisConstant;
import com.hb0730.boot.admin.commons.enums.SortTypeEnum;
import com.hb0730.boot.admin.commons.utils.QueryWrapperUtils;
import com.hb0730.boot.admin.event.menu.MenuEvent;
import com.hb0730.boot.admin.project.system.menu.model.dto.TreeMenuDTO;
import com.hb0730.boot.admin.project.system.menu.model.entity.MenuEntity;
import com.hb0730.boot.admin.project.system.menu.model.query.MenuParams;
import com.hb0730.boot.admin.project.system.menu.service.IMenuService;
import com.hb0730.boot.admin.project.system.permission.model.entity.PermissionEntity;
import com.hb0730.boot.admin.project.system.user.model.dto.UserDTO;
import com.hb0730.boot.admin.project.system.user.model.entity.UserAccountEntity;
import com.hb0730.boot.admin.project.system.user.service.IUserInfoService;
import com.hb0730.boot.admin.project.system.user.service.impl.UserInfoServiceImpl;
import com.hb0730.commons.cache.Cache;
import com.hb0730.commons.lang.collection.CollectionUtils;
import com.hb0730.commons.spring.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * menu listener
 *
 * @author bing_huang
 * @since 3.0.0
 */
@RequiredArgsConstructor
@Component
public class MenuEventListener implements ApplicationListener<MenuEvent> {
    private final IUserInfoService userInfoService;
    private final IMenuService menuService;
    private final Cache<String, List<TreeMenuDTO>> redisCache;

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
        if (CollectionUtils.isEmpty(menu)) {
            return;
        }
        redisCache.put(RedisConstant.MENU_KEY_PREFIX + userId, menu, 1, TimeUnit.DAYS);

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
            return BeanUtils.transformFromInBatch(entities, TreeMenuDTO.class);
        }
        Collection<Long> permissionIds = user.getPermissionIds();
        if (CollectionUtils.isEmpty(permissionIds)) {
            return Lists.newArrayList();
        }
        LambdaQueryWrapper<PermissionEntity> queryWrapper = Wrappers.lambdaQuery(PermissionEntity.class)
                .in(PermissionEntity::getId, permissionIds)
                .select(PermissionEntity::getMenuId);
        List<PermissionEntity> permissionEntities = ((UserInfoServiceImpl) userInfoService.getThis()).getPermissionService().list(queryWrapper);
        if (CollectionUtils.isEmpty(permissionEntities)) {
            return Lists.newArrayList();
        }
        // 菜单
        List<Long> menuIds = permissionEntities.parallelStream().map(PermissionEntity::getMenuId).collect(Collectors.toList());
        Set<MenuEntity> entities = Sets.newHashSet();
        for (Long menuId : menuIds) {
            entities.addAll(menuService.getSuperior(menuId, Lists.newArrayList()));
        }
        List<MenuEntity> menuEntities = entities.stream().sorted(Comparator.comparing(MenuEntity::getSort)).collect(Collectors.toList());
        return BeanUtils.transformFromInBatch(menuEntities, TreeMenuDTO.class);
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
