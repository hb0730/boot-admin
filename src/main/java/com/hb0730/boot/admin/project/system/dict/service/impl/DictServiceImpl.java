package com.hb0730.boot.admin.project.system.dict.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import com.hb0730.boot.admin.commons.constant.RedisConstant;
import com.hb0730.boot.admin.commons.utils.DictCacheUtils;
import com.hb0730.boot.admin.commons.utils.QueryWrapperUtils;
import com.hb0730.boot.admin.domain.service.impl.SuperBaseServiceImpl;
import com.hb0730.boot.admin.event.dict.DictEvent;
import com.hb0730.boot.admin.exceptions.BusinessException;
import com.hb0730.boot.admin.project.system.dict.mapper.IDictMapper;
import com.hb0730.boot.admin.project.system.dict.model.dto.DictDTO;
import com.hb0730.boot.admin.project.system.dict.model.entity.DictEntity;
import com.hb0730.boot.admin.project.system.dict.model.entity.DictEntryEntity;
import com.hb0730.boot.admin.project.system.dict.model.query.DictParams;
import com.hb0730.boot.admin.project.system.dict.model.vo.DictVO;
import com.hb0730.boot.admin.project.system.dict.service.IDictEntryService;
import com.hb0730.boot.admin.project.system.dict.service.IDictService;
import com.hb0730.commons.lang.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 数据字典  服务实现类
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Service
@RequiredArgsConstructor
public class DictServiceImpl extends SuperBaseServiceImpl<Long, DictParams, DictDTO, DictEntity, IDictMapper> implements IDictService {
    private final IDictEntryService entryService;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public boolean save(DictEntity entity) {
        verify(entity, false);
        boolean result = super.save(entity);
        eventPublisher.publishEvent(new DictEvent(this));
        return result;
    }

    @Override
    public boolean updateById(DictEntity entity) {
        verify(entity, true);
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
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        boolean result = super.removeByIds(idList);
        eventPublisher.publishEvent(new DictEvent(this));
        return result;
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

    @Override
    public void updateCache() {
        QueryWrapper<DictEntryEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DictEntryEntity.IS_ENABLED, 1);
        queryWrapper.orderByAsc(DictEntryEntity.SORT);
        List<DictEntryEntity> entities = entryService.list(queryWrapper);
        Map<Long, List<DictEntryEntity>> map = entities.parallelStream().collect(Collectors.groupingBy(DictEntryEntity::getParentId, Collectors.toList()));

        Set<Long> ids = map.keySet();
        List<DictEntity> dictEntities = super.listByIds(ids);
        Map<Long, String> dictMaps = dictEntities.parallelStream().collect(Collectors.toMap(DictEntity::getId, DictEntity::getType));

        List<DictVO> dict = Lists.newArrayList();
        for (Map.Entry<Long, List<DictEntryEntity>> entry : map.entrySet()) {
            DictVO vo = new DictVO();
            vo.setType(dictMaps.get(entry.getKey()));
            List<DictVO.DictEntryVO> entryVos = Lists.newArrayList();
            for (DictEntryEntity entity : entry.getValue()) {
                DictVO.DictEntryVO entryVo = new DictVO.DictEntryVO();
                entryVo.setLabel(entity.getName());
                entryVo.setValue(entity.getValue());
                entryVos.add(entryVo);
            }
            vo.setEntry(entryVos);
            dict.add(vo);
        }
        DictCacheUtils.setCache(RedisConstant.DICT_PATENT_KEY, dict);
    }

    @Override
    public List<DictVO> getCache() {

        Optional<List<DictVO>> results = DictCacheUtils.getCacheValue(RedisConstant.DICT_PATENT_KEY);
        if (results.isPresent()) {
            return results.get();
        } else {
            this.updateCache();
            results = DictCacheUtils.getCacheValue(RedisConstant.DICT_PATENT_KEY);
            return results.orElseGet(ArrayList::new);
        }
    }
}
