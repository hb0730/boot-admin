package com.hb0730.boot.admin.project.role.org.service;

import com.hb0730.boot.admin.project.role.org.model.entity.SystemRoleOrgEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * <p>
 * 角色组织(数据范围)  服务类
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-27
 */
public interface ISystemRoleOrgService extends IService<SystemRoleOrgEntity> {

    /**
     * <p>
     * 保存数据范围
     * </p>
     *
     * @param roleId 角色id
     * @param orgIds 数据范围id
     * @return 是否成功
     */
    boolean saveOrgIdsByRoleId(@NonNull Long roleId, List<Long> orgIds);
}
