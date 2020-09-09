package com.hb0730.boot.admin.domain.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.annotation.Log;
import com.hb0730.boot.admin.commons.enums.ResponseStatusEnum;
import com.hb0730.boot.admin.domain.model.dto.BaseDTO;
import com.hb0730.boot.admin.domain.model.entity.BaseDomain;
import com.hb0730.boot.admin.domain.model.query.BaseParams;
import com.hb0730.boot.admin.domain.result.Result;
import com.hb0730.boot.admin.domain.result.Results;
import com.hb0730.boot.admin.domain.service.IBaseService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;
import java.util.List;

/**
 * 基础controller之查询
 *
 * @param <ENTITY> 实体类型
 * @param <ID>     id类型
 * @param <PARAMS> 请求参数类型
 * @param <DTO>    显示层对象类型
 * @author bing_huang
 * @since 3.0.0
 */
public interface IQueryBaseController<ID extends Serializable,
        DTO extends BaseDTO,
        PARAMS extends BaseParams, ENTITY extends BaseDomain> extends IBaseController<ENTITY> {
    /**
     * 分页查询
     *
     * @param params 请求参数
     * @return 分页列表
     */

    @PostMapping("/list/page")
    @SuppressWarnings({"unchecked"})
    @Log(value = "分页查询", paramsName = {"params"})
    @PreAuthorize("@bootAdmin.hasAnyAuthority(this,'ROLE_ADMINISTRATOR','query')")
    default Result<Object> page(@Validated @RequestBody PARAMS params) {
        IBaseService<ID, PARAMS, DTO, ENTITY> service = getBaseService();
        if (null != service) {
            Page<DTO> page = service.page(params);
            return Results.resultSuccess(page);
        }
        return Results.result(ResponseStatusEnum.PARAMS_REQUIRED_IS_NULL, "service is null");
    }

    /**
     * 列表查询
     *
     * @param params 请求参数
     * @return 列表
     */
    @PostMapping("/list")
    @PreAuthorize("hasAnyAuthority()")
    @SuppressWarnings({"unchecked"})
    default Result<Object> list(@Validated @RequestBody PARAMS params) {
        IBaseService<ID, PARAMS, DTO, ENTITY> service = getBaseService();
        if (null != service) {
            List<DTO> list = service.list(params);
            return Results.resultSuccess(list);
        }
        return Results.result(ResponseStatusEnum.PARAMS_REQUIRED_IS_NULL, "service is null");
    }
}
