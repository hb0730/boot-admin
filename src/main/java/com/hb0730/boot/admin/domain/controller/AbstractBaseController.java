package com.hb0730.boot.admin.domain.controller;

import com.hb0730.boot.admin.domain.model.entity.BaseDomain;
import com.hb0730.boot.admin.domain.model.query.BaseParams;
import com.hb0730.boot.admin.domain.model.vo.BaseVO;
import com.hb0730.boot.admin.domain.service.IBaseService;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * base controller
 *
 * @param <ID>     id 类型
 * @param <VO>     vo类型
 * @param <PARAMS> 请求类型
 * @param <ENTITY> 实体类型
 * @author bing_huang
 * @since 3.0.0
 */
public abstract class AbstractBaseController<ID extends Serializable, VO extends BaseVO, PARAMS extends BaseParams, ENTITY extends BaseDomain>
        implements
        ISaveBaseController<VO, ENTITY>,
        IQueryBaseController<ID, VO, PARAMS, ENTITY>,
        IUpdateBaseController<ID, VO, ENTITY>,
        IDeleteBaseController<ID, ENTITY> {

    private IBaseService<ID, PARAMS, VO, ENTITY> service;
    protected Class<ENTITY> entityClass = null;

    public AbstractBaseController(IBaseService<ID, PARAMS, VO, ENTITY> service) {
        this.service = service;
    }

    public AbstractBaseController() {
    }

    public IBaseService<ID, PARAMS, VO, ENTITY> getBaseService() {
        return service;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public Class<ENTITY> getEntityClass() {
        if (entityClass == null) {
            this.entityClass = (Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[3];
        }
        return this.entityClass;
    }

}
