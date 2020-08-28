package com.hb0730.boot.admin.project.system.user.info.service.impl;

import com.hb0730.boot.admin.domain.service.impl.SuperBaseServiceImpl;
import com.hb0730.boot.admin.project.system.user.account.model.entity.UserAccountEntity;
import com.hb0730.boot.admin.project.system.user.account.service.IUserAccountService;
import com.hb0730.boot.admin.project.system.user.info.mapper.IUserInfoMapper;
import com.hb0730.boot.admin.project.system.user.info.model.dto.UserDTO;
import com.hb0730.boot.admin.project.system.user.info.model.entity.UserInfoEntity;
import com.hb0730.boot.admin.project.system.user.info.model.query.UserInfoParams;
import com.hb0730.boot.admin.project.system.user.info.model.vo.UserInfoVO;
import com.hb0730.boot.admin.project.system.user.info.service.IUserInfoService;
import com.hb0730.commons.spring.BeanUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 用户信息  服务实现类
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl extends SuperBaseServiceImpl<Long, UserInfoParams, UserInfoVO, UserInfoEntity, IUserInfoMapper> implements IUserInfoService {
    @Getter
    private final IUserAccountService accountService;

    @Override
    public UserDTO loadUserByUsername(String username) {
        UserAccountEntity accountEntity = accountService.userAccountByUsername(username);
        if (null == accountEntity) {
            return null;
        }
        Long userId = accountEntity.getUserId();
        UserInfoEntity entity = super.getById(userId);
        UserDTO user = BeanUtils.transformFrom(entity, UserDTO.class);
        assert user != null;
        user.setUsername(accountEntity.getUsername());
        user.setPassword(accountEntity.getPassword());
        return user;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getThis() {
        return (T) this;
    }
}
