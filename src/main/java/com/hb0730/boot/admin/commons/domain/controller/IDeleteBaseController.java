package com.hb0730.boot.admin.commons.domain.controller;

import com.hb0730.boot.admin.commons.domain.model.domain.BusinessDomain;
import com.hb0730.boot.admin.commons.domain.service.IBaseService;
import com.hb0730.boot.admin.commons.web.response.ResponseResult;
import com.hb0730.boot.admin.commons.web.response.Result;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;
import java.util.List;

/**
 * 删除
 *
 * @param <ID> id类型
 * @author bing_huang
 * @date 2020/07/15 9:29
 * @since V1.0
 */
public interface IDeleteBaseController<ID extends Serializable, ENTITY extends BusinessDomain> extends IBaseController<ENTITY> {

    /**
     * 删除
     *
     * @param id id
     * @return 是否成功
     */
    @GetMapping("/delete/{id}")
    @SuppressWarnings({"rawtypes"})
    default Result<String> deleteById(@PathVariable("id") ID id) {
        IBaseService service = getBaseService();
        if (service != null) {
            service.removeById(id);
            ResponseResult.resultSuccess("删除成功");
        }
        return ResponseResult.resultFall("删除失败");
    }

    /**
     * 根据id批量删除
     *
     * @param ids id
     * @return 是否成功
     */
    @PostMapping("/delete")
    @SuppressWarnings({"rawtypes"})
    default Result<String> deleteByIds(@RequestBody List<ID> ids) {
        IBaseService service = getBaseService();
        if (service != null && !CollectionUtils.isEmpty(ids)) {
            service.removeByIds(ids);
            ResponseResult.resultSuccess("删除成功");
        }
        return ResponseResult.resultFall("删除失败");
    }
}
