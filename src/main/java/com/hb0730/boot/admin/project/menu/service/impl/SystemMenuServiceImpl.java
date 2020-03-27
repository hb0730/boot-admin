package com.hb0730.boot.admin.project.menu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.hb0730.boot.admin.commons.utils.BeanUtils;
import com.hb0730.boot.admin.commons.web.exception.BaseException;
import com.hb0730.boot.admin.project.menu.mapper.ISystemMenuMapper;
import com.hb0730.boot.admin.project.menu.model.entity.SystemMenuEntity;
import com.hb0730.boot.admin.project.menu.model.vo.SystemMenuVO;
import com.hb0730.boot.admin.project.menu.model.vo.TreeMenuVO;
import com.hb0730.boot.admin.project.menu.service.ISystemMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 系统菜单  服务实现类
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-26
 */
@Service
public class SystemMenuServiceImpl extends ServiceImpl<ISystemMenuMapper, SystemMenuEntity> implements ISystemMenuService {
    @Autowired
    private ISystemMenuMapper menuMapper;

    public SystemMenuServiceImpl() {
    }

    @Override
    public List<TreeMenuVO> getTreeMenuAll() {
        List<SystemMenuVO> menus = getMenuByParentId(-1L);
        List<TreeMenuVO> trees = BeanUtils.transformFromInBatch(menus, TreeMenuVO.class);
        if (CollectionUtils.isEmpty(menus)) {
            return trees;
        }
        List<TreeMenuVO> newTress = Lists.newArrayList();
        trees.forEach((tree) -> {
            List<TreeMenuVO> childes = Lists.newArrayList();
            TreeMenuVO children = getChildes(tree, childes);
            newTress.add(children);
        });
        return newTress;
    }

    @Override
    public boolean removeById(Serializable id) {
        UpdateWrapper<SystemMenuEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set(SystemMenuEntity.IS_ENABLED, 0);
        updateWrapper.eq(SystemMenuEntity.ID, id);
        super.update(updateWrapper);
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
    public List<SystemMenuVO> getMenuByParentId(@NonNull Long id) {
        QueryWrapper<SystemMenuEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SystemMenuEntity.PARENT_ID, id);
        queryWrapper.eq(SystemMenuEntity.IS_ENABLED, 1);
        List<SystemMenuEntity> entities = list(queryWrapper);
        return BeanUtils.transformFromInBatch(entities, SystemMenuVO.class);
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
     * @return 树形菜单
     */
    @NonNull
    private TreeMenuVO getChildes(@NonNull TreeMenuVO vo, @NonNull List<TreeMenuVO> treeMenus) {
        List<SystemMenuVO> menu = getMenuByParentId(vo.getId());
        List<TreeMenuVO> treeMenu = BeanUtils.transformFromInBatch(menu, TreeMenuVO.class);
        vo.setChildren(treeMenus);
        if (!CollectionUtils.isEmpty(treeMenu)) {
            treeMenu.forEach((tree) -> {
                List<TreeMenuVO> newTreeMen = Lists.newArrayList();
                TreeMenuVO childes = getChildes(tree, newTreeMen);
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
}
