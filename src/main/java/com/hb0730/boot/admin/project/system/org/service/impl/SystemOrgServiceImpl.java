package com.hb0730.boot.admin.project.system.org.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.hb0730.boot.admin.commons.constant.SystemConstants;
import com.hb0730.boot.admin.commons.domain.service.BaseServiceImpl;
import com.hb0730.boot.admin.commons.utils.QueryWrapperUtils;
import com.hb0730.boot.admin.commons.utils.bean.BeanUtils;
import com.hb0730.boot.admin.exception.BaseException;
import com.hb0730.boot.admin.project.system.menu.model.entity.SystemMenuEntity;
import com.hb0730.boot.admin.project.system.org.mapper.ISystemOrgMapper;
import com.hb0730.boot.admin.project.system.org.model.entity.SystemOrgEntity;
import com.hb0730.boot.admin.project.system.org.model.vo.OrgParams;
import com.hb0730.boot.admin.project.system.org.model.vo.SystemOrgVO;
import com.hb0730.boot.admin.project.system.org.model.vo.TreeOrgVO;
import com.hb0730.boot.admin.project.system.org.service.ISystemOrgService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 系统组织  服务实现类
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-26
 */
@Service
public class SystemOrgServiceImpl extends BaseServiceImpl<ISystemOrgMapper, SystemOrgEntity> implements ISystemOrgService {

    @Override
    public boolean save(SystemOrgEntity entity) {
        if (entity.getParentId() == null) {
            entity.setParentId(SystemConstants.PARENT_ID);
            entity.setAncestors(String.valueOf(entity.getParentId()));
        } else {
            QueryWrapper<SystemOrgEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(SystemOrgEntity.IS_ENABLED, SystemConstants.ENABLED);
            queryWrapper.eq(SystemOrgEntity.ID, entity.getParentId());
            SystemOrgEntity parentEntity = super.getOne(queryWrapper);
            if (Objects.isNull(parentEntity)) {
                throw new BaseException("父组织已停用,不允许新增");
            }
            entity.setAncestors(parentEntity.getAncestors() + "_" + entity.getParentId());
        }
        return super.save(entity);
    }

    @Override
    public boolean updateById(SystemOrgEntity entity) {
        SystemOrgEntity e1 = super.getById(entity.getId());
        BeanUtils.updateProperties(entity, e1);
        //修改组织 为禁用 当前组织下用户怎么办


        return super.updateById(e1);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(Serializable id) {
        UpdateWrapper<SystemOrgEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set(SystemOrgEntity.IS_ENABLED, SystemConstants.UN_ENABLED);
        updateWrapper.eq(SystemOrgEntity.ID, id);
        // 当前组织存在用户 删除该怎么办

        super.update(updateWrapper);
        return super.removeById(id);
    }

    @Override
    public List<SystemOrgVO> getOrgByParentId(@NonNull Long parentId, @NonNull Integer isAll) {
        QueryWrapper<SystemOrgEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SystemOrgEntity.PARENT_ID, parentId);
        if (SystemConstants.IS_ALL != isAll) {
            queryWrapper.eq(SystemMenuEntity.IS_ENABLED, isAll);
        }
        List<SystemOrgEntity> entities = super.list(queryWrapper);
        return BeanUtils.transformFromInBatch(entities, SystemOrgVO.class);
    }

    @Override
    public List<TreeOrgVO> getTreeAll(Integer isAll) {
        List<SystemOrgVO> orgVo = getOrgByParentId(SystemConstants.PARENT_ID, isAll);
        List<TreeOrgVO> trees = BeanUtils.transformFromInBatch(orgVo, TreeOrgVO.class);
        if (CollectionUtils.isEmpty(orgVo)) {
            return trees;
        }
        List<TreeOrgVO> newTress = Lists.newArrayList();
        trees.forEach((tree) -> {
            List<TreeOrgVO> childes = Lists.newArrayList();
            TreeOrgVO children = getChildes(tree, childes, isAll);
            newTress.add(children);
        });
        return newTress;
    }

    @Override
    public TreeOrgVO getTreeById(@NonNull Long id, @NonNull Integer isAll) {
        QueryWrapper<SystemOrgEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SystemOrgEntity.ID, id);
        if (SystemConstants.IS_ALL != isAll) {
            queryWrapper.eq(SystemMenuEntity.IS_ENABLED, isAll);
        }
        SystemOrgEntity entity = super.getOne(queryWrapper);
        TreeOrgVO tree = BeanUtils.transformFrom(entity, TreeOrgVO.class);
        List<TreeOrgVO> trees = Lists.newArrayList();
        assert tree != null;
        return getChildes(tree, trees, isAll);
    }


    /**
     * <p>
     * 获取子树
     * </p>
     *
     * @param org   树形组织
     * @param trees 组织树集
     * @param isAll 是否查询全部(已禁用)
     * @return 组织树
     */
    private TreeOrgVO getChildes(TreeOrgVO org, List<TreeOrgVO> trees, Integer isAll) {
        org.setChildren(trees);
        List<SystemOrgVO> orgChildes = getOrgByParentId(org.getId(), isAll);
        List<TreeOrgVO> treeOrgs = BeanUtils.transformFromInBatch(orgChildes, TreeOrgVO.class);
        if (!CollectionUtils.isEmpty(treeOrgs)) {
            treeOrgs.forEach((orgTree) -> {
                List<TreeOrgVO> tree = Lists.newArrayList();
                TreeOrgVO childes = getChildes(orgTree, tree, isAll);
                trees.add(childes);
            });
        }
        return org;
    }


    @Override
    @Deprecated
    public Page<SystemOrgVO> page(@NonNull OrgParams params) {
        return null;
    }

    @Override
    public List<SystemOrgVO> list(@NonNull OrgParams params) {
        return null;
    }

    @NonNull
    @Override
    public QueryWrapper<SystemOrgEntity> query(@NonNull OrgParams params) {
        QueryWrapper<SystemOrgEntity> query = QueryWrapperUtils.getQuery(params);
        return query;
    }
}
