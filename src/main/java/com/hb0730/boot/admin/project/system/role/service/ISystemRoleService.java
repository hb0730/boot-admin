package com.hb0730.boot.admin.project.system.role.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.domain.service.IBaseService;
import com.hb0730.boot.admin.project.system.role.model.entity.SystemRoleEntity;
import com.hb0730.boot.admin.project.system.role.model.vo.RoleParams;
import com.hb0730.boot.admin.project.system.role.model.vo.SystemRoleVO;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * <p>
 * 系统角色  服务类
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-27
 */
public interface ISystemRoleService extends IBaseService<Long, RoleParams, SystemRoleVO, SystemRoleEntity> {

    /**
     * 列表查询
     *
     * @param params 请求参数
     * @return 列表
     */
    @Override
    List<SystemRoleVO> list(@NonNull RoleParams params);

    /**
     * 分页查询
     *
     * @param params 请求参数
     * @return 分页列表
     */
    @Override
    Page<SystemRoleVO> page(@NonNull RoleParams params);
}
