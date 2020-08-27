package com.hb0730.boot.admin.domain.controller;

import com.hb0730.boot.admin.domain.model.entity.BaseDomain;
import com.hb0730.boot.admin.domain.model.vo.BaseVO;
import com.hb0730.boot.admin.commons.enums.ResponseStatusEnum;
import com.hb0730.boot.admin.domain.result.Result;
import com.hb0730.boot.admin.domain.result.Results;
import com.hb0730.boot.admin.domain.service.IBaseService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 基础controller之新增
 *
 * @param <ENTITY> 实体类型
 * @param <VO>     显示层对象类型
 * @author bing_huang
 * @since 3.0.0
 */
public interface ISaveBaseController<VO extends BaseVO, ENTITY extends BaseDomain> extends IBaseController<ENTITY> {

    /**
     * 保存
     *
     * @param vo 参数
     * @return 是否成功
     */
    @PostMapping("/save")
    @SuppressWarnings({"rawtypes", "unchecked"})
    default Result<String> save(@RequestBody @Validated VO vo) {
        IBaseService baseService = getBaseService();
        if (null != baseService) {
            baseService.save(vo);
            return Results.resultSuccess("保存成功");
        }
        return Results.result(ResponseStatusEnum.PARAMS_REQUIRED_IS_NULL, "service is null");
    }
}
