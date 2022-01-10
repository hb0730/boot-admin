package com.hb0730.boot.admin.project.system.dict.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hb0730.boot.admin.commons.utils.QueryWrapperUtils;
import com.hb0730.boot.admin.domain.service.impl.SuperBaseServiceImpl;
import com.hb0730.boot.admin.event.dict.DictEvent;
import com.hb0730.boot.admin.project.system.dict.mapper.IDictEntryMapper;
import com.hb0730.boot.admin.project.system.dict.model.dto.DictEntryDTO;
import com.hb0730.boot.admin.project.system.dict.model.entity.DictEntryEntity;
import com.hb0730.boot.admin.project.system.dict.model.query.DictEntryParams;
import com.hb0730.boot.admin.project.system.dict.service.IDictEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

/**
 * 数据字典项  服务实现类
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Service
@RequiredArgsConstructor
public class DictEntryServiceImpl extends SuperBaseServiceImpl<Long, DictEntryParams, DictEntryDTO, DictEntryEntity, IDictEntryMapper> implements IDictEntryService {
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public QueryWrapper<DictEntryEntity> query(@Nonnull DictEntryParams params) {
        QueryWrapper<DictEntryEntity> query = QueryWrapperUtils.getQuery(params);
        if (Objects.nonNull(params.getParentId())) {
            query.eq(DictEntryEntity.PARENT_ID, params.getParentId());
        }
        return query;
    }

    @Override
    public boolean save(DictEntryEntity entity) {
        boolean result = super.save(entity);
        eventPublisher.publishEvent(new DictEvent(this));
        return result;
    }

    @Override
    public boolean saveBatch(Collection<DictEntryEntity> entityList) {
        boolean result = super.saveBatch(entityList);
        eventPublisher.publishEvent(new DictEvent(this));
        return result;
    }

    @Override
    public boolean updateById(DictEntryEntity entity) {
        boolean result = super.updateById(entity);
        eventPublisher.publishEvent(new DictEvent(this));
        return result;
    }

    @Override
    public boolean removeById(Serializable id) {
        boolean result = super.removeById(id);
        eventPublisher.publishEvent(new DictEvent(this));
        return result;
    }

    @Override
    public boolean removeByIds(Collection<?> idList) {
        boolean result = super.removeByIds(idList);
        eventPublisher.publishEvent(new DictEvent(this));
        return result;
    }
}
