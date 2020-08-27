package com.hb0730.boot.admin.project.system.user.info.service;

import com.hb0730.boot.admin.domain.service.IBaseService;
import com.hb0730.boot.admin.project.system.user.info.model.entity.UserInfoEntity;
import com.hb0730.boot.admin.project.system.user.info.model.query.UserInfoParams;
import com.hb0730.boot.admin.project.system.user.info.model.vo.UserInfoVO;

/**
 * 用户信息  服务类
 *
 * @author bing_huang
 * @since 3.0.0
 */
public interface IUserInfoService extends IBaseService<Long, UserInfoParams, UserInfoVO, UserInfoEntity> {

}
