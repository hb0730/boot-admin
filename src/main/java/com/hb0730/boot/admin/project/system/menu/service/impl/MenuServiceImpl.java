package com.hb0730.boot.admin.project.system.menu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hb0730.boot.admin.commons.enums.EnabledEnum;
import com.hb0730.boot.admin.commons.enums.SortTypeEnum;
import com.hb0730.boot.admin.domain.service.impl.SuperBaseServiceImpl;
import com.hb0730.boot.admin.project.system.menu.mapper.IMenuMapper;
import com.hb0730.boot.admin.project.system.menu.model.dto.MenuDTO;
import com.hb0730.boot.admin.project.system.menu.model.dto.TreeMenuDTO;
import com.hb0730.boot.admin.project.system.menu.model.entity.MenuEntity;
import com.hb0730.boot.admin.project.system.menu.model.query.MenuParams;
import com.hb0730.boot.admin.project.system.menu.model.vo.MenuMetaVO;
import com.hb0730.boot.admin.project.system.menu.model.vo.VueMenuVO;
import com.hb0730.boot.admin.project.system.menu.service.IMenuService;
import com.hb0730.commons.lang.StringUtils;
import com.hb0730.commons.lang.collection.CollectionUtils;
import com.hb0730.commons.spring.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import static com.hb0730.commons.lang.constants.PathConst.ROOT_PATH;

/**
 * 菜单  服务实现类
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Service
public class MenuServiceImpl extends SuperBaseServiceImpl<Long, MenuParams, MenuDTO, MenuEntity, IMenuMapper> implements IMenuService {
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
            if (!CollectionUtils.isEmpty(children)) {
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
        Set<Long> ids = Sets.newHashSet((Long)id);
        List<MenuDTO> children = this.getChildrenByParenId((Long)id);
        if (!CollectionUtils.isEmpty(children)) {
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
        List<MenuDTO> menu = BeanUtils.transformFromInBatch(entities, MenuDTO.class);
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

    @Override
    public List<TreeMenuDTO> queryTree() {
        MenuParams params = new MenuParams();
        params.setSortColumn(Collections.singletonList(MenuEntity.SORT));
        params.setSortType(SortTypeEnum.ASC.getValue());
        QueryWrapper<MenuEntity> query = super.query(params);
        List<MenuEntity> entities = super.list(query);
        List<TreeMenuDTO> treeMenu = BeanUtils.transformFromInBatch(entities, TreeMenuDTO.class);
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
                        List<TreeMenuDTO> menuDtoList = menu.getChildren();
                        // 前端路由
                        VueMenuVO menuVo = new VueMenuVO();
                        // 一级目录需要加斜杠，不然会报警告 path
                        if (!menu.getPath().startsWith(ROOT_PATH)
                                &&
                                (menu.getParentId() == null || menu.getParentId() == -1)) {
                            menuVo.setPath(ROOT_PATH + menu.getPath());
                        } else {
                            menuVo.setPath(menu.getPath());
                        }
                        // 组件名称 name
                        menuVo.setName(menu.getEnname());
                        //是否隐藏
                        menuVo.setHidden(false);
                        // component
                        if (menu.getParentId() == null || menu.getParentId() == -1) {
                            // 设置展开类型 默认 侧边栏
                            menuVo.setComponent(StringUtils.isEmpty(menu.getComponent()) ? "Layout" : menu.getComponent());
                        } else if (!StringUtils.isEmpty(menu.getComponent())) {
                            menuVo.setComponent(menu.getComponent());
                        }
                        // vue router meta
                        menuVo.setMeta(new MenuMetaVO(menu.getTitle(), menu.getIcon(), false, true));

                        if (menuDtoList != null && menuDtoList.size() != 0) {
                            menuVo.setAlwaysShow(true);
                            menuVo.setRedirect("noredirect");
                            menuVo.setChildren(buildVueMenus(menuDtoList));
                            // 处理是一级菜单并且没有子菜单的情况
                        } else if (menu.getParentId() == null || menu.getParentId() == -1) {
                            VueMenuVO menuVo1 = new VueMenuVO();
                            menuVo1.setMeta(menuVo.getMeta());
                            menuVo1.setName(menuVo.getName());
                            menuVo1.setComponent(menuVo.getComponent());
                            menuVo1.setPath(menu.getPath());

                            menuVo.setName(null);
                            menuVo.setMeta(null);
                            menuVo.setComponent("Layout");
                            List<VueMenuVO> list1 = new ArrayList<>();
                            list1.add(menuVo1);
                            menuVo.setChildren(list1);
                        }
                        list.add(menuVo);
                    }
                }
        );
        return list;
    }
}
