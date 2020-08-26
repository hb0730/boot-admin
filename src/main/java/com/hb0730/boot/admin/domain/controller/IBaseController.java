package com.hb0730.boot.admin.domain.controller;

import com.hb0730.boot.admin.domain.model.entity.BaseDomain;
import com.hb0730.boot.admin.domain.service.IBaseService;

/**
 * 基础 controller
 *
 * @param <ENTITY> 实体类型
 * @author bing_huang
 * @since 3.0.0
 */
@SuppressWarnings({"rawtypes"})
interface IBaseController<ENTITY extends BaseDomain> {
    /**
     * 获取实体的类型
     *
     * @return entity class
     */
    Class<ENTITY> getEntityClass();

    /**
     * 获取service
     *
     * @return service
     */
    IBaseService getBaseService();
}
