package com.hb0730.boot.admin.project.system.dept.service.impl;

import com.google.common.collect.Lists;
import com.hb0730.boot.admin.domain.service.impl.SuperBaseServiceImpl;
import com.hb0730.boot.admin.project.system.dept.mapper.IDeptMapper;
import com.hb0730.boot.admin.project.system.dept.model.dto.DeptDTO;
import com.hb0730.boot.admin.project.system.dept.model.dto.TreeDeptDTO;
import com.hb0730.boot.admin.project.system.dept.model.entity.DeptEntity;
import com.hb0730.boot.admin.project.system.dept.model.query.DeptParams;
import com.hb0730.boot.admin.project.system.dept.service.IDeptService;
import com.hb0730.commons.lang.collection.CollectionUtils;
import com.hb0730.commons.spring.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import java.util.LinkedHashSet;
import java.util.List;
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
    public DeptDTO findById(@Nonnull Long id) {
        Assert.notNull(id, "id不为空" );
        DeptEntity entity = super.getById(id);
        return BeanUtils.transformFrom(entity, DeptDTO.class);
    }

    @Override
    public Set<TreeDeptDTO> buildTree(@Nonnull List<DeptDTO> deptList) {
        Set<TreeDeptDTO> trees = new LinkedHashSet<>();
        Set<TreeDeptDTO> depts = new LinkedHashSet<>();
        List<String> deptNames = deptList.stream().map(DeptDTO::getName).collect(Collectors.toList());
        List<TreeDeptDTO> treeDeptList = BeanUtils.transformFromInBatch(deptList, TreeDeptDTO.class);
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
        if (CollectionUtils.isEmpty(trees)) {
            trees = depts;
        }
        return trees;
    }
}
