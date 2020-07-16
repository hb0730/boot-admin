package com.hb0730.boot.admin.project.system.menu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hb0730.boot.admin.commons.constant.SystemConstants;
import com.hb0730.boot.admin.commons.domain.service.SuperBaseServiceImpl;
import com.hb0730.boot.admin.commons.utils.QueryWrapperUtils;
import com.hb0730.boot.admin.commons.utils.bean.BeanUtils;
import com.hb0730.boot.admin.commons.utils.spring.SecurityUtils;
import com.hb0730.boot.admin.exception.BaseException;
import com.hb0730.boot.admin.project.system.menu.mapper.ISystemMenuMapper;
import com.hb0730.boot.admin.project.system.menu.model.entity.SystemMenuEntity;
import com.hb0730.boot.admin.project.system.menu.model.vo.SystemMenuParams;
import com.hb0730.boot.admin.project.system.menu.model.vo.SystemMenuVO;
import com.hb0730.boot.admin.project.system.menu.model.vo.TreeMenuVO;
import com.hb0730.boot.admin.project.system.menu.service.ISystemMenuService;
import com.hb0730.boot.admin.project.system.menu.utils.MenuUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.*;

/**
 * <p>
 * 系统菜单  服务实现类
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-26
 */
@Service
public class SystemMenuServiceImpl extends SuperBaseServiceImpl<SystemMenuParams, SystemMenuVO, ISystemMenuMapper, SystemMenuEntity> implements ISystemMenuService {

    public SystemMenuServiceImpl() {
    }

    @Override
    public List<TreeMenuVO> getTreeMenuAll(Integer isAll) {
        List<SystemMenuVO> menus = getMenuByParentId(SystemConstants.PARENT_ID, isAll);
        List<TreeMenuVO> trees = BeanUtils.transformFromInBatch(menus, TreeMenuVO.class);
        if (CollectionUtils.isEmpty(menus)) {
            return trees;
        }
        List<TreeMenuVO> newTress = Lists.newArrayList();
        trees.forEach((tree) -> {
            List<TreeMenuVO> childes = Lists.newArrayList();
            TreeMenuVO children = getChildes(tree, childes, isAll);
            newTress.add(children);
        });
        return newTress;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    /**
     * <p>
     * 根据父id获取菜单(非树形)
     * </p>
     *
     * @param id 菜单id
     * @return 菜单
     */
    @Override
    public List<SystemMenuVO> getMenuByParentId(@NonNull Long id, Integer isAll) {
        QueryWrapper<SystemMenuEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SystemMenuEntity.PARENT_ID, id);
        if (SystemConstants.IS_ALL != isAll) {
            queryWrapper.eq(SystemMenuEntity.IS_ENABLED, isAll);
        }
        List<SystemMenuEntity> entities = list(queryWrapper);
        return BeanUtils.transformFromInBatch(entities, SystemMenuVO.class);
    }

    @Override
    public List<Map<String, Object>> getVueTreeByMenuId(Collection<Long> menuIds) {
        if (CollectionUtils.isEmpty(menuIds)) {
            return Lists.newArrayList();
        }
        Set<Long> menuIdsSet = com.google.common.collect.Sets.newHashSet(menuIds);
        Set<Long> ids = Sets.newHashSet();
        QueryWrapper<SystemMenuEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SystemMenuEntity.IS_ENABLED, SystemConstants.ENABLED);
        List<SystemMenuEntity> allMenu = super.list(queryWrapper);
        menuIdsSet.forEach(id -> {
            SystemMenuEntity entity = getById(id);
            MenuUtils.getParentNodeInfoByChildrenNode(entity, allMenu, ids);
        });
        List<TreeMenuVO> menuTree = MenuUtils.getMenusTreeByParentId(SystemConstants.PARENT_ID);
        List<TreeMenuVO> treeByNodeId = null;
        if (!SecurityUtils.getLoginUser().isAdmin()) {
            treeByNodeId = MenuUtils.getTreeByNodeId(menuTree, ids);
        } else {
            treeByNodeId = menuTree;
        }
        List<Map<String, Object>> maps = Lists.newArrayList();
        MenuUtils.getVueModel(treeByNodeId, maps);

        return maps;
    }

    @Override
    public boolean save(SystemMenuEntity entity) {
        fillInsertEntity(entity);
        return super.save(entity);
    }

    @Override
    public boolean updateById(SystemMenuEntity entity) {
        SystemMenuEntity e1 = getById(entity.getId());
        BeanUtils.updateProperties(entity, e1);
        return super.updateById(e1);
    }

    /**
     * <p>
     * 获取树形菜单子集
     * </p>
     *
     * @param vo        树形菜单
     * @param treeMenus 树形菜单集
     * @param isAll     是否查询全部
     * @return 树形菜单
     */
    @NonNull
    private TreeMenuVO getChildes(@NonNull TreeMenuVO vo, @NonNull List<TreeMenuVO> treeMenus, Integer isAll) {
        List<SystemMenuVO> menu = getMenuByParentId(vo.getId(), isAll);
        List<TreeMenuVO> treeMenu = BeanUtils.transformFromInBatch(menu, TreeMenuVO.class);
        vo.setChildren(treeMenus);
        if (!CollectionUtils.isEmpty(treeMenu)) {
            treeMenu.forEach((tree) -> {
                List<TreeMenuVO> newTreeMen = Lists.newArrayList();
                TreeMenuVO childes = getChildes(tree, newTreeMen, isAll);
                treeMenus.add(childes);
            });
        }
        return vo;
    }

    /**
     * 填充信息
     *
     * @param entity 被填充的entity
     * @return 已填充
     */
    private void fillInsertEntity(@NonNull SystemMenuEntity entity) {
        Long parentId = entity.getParentId();
        if (Objects.isNull(parentId)) {
            entity.setParentId(-1L);
        }
        if (-1L == entity.getParentId()) {
            entity.setHasChild(0);
            entity.setIsRoot(1);
            entity.setLevel(1);
            entity.setDelFlag(0);
        } else {
            SystemMenuEntity parentEntity = super.getById(parentId);
            if (Objects.isNull(parentEntity)) {
                throw new BaseException("查询:" + entity.getName() + "获取父类信息失败");
            }
            if (parentEntity.getHasChild() == 0) {
                parentEntity.setHasChild(1);
                super.updateById(parentEntity);
            }
            entity.setLevel(parentEntity.getLevel() + 1);
            entity.setHasChild(0);
            entity.setIsRoot(0);
            entity.setDelFlag(0);
        }

    }

    @Override
    @Deprecated
    public Page<SystemMenuVO> page(@NonNull SystemMenuParams params) {
        return null;
    }

    @Override
    public List<SystemMenuVO> list(@NonNull SystemMenuParams params) {
        return null;
    }

    @Override
    @NonNull
    public QueryWrapper<SystemMenuEntity> query(@NonNull SystemMenuParams params) {
        QueryWrapper<SystemMenuEntity> query = QueryWrapperUtils.getQuery(params);
        return query;
    }

    @Override
    public boolean updateById(@NonNull SystemMenuVO vo) {
        Long id = vo.getId();
        if (Objects.isNull(id)) {
            throw new BaseException("id为空");
        }
        SystemMenuEntity entity = vo.convertTo();
        return this.updateById(entity);
    }
}
