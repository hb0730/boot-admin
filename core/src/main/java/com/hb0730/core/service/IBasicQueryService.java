package com.hb0730.core.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2025/5/21
 */
public interface IBasicQueryService<Id extends Serializable, E extends Serializable> {
    /**
     * 查询
     *
     * @param id id
     * @return 数据
     */
    E getById(Id id);

    /**
     * 查询
     *
     * @return 数据
     */
    List<E> listEntity();

    /**
     * 查询
     *
     * @param ids id集合
     * @return 数据
     */
    List<E> listByIds(Collection<? extends Serializable> ids);

}
