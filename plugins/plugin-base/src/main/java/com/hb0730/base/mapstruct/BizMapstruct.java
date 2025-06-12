package com.hb0730.base.mapstruct;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * @param <V>         视图
 * @param <E>         实体
 * @param <CreateReq> 创建请求
 * @param <UpdateReq> 更新请求
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/11
 */
public interface BizMapstruct<V, E, CreateReq, UpdateReq> extends Mapstruct<E> {
    /**
     * 创建请求转换为实体
     *
     * @param createReq 创建请求
     * @return 实体
     */
    E createReqToEntity(CreateReq createReq);

    /**
     * 更新请求转换为实体
     *
     * @param updateReq 更新请求
     * @return 实体
     */
    E updateReqToEntity(UpdateReq updateReq);

    /**
     * 实体转换为视图
     *
     * @param entity 实体
     * @return 视图
     */
    V toVo(E entity);

    /**
     * 视图转换为实体
     *
     * @param vo 视图
     * @return 实体
     */
    E toEntity(V vo);

    /**
     * 转换
     *
     * @param vo vo
     * @return entity
     */
    List<E> toEntityList(List<V> vo);

    /**
     * 转换
     *
     * @param entity entity
     * @return vo
     */
    List<V> toVoList(List<E> entity);


    /**
     * 更新实体
     *
     * @param sources 更新请求
     * @param target  实体
     */
    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    E updateEntity(UpdateReq sources, @MappingTarget E target);

}
