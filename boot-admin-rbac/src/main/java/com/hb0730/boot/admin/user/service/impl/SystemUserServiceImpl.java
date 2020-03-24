package com.hb0730.boot.admin.user.service.impl;

import com.hb0730.boot.admin.user.model.entity.SystemUserEntity;
import com.hb0730.boot.admin.user.mapper.SystemUserMapper;
import com.hb0730.boot.admin.user.service.SystemUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户  服务实现类
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-24
 */
@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUserEntity> implements SystemUserService {

}
