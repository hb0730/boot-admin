package com.hb0730.boot.admin.project.system.dict.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hb0730.boot.admin.commons.utils.QueryWrapperUtils;
import com.hb0730.boot.admin.domain.service.impl.SuperBaseServiceImpl;
import com.hb0730.boot.admin.exceptions.BusinessException;
import com.hb0730.boot.admin.project.system.dict.mapper.IDictMapper;
import com.hb0730.boot.admin.project.system.dict.model.dto.DictDTO;
import com.hb0730.boot.admin.project.system.dict.model.entity.DictEntity;
import com.hb0730.boot.admin.project.system.dict.model.query.DictParams;
import com.hb0730.boot.admin.project.system.dict.service.IDictService;
import com.hb0730.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;

/**
 * 数据字典  服务实现类
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Service
public class DictServiceImpl extends SuperBaseServiceImpl<Long, DictParams, DictDTO, DictEntity, IDictMapper> implements IDictService {

    @Override
    public boolean save(DictEntity entity) {
        verify(entity, false);
        return super.save(entity);
    }

    @Override
    public boolean updateById(DictEntity entity) {
        verify(entity, true);
        return super.updateById(entity);
    }

    @Override
    public QueryWrapper<DictEntity> query(@Nonnull DictParams params) {
        QueryWrapper<DictEntity> query = QueryWrapperUtils.getQuery(params);
        if (StringUtils.isNotBlank(params.getName())) {
            query.like(DictEntity.NAME, params.getName());
        }
        if (StringUtils.isNotBlank(params.getType())) {
            query.eq(DictEntity.TYPE, params.getType());
        }
        return query;
    }

    private void verify(DictEntity entity, boolean isUpdate) {
        if (null == entity) {
            throw new BusinessException("数据字典信息为空");
        }
        String type = entity.getType();
        LambdaQueryWrapper<DictEntity> queryWrapper = Wrappers.lambdaQuery(DictEntity.class)
                .eq(DictEntity::getType, type);
        if (isUpdate) {
            queryWrapper.ne(DictEntity::getId, entity.getId());
        }
        int count = super.count(queryWrapper);
        if (isUpdate && count > 0) {
            throw new BusinessException("数据字典类型已存在");
        } else if (!isUpdate && count > 0) {
            throw new BusinessException("数据字典类型已存在");
        }

    }
}
