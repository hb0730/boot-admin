package com.hb0730.boot.admin.project.system.user.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hb0730.boot.admin.commons.utils.spring.SpringUtils;
import com.hb0730.boot.admin.project.system.user.mapper.SystemUserMapper;
import com.hb0730.boot.admin.project.system.user.model.entity.SystemUserEntity;
import org.springframework.lang.NonNull;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class UserUtil {
    /**
     * <p>
     * 根据用户账号获取用户信息
     * </p>
     *
     * @param userName 用户账号
     * @return 用户信息
     */
    public static SystemUserEntity getUserByUsername(@NonNull String userName) {
        SystemUserMapper mapper = SpringUtils.getBean(SystemUserMapper.class);
        QueryWrapper<SystemUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SystemUserEntity.USERNAME, userName);
        return mapper.selectOne(queryWrapper);
    }
}
