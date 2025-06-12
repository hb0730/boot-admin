package com.hb0730.core.service;


import com.hb0730.mybatis.query.doamin.PageRequest;

import java.io.Serializable;

/**
 * 服务接口
 *
 * @param <Id>        主键类型
 * @param <Q>         查询类型
 * @param <CreateReq> 创建请求类型
 * @param <UpdateReq> 更新请求类型
 * @param <V>         视图类型
 * @param <E>         实体类型
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2025/5/17
 * @since 1.0.0
 */
public interface IService<Id extends Serializable,
        Q extends PageRequest,
        CreateReq extends Serializable,
        UpdateReq extends Serializable,
        V extends Serializable,
        E extends Serializable> extends IQueryService<Id, Q, V, E>, ISaveService<CreateReq, V, E>, IUpdateService<Id,
        UpdateReq, V, E>, IDeleteService<Id> {
}
