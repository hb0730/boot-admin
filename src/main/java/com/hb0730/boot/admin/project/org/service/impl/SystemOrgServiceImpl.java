package com.hb0730.boot.admin.project.org.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.hb0730.boot.admin.commons.utils.BeanUtils;
import com.hb0730.boot.admin.project.org.mapper.ISystemOrgMapper;
import com.hb0730.boot.admin.project.org.model.entity.SystemOrgEntity;
import com.hb0730.boot.admin.project.org.model.vo.SystemOrgVO;
import com.hb0730.boot.admin.project.org.model.vo.TreeOrgVO;
import com.hb0730.boot.admin.project.org.service.ISystemOrgService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 系统组织  服务实现类
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-26
 */
@Service
public class SystemOrgServiceImpl extends ServiceImpl<ISystemOrgMapper, SystemOrgEntity> implements ISystemOrgService {

    @Override
    public List<SystemOrgVO> getOrgByParentId(@NonNull Long parentId) {
        QueryWrapper<SystemOrgEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SystemOrgEntity.PARENT_ID, parentId);
        queryWrapper.eq(SystemOrgEntity.IS_ENABLED, 1);
        List<SystemOrgEntity> entities = super.list(queryWrapper);
        return BeanUtils.transformFromInBatch(entities, SystemOrgVO.class);
    }

    @Override
    public List<TreeOrgVO> getTreeAll() {
        List<SystemOrgVO> orgVo = getOrgByParentId(-1L);
        List<TreeOrgVO> trees = BeanUtils.transformFromInBatch(orgVo, TreeOrgVO.class);
        if (CollectionUtils.isEmpty(orgVo)) {
            return trees;
        }
        List<TreeOrgVO> newTress = Lists.newArrayList();
        trees.forEach((tree) -> {
            List<TreeOrgVO> childes = Lists.newArrayList();
            TreeOrgVO children = getChildes(tree, childes);
            newTress.add(children);
        });
        return newTress;
    }

    @Override
    public TreeOrgVO getTreeById(@NonNull Long id) {
        QueryWrapper<SystemOrgEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SystemOrgEntity.IS_ENABLED, 1);
        queryWrapper.eq(SystemOrgEntity.ID, id);
        SystemOrgEntity entity = super.getOne(queryWrapper);
        TreeOrgVO tree = BeanUtils.transformFrom(entity, TreeOrgVO.class);
        List<TreeOrgVO> trees = Lists.newArrayList();
        assert tree != null;
        return getChildes(tree, trees);
    }


    /**
     * <p>
     * 获取子树
     * </p>
     *
     * @param org   树形组织
     * @param trees 组织树集
     * @return 组织树
     */
    private TreeOrgVO getChildes(TreeOrgVO org, List<TreeOrgVO> trees) {
        org.setChildren(trees);
        List<SystemOrgVO> orgChildes = getOrgByParentId(org.getId());
        List<TreeOrgVO> treeOrgs = BeanUtils.transformFromInBatch(orgChildes, TreeOrgVO.class);
        if (!CollectionUtils.isEmpty(treeOrgs)) {
            treeOrgs.forEach((orgTree) -> {
                List<TreeOrgVO> tree = Lists.newArrayList();
                TreeOrgVO childes = getChildes(orgTree, tree);
                trees.add(childes);
            });
        }
        return org;
    }
}
