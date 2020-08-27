package com.hb0730.boot.admin.project.system.user.info.service.impl;

import com.hb0730.boot.admin.domain.service.impl.SuperBaseServiceImpl;
import com.hb0730.boot.admin.project.system.user.info.mapper.IUserInfoMapper;
import com.hb0730.boot.admin.project.system.user.info.model.entity.UserInfoEntity;
import com.hb0730.boot.admin.project.system.user.info.model.query.UserInfoParams;
import com.hb0730.boot.admin.project.system.user.info.model.vo.UserInfoVO;
import com.hb0730.boot.admin.project.system.user.info.service.IUserInfoService;
import org.springframework.stereotype.Service;

/**
 * 用户信息  服务实现类
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Service
public class UserInfoServiceImpl extends SuperBaseServiceImpl<Long, UserInfoParams, UserInfoVO, UserInfoEntity, IUserInfoMapper> implements IUserInfoService {

}
