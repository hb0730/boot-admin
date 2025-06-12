package com.hb0730.core.service;


import com.hb0730.core.data.Page;
import com.hb0730.mybatis.query.doamin.PageRequest;

import java.io.Serializable;
import java.util.List;

/**
 * Query service interface.
 *
 * @param <Id> id type
 * @param <Q>  query type
 * @param <V>  view type
 * @param <E>  entity type
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2025/5/17
 * @since 1.0.0
 */
public interface IQueryService<Id extends Serializable,
        Q extends PageRequest,
        V extends Serializable,
        E extends Serializable> extends IBasicQueryService<Id, E> {
    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return 分页数据
     */
    Page<V> page(Q query);

    /**
     * 查询
     *
     * @param query 查询条件
     * @return 数据
     */
    List<V> list(Q query);

    /**
     * 查询
     *
     * @param query 查询条件
     * @return 数据
     */
    List<E> listEntity(Q query);

    /**
     * 查询
     *
     * @param id id
     * @return 数据
     */
    V get(Id id);
}
