package com.hb0730.boot.admin.project.system.user.service;

import com.hb0730.boot.admin.domain.service.IBaseService;
import com.hb0730.boot.admin.project.system.user.model.dto.UserDTO;
import com.hb0730.boot.admin.project.system.user.model.entity.UserInfoEntity;
import com.hb0730.boot.admin.project.system.user.model.query.UserInfoParams;
import com.hb0730.boot.admin.project.system.user.model.dto.UserInfoDTO;

/**
 * 用户信息  服务类
 *
 * @author bing_huang
 * @since 3.0.0
 */
public interface IUserInfoService extends IBaseService<Long, UserInfoParams, UserInfoDTO, UserInfoEntity> {


    /**
     * 根据账号获取用户关联信息
     *
     * @param username 用户账号
     * @return 用户关联信息
     */
    UserDTO loadUserByUsername(String username);

}
