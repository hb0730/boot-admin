package com.hb0730.core.service;

import java.io.Serializable;
import java.util.Collection;

/**
 * 基本删除服务
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2025/5/21
 */
public interface IBasicDeleteService<Id extends Serializable> {
    /**
     * delete by id
     *
     * @param id .
     * @return .
     */
    boolean deleteById(Id id);

    /**
     * batch delete by id
     *
     * @param ids .
     * @return .
     */
    boolean deleteByIds(Collection<Id> ids);
}
