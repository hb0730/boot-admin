package com.hb0730.boot.admin.data.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 数据结构的数据
 *
 * @param <T> .
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/1/12
 */
public interface TreeData<T extends TreeData<T>> extends Serializable {
    /**
     * id
     *
     * @return .
     */
    String getId();

    /**
     * parent id
     *
     * @return .
     */
    String getParentId();

    /**
     * children
     *
     * @return .
     */
    List<T> getChildren();

    /**
     * set children
     *
     * @param data .
     * @return .
     */
    TreeData<T> setChildren(List<T> data);
}
