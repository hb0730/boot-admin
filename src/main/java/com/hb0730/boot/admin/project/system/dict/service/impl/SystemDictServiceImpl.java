package com.hb0730.boot.admin.project.system.dict.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hb0730.boot.admin.commons.constant.SystemConstants;
import com.hb0730.boot.admin.commons.constant.VueConstants;
import com.hb0730.boot.admin.commons.utils.bean.BeanUtils;
import com.hb0730.boot.admin.commons.utils.PageInfoUtil;
import com.hb0730.boot.admin.project.system.dict.mapper.ISystemDictMapper;
import com.hb0730.boot.admin.project.system.dict.model.entity.SystemDictEntity;
import com.hb0730.boot.admin.project.system.dict.model.vo.DictParams;
import com.hb0730.boot.admin.project.system.dict.model.vo.SystemDictVO;
import com.hb0730.boot.admin.project.system.dict.service.ISystemDictService;
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
public class SystemDictServiceImpl extends ServiceImpl<ISystemDictMapper, SystemDictEntity> implements ISystemDictService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(SystemDictEntity entity) {
        entity.setParentId(entity.getParentId() == null ? SystemConstants.PARENT_ID : entity.getParentId());
        return super.save(entity);
    }

    @Override
    public PageInfo<SystemDictVO> getPageDict(Long parentId, Integer page, Integer pageSize, DictParams params) {
        parentId = parentId == null ? SystemConstants.PARENT_ID : parentId;
        page = page == null ? 1 : page;
        pageSize = pageSize == null ? 10 : pageSize;
        PageHelper.startPage(page, pageSize);
        QueryWrapper<SystemDictEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SystemDictEntity.PARENT_ID, parentId);
        if (!Objects.isNull(params)) {
            if (!Objects.isNull(params.getIsAll()) && !Objects.equals(params.getIsAll(), SystemConstants.IS_ALL)) {
                queryWrapper.eq(SystemDictEntity.IS_ENABLED, params.getIsAll());
            }
        }
        List<SystemDictEntity> entities = super.list(queryWrapper);
        PageInfo<SystemDictEntity> pageInfo = new PageInfo<>(entities);
        return PageInfoUtil.toBean(pageInfo, SystemDictVO.class);
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
}
