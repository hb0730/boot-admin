package com.hb0730.boot.admin.commons.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.commons.domain.model.domain.BusinessDomain;
import com.hb0730.boot.admin.commons.domain.model.web.BaseParams;
import com.hb0730.boot.admin.commons.domain.model.web.BusinessVO;
import com.hb0730.boot.admin.commons.domain.service.IBaseService;
import com.hb0730.boot.admin.commons.utils.PageUtils;
import com.hb0730.boot.admin.commons.utils.QueryWrapperUtils;
import com.hb0730.boot.admin.commons.utils.bean.BeanUtils;
import com.hb0730.boot.admin.commons.utils.validator.ValidatorUtils;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * base service
 *
 * @param <P> 请求参数类型
 * @param <V> vo类型
 * @param <E> 实体类型
 * @param <M> mapper类型
 * @author bing_huang
 * @date 2020/07/16 9:28
 * @since V1.0
 */
public class SuperBaseServiceImpl<ID extends Serializable, P extends BaseParams,
        V extends BusinessVO,
        M extends BaseMapper<E>,
        E extends BusinessDomain>
        extends BaseServiceImpl<M, E> implements IBaseService<ID, P, V, E> {
    @Override
    public Page<V> page(@NonNull P params) {
        QueryWrapper<E> query = query(params);
        Page<E> page = QueryWrapperUtils.getPage(params);
        page = super.page(page, query);
        return PageUtils.toBean(page, getVoClass());
    }

    @Override
    public List<V> list(@NonNull P params) {
        QueryWrapper<E> query = query(params);
        List<E> list = super.list(query);
        return BeanUtils.transformFromInBatch(list, getVoClass());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(@NonNull V vo) {
        ValidatorUtils.validate(vo);
        E e = BeanUtils.transformFrom(vo, getEntityClass());
        return super.save(e);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(@NonNull V vo) {
        ValidatorUtils.validate(vo);
        E e = BeanUtils.transformFrom(vo, getEntityClass());
        return super.updateById(e);
    }

    @Override
    @NonNull
    public QueryWrapper<E> query(@NonNull P params) {
        return QueryWrapperUtils.getQuery(params);
    }

    @SuppressWarnings({"unchecked"})
    protected Class<V> getVoClass() {
        return (Class<V>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @SuppressWarnings({"unchecked"})
    protected Class<E> getEntityClass() {
        return (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[3];
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(@NonNull ID id, @NonNull V vo) {
        ValidatorUtils.validate(vo);
        ValidatorUtils.validate(id);
        E entity = super.getById(id);
        BeanUtils.updateProperties(vo, entity);
        return super.updateById(entity);
    }
}
