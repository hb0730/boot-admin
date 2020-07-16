package com.hb0730.boot.admin.commons.domain.controller;

import com.hb0730.boot.admin.commons.domain.model.domain.BusinessDomain;
import com.hb0730.boot.admin.commons.domain.model.web.BusinessVO;
import com.hb0730.boot.admin.commons.domain.service.IBaseService;
import com.hb0730.boot.admin.commons.web.response.ResponseResult;
import com.hb0730.boot.admin.commons.web.response.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;

/**
 * 修改
 *
 * @param <ID>     id类型
 * @param <V>      vo类型
 * @param <ENTITY> 实体类型
 * @author bing_huang
 * @date 2020/07/15 9:24
 * @since V1.0
 */
public interface IUpdateBaseController<ID extends Serializable, V extends BusinessVO, ENTITY extends BusinessDomain> extends IBaseController<ENTITY> {

    /**
     * 根据id修改
     *
     * @param id id
     * @param vo 修改参数
     * @return 是否成功
     */
    @PostMapping("/update/{id}")
    @SuppressWarnings({"rawtypes", "unchecked"})
    default Result<String> updateById(@PathVariable("id") ID id, @Validated @RequestBody V vo) {
        IBaseService service = getBaseService();
        if (null != service) {
            service.updateById(vo);
            return ResponseResult.resultSuccess("修改成功");
        }
        return ResponseResult.resultFall("修改失败");
    }
}
