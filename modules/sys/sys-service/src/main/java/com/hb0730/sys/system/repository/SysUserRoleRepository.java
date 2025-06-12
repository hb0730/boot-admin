package com.hb0730.sys.system.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hb0730.core.repository.BasicRepository;
import com.hb0730.sys.system.model.entity.SysUserRole;
import com.hb0730.sys.system.repository.mapper.SysUserRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2025/5/21
 */
@Service
@Slf4j
public class SysUserRoleRepository extends BasicRepository<String, SysUserRole, SysUserRoleMapper> {

    /**
     * 根据用户ID删除
     *
     * @param userId 用户ID
     * @return 是否成功
     */
    public boolean deleteByUserId(String userId) {
        return baseMapper.deleteByUserId(userId) > 0;
    }

    /**
     * 通过用户ID查询有效的角色
     *
     * @param userId 用户ID
     * @return 有效的角色列表
     */
    public List<SysUserRole> findEffectiveRolesByUserId(String userId) {
        LambdaQueryWrapper<SysUserRole> queryWrapper = Wrappers.lambdaQuery(SysUserRole.class)
                .eq(SysUserRole::getUserId, userId)
                .and(wrapper -> wrapper.isNull(SysUserRole::getEndTime)
                        .or()
                        .ge(SysUserRole::getEndTime, new Date()));

        return list(queryWrapper);
    }
}
