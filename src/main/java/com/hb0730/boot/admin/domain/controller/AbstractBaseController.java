package com.hb0730.boot.admin.domain.controller;

import com.hb0730.boot.admin.domain.model.entity.BaseDomain;
import com.hb0730.boot.admin.domain.model.query.BaseParams;
import com.hb0730.boot.admin.domain.model.dto.BaseDTO;
import com.hb0730.boot.admin.domain.service.IBaseService;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * base controller
 *
 * @param <ID>     id 类型
 * @param <DTO>     vo类型
 * @param <PARAMS> 请求类型
 * @param <ENTITY> 实体类型
 * @author bing_huang
 * @since 3.0.0
 */
public abstract class AbstractBaseController<ID extends Serializable, DTO extends BaseDTO, PARAMS extends BaseParams, ENTITY extends BaseDomain>
        implements
        ISaveBaseController<DTO, ENTITY>,
        IQueryBaseController<ID, DTO, PARAMS, ENTITY>,
        IUpdateBaseController<ID, DTO, ENTITY>,
        IDeleteBaseController<ID, ENTITY> {

    private IBaseService<ID, PARAMS, DTO, ENTITY> service;
    protected Class<ENTITY> entityClass = null;

    public AbstractBaseController(IBaseService<ID, PARAMS, DTO, ENTITY> service) {
        this.service = service;
    }

    public AbstractBaseController() {
    }

    @Override
    public IBaseService<ID, PARAMS, DTO, ENTITY> getBaseService() {
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
