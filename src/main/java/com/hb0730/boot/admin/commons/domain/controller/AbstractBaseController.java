package com.hb0730.boot.admin.commons.domain.controller;

import com.hb0730.boot.admin.commons.domain.model.domain.BusinessDomain;
import com.hb0730.boot.admin.commons.domain.model.web.BaseParams;
import com.hb0730.boot.admin.commons.domain.model.web.BusinessVO;
import com.hb0730.boot.admin.commons.domain.service.IBaseService;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * base controller
 *
 * @param <ID>     id 类型
 * @param <V>      vo类型
 * @param <P>      请求类型
 * @param <ENTITY> 实体类型
 * @author bing_huang
 * @date 2020/07/15 9:40
 * @since V1.0
 */
public abstract class AbstractBaseController<ID extends Serializable, V extends BusinessVO,
        P extends BaseParams, ENTITY extends BusinessDomain>
        implements ISaveBaseController<V, ENTITY>, IUpdateBaseController<ID, V, ENTITY>, IDeleteBaseController<ID, ENTITY>,
        IQueryBaseController<V, P, ENTITY> {
    private IBaseService<P, V, ENTITY> service;
    protected Class<ENTITY> entityClass = null;

    public AbstractBaseController(IBaseService<P, V, ENTITY> service) {
        this.service = service;
    }

    public AbstractBaseController() {
    }

    @Override
    public IBaseService<P, V, ENTITY> getBaseService() {
        return service;
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Class<ENTITY> getEntityClass() {
        if (entityClass == null) {
            this.entityClass = (Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[3];
        }
        return this.entityClass;
    }
}
