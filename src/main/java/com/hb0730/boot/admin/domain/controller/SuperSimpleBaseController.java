package com.hb0730.boot.admin.domain.controller;

import com.hb0730.boot.admin.domain.controller.base.IBaseDeleteController;
import com.hb0730.boot.admin.domain.controller.base.IBaseQueryController;
import com.hb0730.boot.admin.domain.controller.base.IBaseSaveController;
import com.hb0730.boot.admin.domain.controller.base.IBaseUpdateController;
import com.hb0730.boot.admin.domain.model.dto.BaseDTO;
import com.hb0730.boot.admin.domain.model.entity.BaseDomain;
import com.hb0730.boot.admin.domain.model.query.BaseParams;
import com.hb0730.boot.admin.domain.service.ISuperBaseService;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * 简单的实现了BaseController，<br>
 * 为了获取注入 Service 和 实体类型,实现基本的curd与数据校验已经对应的权限
 *
 * @param <ID>     id 类型
 * @param <DTO>    vo类型
 * @param <PARAMS> 请求类型
 * @param <ENTITY> 实体类型
 * @author bing_huang
 * @since 3.0.0
 */
public abstract class SuperSimpleBaseController<ID extends Serializable, DTO extends BaseDTO, PARAMS extends BaseParams, ENTITY extends BaseDomain>
        implements
        IBaseSaveController<DTO, ENTITY>,
        IBaseQueryController<ID, DTO, PARAMS, ENTITY>,
        IBaseUpdateController<ID, DTO, ENTITY>,
        IBaseDeleteController<ID, ENTITY> {

    private ISuperBaseService<ID, PARAMS, DTO, ENTITY> service;
    protected Class<ENTITY> entityClass = null;

    public SuperSimpleBaseController(ISuperBaseService<ID, PARAMS, DTO, ENTITY> service) {
        this.service = service;
    }

    public SuperSimpleBaseController() {
    }

    @Override
    public ISuperBaseService<ID, PARAMS, DTO, ENTITY> getBaseService() {
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
