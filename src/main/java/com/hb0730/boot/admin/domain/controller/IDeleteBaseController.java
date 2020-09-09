package com.hb0730.boot.admin.domain.controller;

import com.hb0730.boot.admin.annotation.Log;
import com.hb0730.boot.admin.commons.enums.BusinessTypeEnum;
import com.hb0730.boot.admin.commons.enums.ResponseStatusEnum;
import com.hb0730.boot.admin.domain.model.entity.BaseDomain;
import com.hb0730.boot.admin.domain.result.Result;
import com.hb0730.boot.admin.domain.result.Results;
import com.hb0730.boot.admin.domain.service.IBaseService;
import com.hb0730.commons.lang.collection.CollectionUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;
import java.util.List;

/**
 * 删除
 *
 * @param <ID>     id类型
 * @param <ENTITY> 实体类型
 * @author bing_huang
 * @since 3.0.0
 */
public interface IDeleteBaseController<ID extends Serializable, ENTITY extends BaseDomain> extends IBaseController<ENTITY> {
    /**
     * 删除
     *
     * @param id id
     * @return 是否成功
     */
    @GetMapping("/delete/{id}")
    @SuppressWarnings({"rawtypes"})
    @Log(value = "删除", businessType = BusinessTypeEnum.DELETE)
    @PreAuthorize("@bootAdmin.hasAnyAuthority(this,'ROLE_ADMINISTRATOR','delete')")
    default Result<String> deleteById(@PathVariable("id") ID id) {
        IBaseService service = getBaseService();
        if (service != null) {
            service.removeById(id);
            return Results.resultSuccess("删除成功");
        }
        return Results.result(ResponseStatusEnum.PARAMS_REQUIRED_IS_NULL, "service is null");
    }

    /**
     * 根据id批量删除
     *
     * @param ids id
     * @return 是否成功
     */
    @PostMapping("/delete")
    @SuppressWarnings({"rawtypes"})
    @Log(value = "删除", paramsName = {"ids"}, businessType = BusinessTypeEnum.DELETE)
    @PreAuthorize("@bootAdmin.hasAnyAuthority(this,'ROLE_ADMINISTRATOR','delete')")
    default Result<String> deleteByIds(@RequestBody List<ID> ids) {
        IBaseService service = getBaseService();
        if (service != null && !CollectionUtils.isEmpty(ids)) {
            service.removeByIds(ids);
            return Results.resultSuccess("删除成功");
        }
        return Results.result(ResponseStatusEnum.PARAMS_REQUIRED_IS_NULL, "service is null");
    }
}
