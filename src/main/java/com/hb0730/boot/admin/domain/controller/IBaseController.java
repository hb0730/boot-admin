package com.hb0730.boot.admin.domain.controller;

import com.hb0730.boot.admin.domain.model.domain.BusinessDomain;
import com.hb0730.boot.admin.domain.service.IBaseService;

/**
 * @author bing_huang
 * @date 2020/07/15 9:16
 * @since V1.0
 */
@SuppressWarnings({"rawtypes"})
interface IBaseController<ENTITY extends BusinessDomain> {
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
