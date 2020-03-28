package com.hb0730.boot.admin.project.post.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hb0730.boot.admin.commons.constant.SystemConstants;
import com.hb0730.boot.admin.project.post.mapper.ISystemPostMapper;
import com.hb0730.boot.admin.project.post.model.entity.SystemPostEntity;
import com.hb0730.boot.admin.project.post.service.ISystemPostService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 系统岗位  服务实现类
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-28
 */
@Service
public class SystemPostServiceImpl extends ServiceImpl<ISystemPostMapper, SystemPostEntity> implements ISystemPostService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteById(@NonNull Long id) {
        UpdateWrapper<SystemPostEntity> updateWrapper=new UpdateWrapper<>();
        updateWrapper.set(SystemPostEntity.IS_ENABLED, SystemConstants.NOT_USE);
        updateWrapper.eq(SystemPostEntity.ID,id);
        super.update(updateWrapper);
        return super.removeById(id);
    }
}
