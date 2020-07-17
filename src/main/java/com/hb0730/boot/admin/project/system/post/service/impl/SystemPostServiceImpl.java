package com.hb0730.boot.admin.project.system.post.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.model.constants.SystemConstants;
import com.hb0730.boot.admin.domain.service.impl.SuperBaseServiceImpl;
import com.hb0730.boot.admin.utils.PageUtils;
import com.hb0730.boot.admin.utils.QueryWrapperUtils;
import com.hb0730.boot.admin.utils.bean.BeanUtils;
import com.hb0730.boot.admin.exception.BaseException;
import com.hb0730.boot.admin.exception.file.FileUploadException;
import com.hb0730.boot.admin.project.system.post.mapper.ISystemPostMapper;
import com.hb0730.boot.admin.project.system.post.model.dto.PostExcelDto;
import com.hb0730.boot.admin.project.system.post.model.entity.SystemPostEntity;
import com.hb0730.boot.admin.project.system.post.model.vo.PostParams;
import com.hb0730.boot.admin.project.system.post.model.vo.SystemPostVO;
import com.hb0730.boot.admin.project.system.post.service.ISystemPostService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统岗位  服务实现类
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-28
 */
@Service
public class SystemPostServiceImpl extends SuperBaseServiceImpl<Long, PostParams, SystemPostVO, ISystemPostMapper, SystemPostEntity> implements ISystemPostService {


    @Override
    public Page<SystemPostVO> page(@NonNull PostParams params) {
        QueryWrapper<SystemPostEntity> query = query(params);
        Page<SystemPostEntity> page = QueryWrapperUtils.getPage(params);
        page = super.page(page, query);
        return PageUtils.toBean(page, SystemPostVO.class);
    }

    @Override
    public List<SystemPostVO> list(@NonNull PostParams params) {
        QueryWrapper<SystemPostEntity> query = query(params);
        List<SystemPostEntity> list = super.list(query);
        return BeanUtils.transformFromInBatch(list, SystemPostVO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(SystemPostEntity entity) {
        String number = entity.getNumber();
        QueryWrapper<SystemPostEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SystemPostEntity.NUMBER, number);
        int count = super.count(queryWrapper);
        if (count > 0) {
            throw new BaseException("保存失败,编码已存在");
        }
        return super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteById(@NonNull Long id) {
        UpdateWrapper<SystemPostEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set(SystemPostEntity.IS_ENABLED, SystemConstants.UN_ENABLED);
        updateWrapper.eq(SystemPostEntity.ID, id);
        super.update(updateWrapper);
        return super.removeById(id);
    }

    @Override
    public List<PostExcelDto> export(PostParams params) {
        QueryWrapper<SystemPostEntity> queryWrapper = query(params);
        List<SystemPostEntity> entities = super.list(queryWrapper);
        return BeanUtils.transformFromInBatch(entities, PostExcelDto.class);
    }

    private QueryWrapper<SystemPostEntity> getQuery(PostParams params) {
        QueryWrapper<SystemPostEntity> queryWrapper = new QueryWrapper<>();
        if (Objects.nonNull(params)) {
            if (StringUtils.isNotBlank(params.getName())) {
                queryWrapper.eq(SystemPostEntity.NAME, params.getName());
            }
            if (StringUtils.isNotBlank(params.getNumber())) {
                queryWrapper.eq(SystemPostEntity.NUMBER, params.getNumber());
            }
            if (Objects.nonNull(params.getIsEnabled())) {
                queryWrapper.eq(SystemPostEntity.IS_ENABLED, params.getIsEnabled());
            }
        }
        return queryWrapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean upload(Collection<PostExcelDto> list) {
        if (!CollectionUtils.isEmpty(list)) {
            Set<String> numbers = list.parallelStream().map(PostExcelDto::getNumber).collect(Collectors.toSet());
            QueryWrapper<SystemPostEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.in(SystemPostEntity.NUMBER, numbers);
            int count = super.count(queryWrapper);
            if (count > 0) {
                throw new FileUploadException("导入岗位失败,编码已存在");
            }
            List<SystemPostEntity> entities = BeanUtils.transformFromInBatch(list, SystemPostEntity.class);
            return saveBatch(entities);
        }
        return false;
    }

    @Override
    @NonNull
    public QueryWrapper<SystemPostEntity> query(@NonNull PostParams params) {
        QueryWrapper<SystemPostEntity> query = QueryWrapperUtils.getQuery(params);
        if (StringUtils.isNotBlank(params.getName())) {
            query.eq(SystemPostEntity.NAME, params.getName());
        }
        if (StringUtils.isNotBlank(params.getNumber())) {
            query.eq(SystemPostEntity.NUMBER, params.getNumber());
        }
        if (Objects.nonNull(params.getIsEnabled())) {
            query.eq(SystemPostEntity.IS_ENABLED, params.getIsEnabled());
        }
        return query;
    }
}
