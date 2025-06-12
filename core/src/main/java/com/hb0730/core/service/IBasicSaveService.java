package com.hb0730.core.service;

import java.util.Collection;

/**
 * 基本保存服务
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2025/5/21
 */
public interface IBasicSaveService<E> {
    /**
     * Save entity
     *
     * @param entity entity to save
     * @return true if success, false otherwise
     */
    boolean save(E entity);

    /**
     * batch save
     *
     * @param entities entities to save
     * @return true if success, false otherwise
     */
    boolean saveBatch(Collection<E> entities);

    /**
     * Update entity
     *
     * @param entity entity to update
     * @return true if success, false otherwise
     */
    boolean saveOrUpdate(E entity);
}
