package com.hb0730.base.mapstruct;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/11
 */
public interface BaseMapstruct<O, E> extends Mapstruct<E> {

    /**
     * 对象转换
     *
     * @param o 对象
     * @return 实体
     */
    E toEntity(O o);

    /**
     * 对象转换
     *
     * @param e 实体
     * @return 对象
     */
    O toObject(E e);

    /**
     * 对象转换
     *
     * @param o 对象
     * @return 实体
     */
    List<E> toEntityList(List<O> o);

    /**
     * 对象转换
     *
     * @param e 实体
     * @return 对象
     */
    List<O> toObjectList(List<E> e);

}
