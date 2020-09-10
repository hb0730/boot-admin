package com.hb0730.boot.admin.project.system.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hb0730.boot.admin.commons.enums.ResponseStatusEnum;
import com.hb0730.boot.admin.commons.utils.PasswordSecurityUtils;
import com.hb0730.boot.admin.domain.service.impl.SuperBaseServiceImpl;
import com.hb0730.boot.admin.exceptions.AccountException;
import com.hb0730.boot.admin.exceptions.BusinessException;
import com.hb0730.boot.admin.project.system.user.mapper.IUserAccountMapper;
import com.hb0730.boot.admin.project.system.user.model.dto.UserAccountDTO;
import com.hb0730.boot.admin.project.system.user.model.entity.UserAccountEntity;
import com.hb0730.boot.admin.project.system.user.model.query.UserAccountParams;
import com.hb0730.boot.admin.project.system.user.service.IUserAccountService;
import com.hb0730.boot.admin.security.utils.SecurityUtils;
import com.hb0730.commons.lang.StringUtils;
import com.hb0730.commons.spring.ValidatorUtils;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

/**
 * <p>
 * 用户账号  服务实现类
 * </p>
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Service
public class UserAccountServiceImpl
        extends SuperBaseServiceImpl<Long, UserAccountParams, UserAccountDTO, UserAccountEntity, IUserAccountMapper> implements IUserAccountService {

    @Override
    public boolean save(@NonNull UserAccountDTO dto) {
        ValidatorUtils.validate(dto);
        String username = dto.getUsername();
        Long userId = dto.getUserId();
        UserAccountEntity entity = userAccountByUserId(userId);
        if (null != entity) {
            throw new BusinessException("用户id已绑定账号,请勿重新绑定");
        }
        entity = findUserAccountByUsername(username);
        if (null != entity) {
            throw new BusinessException("用户账号已存在，请重新设置");
        }
        entity = dto.convertTo();

        return super.save(entity);
    }

    @Override
    @Nullable
    public UserAccountEntity userAccountByUserId(Long userId) {
        if (Objects.isNull(userId)) {
            return null;
        }
        QueryWrapper<UserAccountEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(UserAccountEntity.USER_ID, userId);
        return super.getOne(queryWrapper, false);
    }


    @Override
    @Nullable
    public UserAccountEntity findUserAccountByUsername(String username) {
        if (StringUtils.isBlank(username)) {
            return null;
        }
        QueryWrapper<UserAccountEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(UserAccountEntity.USERNAME, username);
        return super.getOne(queryWrapper, false);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updatePassword(@NonNull Long userId, @NonNull String oldPassword, @NonNull String newPassword) {
        LambdaQueryWrapper<UserAccountEntity> query = Wrappers.lambdaQuery();
        query.eq(UserAccountEntity::getUserId, userId);
        UserAccountEntity accountEntity = super.getOne(query, false);
        if (null == accountEntity) {
            throw new AccountException(ResponseStatusEnum.USER_NAME_NOT_FONT, "根据用户id查询不到账号信息");
        }
        String password = accountEntity.getPassword();
        if (!PasswordSecurityUtils.matches(SecurityUtils.getPasswordEncoder(), oldPassword, password)) {
            throw new AccountException(ResponseStatusEnum.USER_PASSWORD_ERROR, "原密码不正确");
        }
        newPassword = PasswordSecurityUtils.encode(SecurityUtils.getPasswordEncoder(), newPassword);
        accountEntity.setPassword(newPassword);
        return super.updateById(accountEntity);
    }

    @Override
    public boolean updatePassword(@Nonnull Long userId, @Nonnull String password) {
        LambdaQueryWrapper<UserAccountEntity> query = Wrappers.lambdaQuery();
        query.eq(UserAccountEntity::getUserId, userId);
        UserAccountEntity accountEntity = super.getOne(query, false);
        if (null == accountEntity) {
            throw new AccountException(ResponseStatusEnum.USER_NAME_NOT_FONT, "根据用户id查询不到账号信息");
        }
        password = PasswordSecurityUtils.encode(SecurityUtils.getPasswordEncoder(), password);
        accountEntity.setPassword(password);
        return super.updateById(accountEntity);
    }

    @Override
    public boolean removeByUserId(@Nonnull Serializable userId) {
        Assert.notNull(userId, "用户id不为空");
        LambdaQueryWrapper<UserAccountEntity> queryWrapper = Wrappers
                .lambdaQuery(UserAccountEntity.class)
                .eq(UserAccountEntity::getUserId, userId);
        return super.remove(queryWrapper);
    }

    @Override
    public boolean removeByUserIds(@Nonnull Collection<? extends Serializable> userIds) {
        Assert.notEmpty(userIds, "用户id不为空");
        LambdaQueryWrapper<UserAccountEntity> queryWrapper = Wrappers
                .lambdaQuery(UserAccountEntity.class)
                .in(UserAccountEntity::getUserId, userIds);
        return super.remove(queryWrapper);
    }
}
