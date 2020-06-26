package com.hb0730.boot.admin.project.system.role.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hb0730.boot.admin.commons.domain.service.IBaseService;
import com.hb0730.boot.admin.project.system.role.model.entity.SystemRoleEntity;
import com.hb0730.boot.admin.project.system.role.model.vo.RoleParams;
import com.hb0730.boot.admin.project.system.role.model.vo.SystemRoleVO;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 系统角色  服务类
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-27
 */
public interface ISystemRoleService extends IService<SystemRoleEntity>, IBaseService<RoleParams, SystemRoleEntity> {

    /**
     * 列表查询
     *
     * @param params 请求参数
     * @return 列表
     */
    List<SystemRoleVO> list(@NotNull RoleParams params);

    /**
     * 分页查询
     *
     * @param params 请求参数
     * @return 分页列表
     */
    Page<SystemRoleVO> page(@NotNull RoleParams params);
}
