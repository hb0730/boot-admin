package com.hb0730.boot.admin.project.system.dept.service;

import com.hb0730.boot.admin.domain.service.IBaseService;
import com.hb0730.boot.admin.project.system.dept.model.dto.DeptDTO;
import com.hb0730.boot.admin.project.system.dept.model.dto.TreeDeptDTO;
import com.hb0730.boot.admin.project.system.dept.model.entity.DeptEntity;
import com.hb0730.boot.admin.project.system.dept.model.query.DeptParams;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Set;

/**
 * 部门  服务类
 *
 * @author bing_huang
 * @since 3.0.0
 */
public interface IDeptService extends IBaseService<Long, DeptParams, DeptDTO, DeptEntity> {

    /**
     * 根据id查询
     *
     * @param id id
     * @return 部门
     */
    DeptDTO findById(@Nonnull Long id);

    /**
     * 构建成树形
     *
     * @param deptList 组织集
     * @return 组织树形
     */
    Set<TreeDeptDTO> buildTree(@Nonnull List<DeptDTO> deptList);

}
