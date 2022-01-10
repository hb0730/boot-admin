package com.hb0730.boot.admin.project.system.post.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hb0730.boot.admin.commons.utils.QueryWrapperUtils;
import com.hb0730.boot.admin.domain.service.impl.SuperBaseServiceImpl;
import com.hb0730.boot.admin.exceptions.BusinessException;
import com.hb0730.boot.admin.project.system.post.mapper.IPostMapper;
import com.hb0730.boot.admin.project.system.post.model.dto.PostDTO;
import com.hb0730.boot.admin.project.system.post.model.dto.PostExcelDTO;
import com.hb0730.boot.admin.project.system.post.model.entity.PostEntity;
import com.hb0730.boot.admin.project.system.post.model.query.PostParams;
import com.hb0730.boot.admin.project.system.post.service.IPostService;
import com.hb0730.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * 岗位  服务实现类
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Service
public class PostServiceImpl extends SuperBaseServiceImpl<Long, PostParams, PostDTO, PostEntity, IPostMapper> implements IPostService {

    @Override
    public boolean save(PostEntity entity) {
        verify(entity, false);
        return super.save(entity);
    }

    @Override
    public boolean saveBatch(Collection<PostEntity> entityList) {
        if (CollectionUtils.isEmpty(entityList)) {
            return false;
        }
        for (PostEntity entity : entityList) {
            verify(entity, false);
        }
        return super.saveBatch(entityList);
    }

    @Override
    public boolean updateById(PostEntity entity) {
        verify(entity, true);
        return super.updateById(entity);
    }

    @Override
    @Nonnull
    public QueryWrapper<PostEntity> query(@Nonnull PostParams params) {
        QueryWrapper<PostEntity> query = QueryWrapperUtils.getQuery(params);
        if (StringUtils.isNotBlank(params.getName())) {
            query.like(PostEntity.NAME, params.getName());
        }
        if (StringUtils.isNotBlank(params.getNumber())) {
            query.eq(PostEntity.NUMBER, params.getNumber());
        }
        if (Objects.nonNull(params.getIsEnabled())) {
            query.eq(PostEntity.IS_ENABLED, params.getIsEnabled());
        }
        return query;
    }

    private void verify(PostEntity entity, boolean isUpdate) {
        if (null == entity) {
            throw new BusinessException("岗位实体不为空");
        }
        String number = entity.getNumber();
        String name = entity.getName();
        if (StringUtils.isBlank(name)) {
            throw new BusinessException("岗位名称不为空");
        }
        if (StringUtils.isBlank(number)) {
            throw new BusinessException("岗位编码不为空");
        }
        LambdaQueryWrapper<PostEntity> queryWrapper = Wrappers.lambdaQuery(PostEntity.class).eq(PostEntity::getNumber, number);
        if (isUpdate) {
            queryWrapper.ne(PostEntity::getId, entity.getId());
        }
        long count = super.count(queryWrapper);
        if (isUpdate && count >= 1) {
            throw new BusinessException("编码重复:" + entity.getNumber());
        } else if (!isUpdate && count > 0) {
            throw new BusinessException("编码重复:" + entity.getNumber());
        }
    }

    @Override
    public List<PostExcelDTO> export(@Nonnull PostParams params) {
        QueryWrapper<PostEntity> query = this.query(params);
        List<PostEntity> entities = super.list(query);
        return BeanUtil.copyToList(entities, PostExcelDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean upload(Collection<PostExcelDTO> dataList) {
        if (CollectionUtils.isEmpty(dataList)) {
            return false;
        }
        List<PostEntity> entities = BeanUtil.copyToList(dataList, PostEntity.class);
        return this.saveBatch(entities);
    }
}
