package com.hb0730.boot.admin.project.system.menu.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hb0730.boot.admin.commons.constant.RedisConstant;
import com.hb0730.boot.admin.commons.enums.EnabledEnum;
import com.hb0730.boot.admin.commons.enums.ResponseStatusEnum;
import com.hb0730.boot.admin.commons.enums.SortTypeEnum;
import com.hb0730.boot.admin.domain.service.impl.SuperBaseServiceImpl;
import com.hb0730.boot.admin.event.menu.MenuEvent;
import com.hb0730.boot.admin.exceptions.LoginException;
import com.hb0730.boot.admin.project.system.menu.mapper.IMenuMapper;
import com.hb0730.boot.admin.project.system.menu.model.dto.MenuDTO;
import com.hb0730.boot.admin.project.system.menu.model.dto.TreeMenuDTO;
import com.hb0730.boot.admin.project.system.menu.model.entity.MenuEntity;
import com.hb0730.boot.admin.project.system.menu.model.query.MenuParams;
import com.hb0730.boot.admin.project.system.menu.model.vo.MenuMetaVO;
import com.hb0730.boot.admin.project.system.menu.model.vo.MenuPermissionVO;
import com.hb0730.boot.admin.project.system.menu.model.vo.VueMenuVO;
import com.hb0730.boot.admin.project.system.menu.service.IMenuService;
import com.hb0730.boot.admin.project.system.permission.mapper.IPermissionMapper;
import com.hb0730.boot.admin.project.system.permission.model.entity.PermissionEntity;
import com.hb0730.boot.admin.security.model.User;
import com.hb0730.boot.admin.security.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.hb0730.commons.lang.constants.PathConst.ROOT_PATH;

/**
 * 菜单  服务实现类
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Service
@RequiredArgsConstructor
public class MenuServiceImpl extends SuperBaseServiceImpl<Long, MenuParams, MenuDTO, MenuEntity, IMenuMapper> implements IMenuService {
    private final IPermissionMapper permissionMapper;
    private final RedisTemplate<String, List<TreeMenuDTO>> redisTemplate;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(MenuEntity entity) {
        Assert.notNull(entity, "修改信息不为空");
        Assert.notNull(entity.getId(), "id为空");
        Integer isEnabled = entity.getIsEnabled();
        boolean updateResult = super.updateById(entity);
        // 禁用子集
        if (EnabledEnum.UN_ENABLED.getValue().equals(isEnabled)) {
            List<MenuDTO> children = getChildrenByParenId(entity.getId());
            if (CollectionUtil.isNotEmpty(children)) {
                Set<Long> childrenIds = children.stream().map(MenuDTO::getId).collect(Collectors.toSet());
                UpdateWrapper<MenuEntity> update = Wrappers.update();
                update.set(MenuEntity.IS_ENABLED, EnabledEnum.UN_ENABLED.getValue());
                update.in(MenuEntity.ID, childrenIds);
                super.update(update);
            }
        }
        return updateResult;
    }

    @Override
    public boolean removeById(Serializable id) {
        //获取子集
        Set<Long> ids = Sets.newHashSet((Long) id);
        List<MenuDTO> children = this.getChildrenByParenId((Long) id);
        if (CollectionUtil.isNotEmpty(children)) {
            Set<Long> childrenIds = children.stream().map(MenuDTO::getId).collect(Collectors.toSet());
            ids.addAll(childrenIds);
        }
        return super.removeByIds(ids);
    }

    @Override
    @Nullable
    public List<MenuDTO> getChildrenByParenId(@Nonnull Long id) {
        Assert.notNull(id, "id不为空");
        List<MenuEntity> entities = super.list();
        List<MenuDTO> menu = BeanUtil.copyToList(entities, MenuDTO.class);
        List<MenuDTO> result = Lists.newArrayList();
        for (MenuDTO dto : menu) {
            // 第一级
            if (dto.getParentId().equals(id)) {
                result.add(dto);
                for (MenuDTO item : menu) {
                    if (dto.getId().equals(item.getParentId())) {
                        result.add(item);
                    }
                }
            }
        }
        return result;
    }

    @SneakyThrows
    @Override
    public List<TreeMenuDTO> getCurrentMenu() {
        User currentUser = SecurityUtils.getCurrentUser();
        if (null == currentUser) {
            throw new LoginException(ResponseStatusEnum.USE_LOGIN_ERROR, "当前用户未登录,请登录后重试");
        }
        HashOperations<String, Long, List<TreeMenuDTO>> hash = redisTemplate.opsForHash();
        List<TreeMenuDTO> treeMenu = hash.get(RedisConstant.MENU_KEY_PREFIX, currentUser.getId());
        if (CollectionUtil.isEmpty(treeMenu)) {
            eventPublisher.publishEvent(new MenuEvent(this, currentUser.getId()));
        }
        treeMenu = hash.get(RedisConstant.MENU_KEY_PREFIX, currentUser.getId());
        if (CollectionUtil.isEmpty(treeMenu)) {
            return Lists.newArrayList();
        }
        return treeMenu;
    }

    @Override
    public boolean updateCurrentMenu() {
        User currentUser = SecurityUtils.getCurrentUser();
        if (null == currentUser) {
            throw new LoginException(ResponseStatusEnum.USE_LOGIN_ERROR, "当前用户未登录,请登录后重试");
        }
        eventPublisher.publishEvent(new MenuEvent(this, currentUser.getId()));
        return true;
    }

    @Override
    public List<TreeMenuDTO> queryTree() {
        MenuParams params = new MenuParams();
        params.setSortColumn(Collections.singletonList(MenuEntity.SORT));
        params.setSortType(SortTypeEnum.ASC.getValue());
        QueryWrapper<MenuEntity> query = super.query(params);
        List<MenuEntity> entities = super.list(query);
        List<TreeMenuDTO> treeMenu = BeanUtil.copyToList(entities, TreeMenuDTO.class);
        return buildTree(treeMenu);
    }

    @Override
    public List<TreeMenuDTO> buildTree(List<TreeMenuDTO> menu) {
        List<TreeMenuDTO> trees = new ArrayList<>();
        Set<Long> ids = new HashSet<>();
        for (TreeMenuDTO treeMenu : menu) {
            // -1或者null
            if (treeMenu.getParentId() == null || treeMenu.getParentId() == -1) {
                trees.add(treeMenu);
            }
            for (TreeMenuDTO it : menu) {
                if (treeMenu.getId().equals(it.getParentId())) {
                    if (treeMenu.getChildren() == null) {
                        treeMenu.setChildren(new ArrayList<>());
                    }
                    treeMenu.getChildren().add(it);
                    ids.add(it.getId());
                }
            }
        }
        if (trees.size() == 0) {
            trees = menu.stream().filter(s -> !ids.contains(s.getId())).collect(Collectors.toList());
        }
        return trees;
    }

    @Override
    public List<VueMenuVO> buildVueMenus(List<TreeMenuDTO> treeMenu) {
        List<VueMenuVO> list = new LinkedList<>();
        treeMenu.forEach(menu -> {
                if (menu != null) {
                    VueMenuVO menuVO = new VueMenuVO();
                    menuVO.setName(menu.getEnname());
                    // 一级目录需要加斜杠，不然会报警告 path
                    if (!menu.getPath().startsWith(ROOT_PATH)
                        &&
                        (menu.getParentId() == null || menu.getParentId() == -1)) {
                        menuVO.setPath(ROOT_PATH + menu.getPath());
                    } else {
                        menuVO.setPath(menu.getPath());
                    }

                    // component parent
                    if (menu.getParentId()==null||menu.getParentId()==-1){
                        menuVO.setComponent(StrUtil.isBlank(menu.getComponent())?"Layout":menu.getComponent());
                    }else if(StrUtil.isNotBlank(menu.getComponent())){
                        menuVO.setComponent(menu.getComponent());
                    }

                    // meta
                    MenuMetaVO meta=new MenuMetaVO();
                    meta.setRank(menu.getSort());
                    meta.setShowLink(true);
                    meta.setIcon(menu.getIcon());
                    meta.setTitle(menu.getTitle());
                    meta.setI18n(false);
                    meta.setKeepAlive(false);
                    menuVO.setMeta(meta);

                    List<TreeMenuDTO> children = menu.getChildren();
                    if (CollectionUtil.isNotEmpty(children)) {
                        menuVO.setRedirect(children.get(0).getPath());
                        menuVO.setChildren(buildVueMenus(children));
                    } else {

                    }
                    list.add(menuVO);
                }
            }
        );
        return list;
    }

    @Override
    public List<MenuPermissionVO> queryMenuPermissionTree() {
        List<PermissionEntity> permissionEntities = permissionMapper.selectList(null);
        List<MenuEntity> entities = super.list();
        List<MenuPermissionVO> trees = new ArrayList<>();
        for (MenuEntity menuEntity : entities) {

            MenuPermissionVO menuPermission = new MenuPermissionVO();
            if (menuEntity.getParentId() == null || menuEntity.getParentId() == -1) {
                menuPermission.setId(menuEntity.getId());
                menuPermission.setName(menuEntity.getTitle());
                menuPermission.setIsPermission(false);
                trees.add(menuPermission);
            }
            for (MenuEntity entity : entities) {
                if (menuEntity.getId().equals(entity.getParentId())) {
                    if (menuPermission.getChildren() == null) {
                        menuPermission.setChildren(new ArrayList<>());
                    }
                    MenuPermissionVO menuPermissionInfo = new MenuPermissionVO();
                    menuPermissionInfo.setName(entity.getTitle());
                    menuPermissionInfo.setId(entity.getId());
                    menuPermissionInfo.setIsPermission(false);
                    menuPermission.getChildren().add(menuPermissionInfo);
                    // 权限
                    List<PermissionEntity> permissionList = permissionEntities.stream().filter(e -> e.getMenuId().equals(entity.getId())).collect(Collectors.toList());
                    for (PermissionEntity permissionEntity : permissionList) {
                        MenuPermissionVO permission = new MenuPermissionVO();
                        permission.setIsPermission(true);
                        permission.setId(permissionEntity.getId());
                        permission.setName(permissionEntity.getName());
                        if (menuPermissionInfo.getChildren() == null) {
                            menuPermissionInfo.setChildren(new ArrayList<>());
                        }
                        menuPermissionInfo.getChildren().add(permission);
                    }
                }
            }
        }
        return trees;
    }

    @Override
    public List<MenuEntity> getSuperior(@Nonnull Long id, List<MenuEntity> entities) {
        if (null == id || -1 == id) {
            return entities;
        }
        MenuEntity menuEntity = super.getById(id);
        entities.add(menuEntity);
        return getSuperior(menuEntity.getParentId(), entities);
    }
}
