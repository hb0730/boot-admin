package com.hb0730.boot.admin.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.commons.utils.QueryWrapperUtils;
import com.hb0730.boot.admin.domain.model.dto.BaseDTO;
import com.hb0730.boot.admin.domain.model.entity.BaseDomain;
import com.hb0730.boot.admin.domain.model.query.BaseParams;
import com.hb0730.boot.admin.domain.service.ISuperBaseService;
import com.hb0730.commons.spring.BeanUtils;
import com.hb0730.commons.spring.ValidatorUtils;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * 基础service 实现基本的curd<br>
 * 所有的service应当继承此类,以便Controller基类的调用{@link com.hb0730.boot.admin.domain.controller.SuperSimpleBaseController}
 *
 * @author bing_huang
 * @since 3.0.0
 */
public class SuperBaseServiceImpl<ID extends Serializable, PARAMS extends BaseParams, DTO extends BaseDTO, ENTITY extends BaseDomain, MAPPER extends BaseMapper<ENTITY>>
        extends BaseServiceImpl<MAPPER, ENTITY> implements ISuperBaseService<ID, PARAMS, DTO, ENTITY> {
    @Override
    public boolean updateById(@NonNull DTO dto) {
        ValidatorUtils.validate(dto);
        ENTITY e = BeanUtils.transformFrom(dto, getEntityClass());
        return this.updateById(e);
    }

    @Override
    public QueryWrapper<ENTITY> query(@NonNull PARAMS params) {
        return QueryWrapperUtils.getQuery(params);
    }

    @Override
    public <T> T getThis() {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(@NonNull DTO dto) {
        ValidatorUtils.validate(dto);
        ENTITY entity = BeanUtils.transformFrom(dto, getEntityClass());
        return this.save(entity);
    }

    @Override
    public boolean updateById(@NonNull ID id, @NonNull DTO dto) {
        ValidatorUtils.validate(dto);
        ValidatorUtils.validate(id);
        ENTITY entity = super.getById(id);
        BeanUtils.updateProperties(dto, entity);
        return this.updateById(entity);
    }

    @Override
    public Page<DTO> page(@NonNull PARAMS params) {
        QueryWrapper<ENTITY> query = query(params);
        Page<ENTITY> page = QueryWrapperUtils.getPage(params);
        page = super.page(page, query);
        return QueryWrapperUtils.pageToBean(page, getVoClass());
    }

    @Override
    public List<DTO> list(@NonNull PARAMS params) {
        QueryWrapper<ENTITY> query = query(params);
        List<ENTITY> list = super.list(query);
        return BeanUtils.transformFromInBatch(list, getVoClass());
    }

    @SuppressWarnings({"unchecked"})
    protected Class<DTO> getVoClass() {
        return (Class<DTO>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[2];
    }

    @SuppressWarnings({"unchecked"})
    protected Class<ENTITY> getEntityClass() {
        return (Class<ENTITY>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[3];
    }

    @Override
    @SuppressWarnings({"unchecked"})
    protected Class<ENTITY> currentModelClass() {
        return (Class<ENTITY>) ReflectionKit.getSuperClassGenericType(getClass(), 3);
    }

    @Override
    @SuppressWarnings({"unchecked"})
    protected Class<ENTITY> currentMapperClass() {
        return (Class<ENTITY>) ReflectionKit.getSuperClassGenericType(getClass(), 4);
    }
}
