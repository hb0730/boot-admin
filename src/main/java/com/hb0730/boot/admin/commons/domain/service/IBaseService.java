package com.hb0730.boot.admin.commons.domain.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hb0730.boot.admin.commons.web.model.BaseParams;

/**
 * service 基类
 *
 * @author bing_huang
 * @date 2020/06/26 12:10
 * @since V1.0
 */
public interface IBaseService<P extends BaseParams, E> {

    /**
     * 构造QueryWrapper
     * @param params 请求参数
     * @return  QueryWrapper
     */
    QueryWrapper<E> query(P params);
}
