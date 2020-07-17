package com.hb0730.boot.admin.project.system.org.service;

import com.hb0730.boot.admin.domain.service.IBaseService;
import com.hb0730.boot.admin.project.system.org.model.entity.SystemOrgEntity;
import com.hb0730.boot.admin.project.system.org.model.vo.OrgParams;
import com.hb0730.boot.admin.project.system.org.model.vo.SystemOrgVO;
import com.hb0730.boot.admin.project.system.org.model.vo.TreeOrgVO;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * <p>
 * 系统组织  服务类
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-26
 */
public interface ISystemOrgService extends IBaseService<Long, OrgParams, SystemOrgVO, SystemOrgEntity> {

    /**
     * <p>
     * 根据父id获取组织信息
     * </p>
     *
     * @param parentId 父id
     * @param isAll    是否查询全部(包含禁用)
     * @return 组织信息
     */
    List<SystemOrgVO> getOrgByParentId(@NonNull Long parentId, @NonNull Integer isAll);

    /**
     * <p>
     * 获取全部组织树
     * </p>
     *
     * @param isAll 是否查询全部(包含禁用)
     * @return 组织树
     */
    List<TreeOrgVO> getTreeAll(Integer isAll);

    /**
     * <p>
     * 根据id获取组织树
     * </p>
     *
     * @param id    id
     * @param isAll 是否查询全部(包含禁用)
     * @return 组织树
     */
    TreeOrgVO getTreeById(@NonNull Long id, @NonNull Integer isAll);
}
