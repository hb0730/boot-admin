package com.hb0730.core.service;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2025/5/21
 */
public interface IBasicUpdateService<E extends Serializable> {

    /**
     * Update entity by id.
     *
     * @param entity entity
     * @return true if update success, false otherwise
     */
    boolean updateById(E entity);

    /**
     * update batch by id
     *
     * @param entities entities to update
     * @return true if success, false otherwise
     */
    boolean updateBatchById(Collection<E> entities);
}
