package com.hb0730.boot.admin.project.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hb0730.boot.admin.commons.web.exception.BaseException;
import com.hb0730.boot.admin.commons.web.utils.SecurityUtils;
import com.hb0730.boot.admin.project.user.mapper.SystemUserMapper;
import com.hb0730.boot.admin.project.user.model.entity.SystemUserEntity;
import com.hb0730.boot.admin.project.user.service.ISystemUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 系统用户  服务实现类
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-24
 */
@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUserEntity> implements ISystemUserService {

    @Override
    public boolean save(SystemUserEntity entity) {
        String username = entity.getUsername();
        if (StringUtils.isBlank(username)) {
            throw new BaseException("用户账号为空");
        }
        QueryWrapper<SystemUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        List<SystemUserEntity> entities = list(queryWrapper);
        if (!CollectionUtils.isEmpty(entities)) {
            throw new BaseException("用户账号已存在");
        }
        String password = entity.getPassword();
        if (StringUtils.isBlank(password)) {
            throw new BaseException("用户密码为空");
        }
        String encryptPassword = SecurityUtils.encryptPassword(password);
        entity.setPassword(encryptPassword);
        return super.save(entity);
    }
}
