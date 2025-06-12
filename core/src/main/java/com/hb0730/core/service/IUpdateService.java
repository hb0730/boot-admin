package com.hb0730.core.service;

import java.io.Serializable;

/**
 * @param <Id>        id type
 * @param <UpdateReq> update request type
 * @param <V>         view type
 * @param <E>         entity type
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2025/5/17
 * @since 1.0.0
 */
public interface IUpdateService<Id extends Serializable, UpdateReq extends Serializable, V extends Serializable,
        E extends Serializable> extends IBasicUpdateService<E> {
    /**
     * update
     *
     * @param id  id
     * @param req request parameter
     * @return true if success, false otherwise
     */
    boolean updateById(Id id, UpdateReq req);

    /**
     * update and return
     *
     * @param id  id
     * @param req request parameter
     * @return updated entity
     */
    V updateReturnById(Id id, UpdateReq req);

    /**
     * update entity and return
     *
     * @param entity entity to update
     * @return updated entity
     */
    V updateReturnById(E entity);
}
