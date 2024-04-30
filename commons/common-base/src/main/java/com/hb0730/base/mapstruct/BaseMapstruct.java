package com.hb0730.base.mapstruct;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/22
 */
public interface BaseMapstruct<D, E> {
    /**
     * dto转entity
     *
     * @param dto dto
     * @return entity
     */
    E toEntity(D dto);

    /**
     * entity转dto
     *
     * @param entity entity
     * @return dto
     */
    D toDto(E entity);

    /**
     * dtoList转entityList
     *
     * @param dtoList dtoList
     * @return entityList
     */
    List<E> toEntityList(List<D> dtoList);

    /**
     * entityList转dtoList
     *
     * @param entityList entityList
     * @return dtoList
     */
    List<D> toDtoList(List<E> entityList);
}