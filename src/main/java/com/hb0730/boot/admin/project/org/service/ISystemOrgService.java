package com.hb0730.boot.admin.project.org.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hb0730.boot.admin.project.org.model.entity.SystemOrgEntity;
import com.hb0730.boot.admin.project.org.model.vo.SystemOrgVO;
import com.hb0730.boot.admin.project.org.model.vo.TreeOrgVO;
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
public interface ISystemOrgService extends IService<SystemOrgEntity> {

    /**
     * <p>
     * 根据父id获取组织信息
     * </p>
     *
     * @param parentId 父id
     * @return 组织信息
     */
    List<SystemOrgVO> getOrgByParentId(@NonNull Long parentId);

    /**
     * <p>
     * 获取全部组织树
     * </p>
     *
     * @return 组织树
     */
    List<TreeOrgVO> getTreeAll();

    /**
     * <p>
     * 根据id获取组织树
     * </p>
     *
     * @param id id
     * @return 组织树
     */
    TreeOrgVO getTreeById(@NonNull Long id);
}
