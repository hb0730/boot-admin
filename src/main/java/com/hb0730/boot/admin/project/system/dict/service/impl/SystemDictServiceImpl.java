package com.hb0730.boot.admin.project.system.dict.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hb0730.boot.admin.commons.constant.SystemConstants;
import com.hb0730.boot.admin.commons.constant.VueConstants;
import com.hb0730.boot.admin.commons.domain.service.BaseServiceImpl;
import com.hb0730.boot.admin.commons.utils.PageUtils;
import com.hb0730.boot.admin.commons.utils.QueryWrapperUtils;
import com.hb0730.boot.admin.commons.utils.bean.BeanUtils;
import com.hb0730.boot.admin.project.system.dict.mapper.ISystemDictMapper;
import com.hb0730.boot.admin.project.system.dict.model.entity.SystemDictEntity;
import com.hb0730.boot.admin.project.system.dict.model.vo.DictParams;
import com.hb0730.boot.admin.project.system.dict.model.vo.SystemDictVO;
import com.hb0730.boot.admin.project.system.dict.service.ISystemDictService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据字段类型  服务实现类
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-30
 */
@Service
public class SystemDictServiceImpl extends BaseServiceImpl<ISystemDictMapper, SystemDictEntity> implements ISystemDictService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(SystemDictEntity entity) {
        entity.setParentId(entity.getParentId() == null ? SystemConstants.PARENT_ID : entity.getParentId());
        return super.save(entity);
    }


    @Override
    public Page<SystemDictVO> page(Long parentId, @NonNull DictParams params) {
        parentId = parentId == null ? SystemConstants.PARENT_ID : parentId;
        @NotNull QueryWrapper<SystemDictEntity> query = query(params);
        query.eq(SystemDictEntity.PARENT_ID, parentId);
        @NotNull Page<SystemDictEntity> page = QueryWrapperUtils.getPage(params);
        page = super.page(page, query);
        return PageUtils.toBean(page, SystemDictVO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(@NonNull Long id, SystemDictVO vo) {
        SystemDictEntity entity = super.getById(id);
        BeanUtils.updateProperties(vo, entity);
        return super.updateById(entity);
    }

    @Override
    @NonNull
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(@NonNull Long id) {
        UpdateWrapper<SystemDictEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set(SystemDictEntity.IS_ENABLED, SystemConstants.NOT_USE);
        updateWrapper.eq(SystemDictEntity.ID, id);
        super.update(updateWrapper);
        return super.removeById(id);
    }

    @Override
    public Map<String, List> getDictForMap() {
        // group 分出类型
        QueryWrapper<SystemDictEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.groupBy(SystemDictEntity.DICT_TYPE);
        queryWrapper.eq(SystemDictEntity.IS_ENABLED, SystemConstants.USE);
        queryWrapper.ne(SystemDictEntity.PARENT_ID, SystemConstants.PARENT_ID);
        queryWrapper.select(SystemDictEntity.DICT_TYPE);
        List<SystemDictEntity> entities = super.list(queryWrapper);
        Map<String, List> maps = Maps.newHashMapWithExpectedSize(entities.size());
        if (!CollectionUtils.isEmpty(entities)) {
            entities.forEach((type) -> {
                // 类型对应的值
                QueryWrapper<SystemDictEntity> q1 = new QueryWrapper<>();
                q1.eq(SystemDictEntity.DICT_TYPE, type.getDictType());
                q1.eq(SystemDictEntity.IS_ENABLED, SystemConstants.USE);
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
        return maps;
    }

    @Override
    public @NotNull QueryWrapper<SystemDictEntity> query(@NotNull DictParams params) {
        @NotNull QueryWrapper<SystemDictEntity> query = QueryWrapperUtils.getQuery(params);
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
}
