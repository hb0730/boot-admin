package com.hb0730.boot.admin.project.system.dept.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hb0730.boot.admin.commons.utils.QueryWrapperUtils;
import com.hb0730.boot.admin.domain.service.impl.SuperBaseServiceImpl;
import com.hb0730.boot.admin.project.system.dept.mapper.IDeptMapper;
import com.hb0730.boot.admin.project.system.dept.model.dto.DeptDTO;
import com.hb0730.boot.admin.project.system.dept.model.dto.TreeDeptDTO;
import com.hb0730.boot.admin.project.system.dept.model.entity.DeptEntity;
import com.hb0730.boot.admin.project.system.dept.model.query.DeptParams;
import com.hb0730.boot.admin.project.system.dept.service.IDeptService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 部门  服务实现类
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Service
public class DeptServiceImpl extends SuperBaseServiceImpl<Long, DeptParams, DeptDTO, DeptEntity, IDeptMapper> implements IDeptService {

    @Override
    public QueryWrapper<DeptEntity> query(@Nonnull DeptParams params) {
        QueryWrapper<DeptEntity> query = QueryWrapperUtils.getQuery(params);
        String name = params.getName();
        if (StrUtil.isNotBlank(name)) {
            query.like(DeptEntity.NAME, name);
        }
        Integer isEnabled = params.getIsEnabled();
        if (Objects.nonNull(isEnabled)) {
            query.eq(DeptEntity.IS_ENABLED, isEnabled);
        }
        return query;
    }

    @Override
    public boolean updateById(DeptEntity entity) {
        Long parentId = entity.getParentId();
        if (Objects.isNull(parentId) || parentId == -1L) {
            entity.setAncestors("-1");
        } else {
            DeptEntity parentEntity = super.getById(parentId);
            entity.setAncestors(parentEntity.getAncestors() + "_" + parentId);
        }
        return super.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(Serializable id) {
        Long firstId = (Long) id;
        List<DeptDTO> childrenId = this.getChildrenByParenId(firstId);
        Set<Long> ids = Sets.newHashSet();
        if (!CollectionUtil.isEmpty(childrenId)) {
            ids.addAll(childrenId.stream().map(DeptDTO::getId).collect(Collectors.toSet()));
        }
        ids.add(firstId);
        return super.removeByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIds(Collection<?> list) {
        Set<Long> ids = Sets.newHashSet();
        for (Object id : list) {
            List<DeptDTO> children = this.getChildrenByParenId((Long) id);
            if (!CollectionUtil.isEmpty(children)) {
                ids.addAll(children.stream().map(DeptDTO::getId).collect(Collectors.toSet()));
            }
            ids.add((Long) id);
        }
        return super.removeByIds(ids);
    }

    @Override
    public boolean save(@Nonnull DeptDTO dto) {
        Long parentId = dto.getParentId();
        if (Objects.isNull(parentId) || parentId == -1L) {
            dto.setAncestors("-1");
        } else {
            DeptEntity entity = super.getById(dto.getParentId());
            dto.setAncestors(entity.getAncestors() + "_" + dto.getParentId());
        }
        return super.save(dto);
    }

    @Override
    public DeptDTO findById(@Nonnull Long id) {
        Assert.notNull(id, "id不为空");
        DeptEntity entity = super.getById(id);
        return BeanUtil.toBean(entity, DeptDTO.class);
    }

    @Nullable
    @Override
    public List<DeptDTO> getChildrenByParenId(@Nonnull Long id) {
        Assert.notNull(id, "id不为空");
        List<DeptEntity> entities = super.list();
        List<DeptDTO> dept = BeanUtil.copyToList(entities, DeptDTO.class);
        List<DeptDTO> result = Lists.newArrayList();
        for (DeptDTO dto : dept) {
            // 第一级
            if (dto.getParentId().equals(id)) {
                result.add(dto);
                for (DeptDTO item : dept) {
                    if (dto.getId().equals(item.getParentId())) {
                        result.add(item);
                    }
                }
            }
        }
        return result;
    }

    @Override
    public Set<TreeDeptDTO> buildTree(@Nonnull List<DeptDTO> deptList) {
        Set<TreeDeptDTO> trees = new LinkedHashSet<>();
        Set<TreeDeptDTO> depts = new LinkedHashSet<>();
        List<String> deptNames = deptList.stream().map(DeptDTO::getName).collect(Collectors.toList());
        List<TreeDeptDTO> treeDeptList = BeanUtil.copyToList(deptList, TreeDeptDTO.class);
        boolean isChild;
        for (TreeDeptDTO dept : treeDeptList) {
            isChild = false;
            if (dept.getParentId() == null || dept.getParentId() == -1) {
                trees.add(dept);
            }
            for (TreeDeptDTO item : treeDeptList) {
                if ((item.getParentId() != null && item.getParentId() != -1) && dept.getId().equals(item.getParentId())) {
                    isChild = true;
                    if (dept.getChildren() == null) {
                        dept.setChildren(Lists.newArrayList());
                    }
                    dept.getChildren().add(item);
                }
            }
            if (isChild) {
                depts.add(dept);
            } else if ((dept.getParentId() != null && dept.getParentId() != -1) && !deptNames.contains(findById(dept.getParentId()).getName())) {
                depts.add(dept);
            }
        }
        if (CollectionUtil.isEmpty(trees)) {
            trees = depts;
        }
        return trees;
    }
}
