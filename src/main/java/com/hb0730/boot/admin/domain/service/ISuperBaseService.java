package com.hb0730.boot.admin.domain.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hb0730.boot.admin.domain.model.dto.BaseDTO;
import com.hb0730.boot.admin.domain.model.entity.BaseDomain;
import com.hb0730.boot.admin.domain.model.query.BaseParams;
import com.hb0730.boot.admin.domain.service.base.ISuperQueryService;
import com.hb0730.boot.admin.domain.service.base.ISuperSaveService;
import com.hb0730.boot.admin.domain.service.base.ISuperUpdateService;
import org.springframework.lang.NonNull;

import java.io.Serializable;

/**
 * 基础Service interface,除了对Controller对象方法的扩展，还包含了
 * <pre>
 *     1. mybatis Plus QueryWrapper的构造,方便{@link BaseParams}
 *     2. {@link #getThis()}方法，主要是方便获取实现类的成员
 * </pre>
 * 具体的业务Service接口应当继承此接口
 *
 * @param <ID>     id类型
 * @param <PARAMS> 请求参数类型
 * @param <DTO>    显示层对象类型
 * @param <ENTITY> 实体类型
 * @author bing_huang
 * @since 3.0.0
 */
public interface ISuperBaseService<ID extends Serializable, PARAMS extends BaseParams, DTO extends BaseDTO, ENTITY extends BaseDomain>
        extends IService<ENTITY>,
        ISuperUpdateService<ID, DTO>,
        ISuperSaveService<DTO>,
        ISuperQueryService<PARAMS, DTO> {

    /**
     * 根据id修改
     *
     * @param dto 显示层对象类型
     * @return 是否成功
     */
    boolean updateById(@NonNull DTO dto);

    /**
     * 构造QueryWrapper
     *
     * @param params 请求参数
     * @return QueryWrapper
     */
    QueryWrapper<ENTITY> query(@NonNull PARAMS params);

    /**
     * 获取实现类,用户获取实现内，内部依赖
     *
     * @param <T> 实现类类型
     * @return 实现类
     */
    <T> T getThis();
}
