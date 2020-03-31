package com.hb0730.boot.admin.project.dict.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hb0730.boot.admin.commons.constant.SystemConstants;
import com.hb0730.boot.admin.commons.utils.BeanUtils;
import com.hb0730.boot.admin.commons.utils.PageInfoUtil;
import com.hb0730.boot.admin.commons.web.exception.BaseException;
import com.hb0730.boot.admin.project.dict.mapper.ISystemDictMapper;
import com.hb0730.boot.admin.project.dict.model.entity.SystemDictEntity;
import com.hb0730.boot.admin.project.dict.model.vo.DictParams;
import com.hb0730.boot.admin.project.dict.model.vo.SystemDictVO;
import com.hb0730.boot.admin.project.dict.service.ISystemDictService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
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
        verify(entity);
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
        verify(entity);
        return super.updateById(entity);
    }

    @Override
    @NonNull
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(@NonNull Long id) {
        UpdateWrapper<SystemDictEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set(SystemDictEntity.IS_ENABLED, SystemConstants.NOT_USE);
        updateWrapper.eq(SystemDictEntity.ID,id);
        super.update(updateWrapper);
        return super.removeById(id);
    }


    private void verify(@NonNull SystemDictEntity entity) {
        if (Objects.equals(entity.getParentId(), SystemConstants.PARENT_ID)) {
            if (StringUtils.isBlank(entity.getNumber())) {
                throw new BaseException("字典编码不为空");
            }
            if (Objects.isNull(entity.getId())) {
                QueryWrapper<SystemDictEntity> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq(SystemDictEntity.NUMBER, entity.getNumber());
                List<SystemDictEntity> entities = super.list(queryWrapper);
                if (!CollectionUtils.isEmpty(entities)) {
                    throw new BaseException("字典编码已存在");
                }
            }
            if (StringUtils.isBlank(entity.getName())) {
                throw new BaseException("字典名称不为空");
            }
        } else {

        }
    }
}
