package com.hb0730.boot.admin.project.system.dict.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hb0730.boot.admin.commons.constant.ActionEnum;
import com.hb0730.boot.admin.commons.constant.RedisConstants;
import com.hb0730.boot.admin.commons.constant.SystemConstants;
import com.hb0730.boot.admin.commons.constant.VueConstants;
import com.hb0730.boot.admin.commons.domain.service.SuperBaseServiceImpl;
import com.hb0730.boot.admin.commons.utils.PageUtils;
import com.hb0730.boot.admin.commons.utils.QueryWrapperUtils;
import com.hb0730.boot.admin.commons.utils.bean.BeanUtils;
import com.hb0730.boot.admin.commons.utils.cache.DictCacheUtils;
import com.hb0730.boot.admin.commons.utils.validator.ValidatorUtils;
import com.hb0730.boot.admin.event.dict.DictEvent;
import com.hb0730.boot.admin.project.system.dict.mapper.ISystemDictMapper;
import com.hb0730.boot.admin.project.system.dict.model.entity.SystemDictEntity;
import com.hb0730.boot.admin.project.system.dict.model.vo.DictParams;
import com.hb0730.boot.admin.project.system.dict.model.vo.SystemDictVO;
import com.hb0730.boot.admin.project.system.dict.service.ISystemDictService;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 数据字段类型  服务实现类
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-30
 */
@Service
public class SystemDictServiceImpl extends SuperBaseServiceImpl<DictParams, SystemDictVO, ISystemDictMapper, SystemDictEntity> implements ISystemDictService {
    @CreateCache(cacheType = CacheType.REMOTE, area = RedisConstants.REDIS_JETCACHE_AREA, name = RedisConstants.REDIS_JETCACHE_NAME_DICT)
    private Cache<String, Map<String, List<Map<String, Object>>>> cache;
    private final ApplicationContext applicationContext;

    public SystemDictServiceImpl(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(SystemDictEntity entity) {
        entity.setParentId(entity.getParentId() == null ? SystemConstants.PARENT_ID : entity.getParentId());
        boolean save = super.save(entity);
        applicationContext.publishEvent(new DictEvent(this, ActionEnum.SAVE));
        return save;
    }


    @Override
    public Page<SystemDictVO> page(Long parentId, @NonNull DictParams params) {
        parentId = parentId == null ? SystemConstants.PARENT_ID : parentId;
        QueryWrapper<SystemDictEntity> query = query(params);
        query.eq(SystemDictEntity.PARENT_ID, parentId);
        Page<SystemDictEntity> page = QueryWrapperUtils.getPage(params);
        page = super.page(page, query);
        return PageUtils.toBean(page, SystemDictVO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(@NonNull Long id, @NonNull SystemDictVO vo) {
        SystemDictEntity entity = super.getById(id);
        BeanUtils.updateProperties(vo, entity);
        updateTypeByParentId(id, entity.getNumber());
        boolean b = super.updateById(entity);
        applicationContext.publishEvent(new DictEvent(this, ActionEnum.UPDATE));
        return b;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(@NonNull Long id) {
        UpdateWrapper<SystemDictEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set(SystemDictEntity.IS_ENABLED, SystemConstants.UN_ENABLED);
        updateWrapper.eq(SystemDictEntity.ID, id);
        super.update(updateWrapper);
        boolean b = super.removeById(id);
        applicationContext.publishEvent(new DictEvent(this, ActionEnum.DELETE));
        return b;
    }


    @Override
    public Map<String, List<Map<String, Object>>> getDictForMap() {
        Map<String, List<Map<String, Object>>> cache = null;
        try {
            cache = DictCacheUtils.getCache();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (CollectionUtils.isEmpty(cache)) {
            cache();
            cache = DictCacheUtils.getCache();
        }
        return cache;
    }

    @Override
    public void cache() {
        // group 分出类型
        QueryWrapper<SystemDictEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.groupBy(SystemDictEntity.DICT_TYPE);
        queryWrapper.eq(SystemDictEntity.IS_ENABLED, SystemConstants.ENABLED);
        queryWrapper.ne(SystemDictEntity.PARENT_ID, SystemConstants.PARENT_ID);
        queryWrapper.select(SystemDictEntity.DICT_TYPE);
        List<SystemDictEntity> entities = super.list(queryWrapper);
        Map<String, List<Map<String, Object>>> maps = Maps.newHashMapWithExpectedSize(entities.size());
        if (!CollectionUtils.isEmpty(entities)) {
            entities.forEach((type) -> {
                // 类型对应的值
                QueryWrapper<SystemDictEntity> q1 = new QueryWrapper<>();
                q1.eq(SystemDictEntity.DICT_TYPE, type.getDictType());
                q1.eq(SystemDictEntity.IS_ENABLED, SystemConstants.ENABLED);
                q1.select(SystemDictEntity.DICT_LABEL, SystemDictEntity.DICT_VALUE);
                List<SystemDictEntity> valueEntity = super.list(q1);
                List<Map<String, Object>> list = Lists.newArrayListWithExpectedSize(valueEntity.size());
                if (!CollectionUtils.isEmpty(valueEntity)) {
                    valueEntity.forEach((value) -> {
                        Map<String, Object> map = Maps.newHashMap();
                        map.put(VueConstants.LABEL, value.getDictLabel());
                        map.put(VueConstants.VALUE, value.getDictValue());
                        list.add(map);
                    });
                }
                maps.put(type.getDictType(), list);
            });
        }
        cache.put(DictCacheUtils.getDictKey("-1"), maps);

    }

    @Override
    @Deprecated
    public Page<SystemDictVO> page(@NotNull @NonNull DictParams params) {
        return null;
    }

    @Override
    @Deprecated
    public List<SystemDictVO> list(@NonNull DictParams params) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(@NonNull SystemDictVO vo) {
        ValidatorUtils.validate(vo);
        SystemDictEntity entity = vo.convertTo();
        return this.save(entity);
    }

    @Override
    @NonNull
    public QueryWrapper<SystemDictEntity> query(@NonNull DictParams params) {
        QueryWrapper<SystemDictEntity> query = QueryWrapperUtils.getQuery(params);
        if (StringUtils.isNotBlank(params.getName())) {
            query.eq(SystemDictEntity.NAME, params.getName());
        }
        if (StringUtils.isNotBlank(params.getNumber())) {
            query.eq(SystemDictEntity.NUMBER, params.getNumber());
        }
        if (StringUtils.isNotBlank(params.getIsEnabled())) {
            query.eq(SystemDictEntity.IS_ENABLED, params.getIsEnabled());
        }

        return query;
    }

    /**
     * 根据父id更新类型
     *
     * @param parentId 父id
     * @param type     类型
     */
    private void updateTypeByParentId(Long parentId, String type) {
        if (Objects.isNull(parentId)) {
            return;
        }
        UpdateWrapper<SystemDictEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set(SystemDictEntity.DICT_TYPE, type);
        updateWrapper.eq(SystemDictEntity.PARENT_ID, parentId);
        super.update(updateWrapper);
    }
}
