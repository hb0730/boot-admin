package com.hb0730.boot.admin.domain.controller;

import com.hb0730.boot.admin.commons.enums.ResponseStatusEnum;
import com.hb0730.boot.admin.domain.model.dto.BaseDTO;
import com.hb0730.boot.admin.domain.model.entity.BaseDomain;
import com.hb0730.boot.admin.domain.result.Result;
import com.hb0730.boot.admin.domain.result.Results;
import com.hb0730.boot.admin.domain.service.IBaseService;
import com.hb0730.commons.spring.ValidatorUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;

/**
 * 修改
 *
 * @param <DTO>    vo类型
 * @param <ID>     id 类型
 * @param <ENTITY> 实体类型
 * @author bing_huang
 * @since 3.0.0
 */
public interface IUpdateBaseController<ID extends Serializable, DTO extends BaseDTO, ENTITY extends BaseDomain> extends IBaseController<ENTITY> {
    /**
     * 根据id修改
     *
     * @param id  id
     * @param dto 修改参数
     * @return 是否成功
     */
    @PostMapping("/update/{id}")
    @SuppressWarnings({"rawtypes", "unchecked"})
    default Result<String> updateById(@PathVariable("id") ID id, @Validated @RequestBody DTO dto) {
        ValidatorUtils.validate(dto);
        IBaseService service = getBaseService();
        if (null != service) {
            service.updateById(id, dto);
            return Results.resultSuccess("修改成功");
        }
        return Results.result(ResponseStatusEnum.PARAMS_REQUIRED_IS_NULL, "service is null");
    }
}
