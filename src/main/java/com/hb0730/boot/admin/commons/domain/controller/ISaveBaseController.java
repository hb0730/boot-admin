package com.hb0730.boot.admin.commons.domain.controller;

import com.hb0730.boot.admin.commons.domain.model.domain.BusinessDomain;
import com.hb0730.boot.admin.commons.domain.model.web.BusinessVO;
import com.hb0730.boot.admin.commons.domain.service.IBaseService;
import com.hb0730.boot.admin.commons.utils.bean.BeanUtils;
import com.hb0730.boot.admin.commons.web.response.ResponseResult;
import com.hb0730.boot.admin.commons.web.response.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 新增
 *
 * @param <V>      vo 类型
 * @param <ENTITY> 实体类型
 * @author bing_huang
 * @date 2020/07/15 9:20
 * @since V1.0
 */
public interface ISaveBaseController<V extends BusinessVO, ENTITY extends BusinessDomain> extends IBaseController<ENTITY> {
    /**
     * 保存
     *
     * @param vo 保存参数
     * @return 是否成功
     */
    @PostMapping("/save")
    @SuppressWarnings({"rawtypes", "unchecked"})
    default Result<String> save(@RequestBody @Validated V vo) {
        IBaseService service = getBaseService();
        if (null != service) {
            ENTITY entity = BeanUtils.transformFrom(vo, getEntityClass());
            service.save(entity);
            return ResponseResult.resultSuccess("保存成功");
        }
        return ResponseResult.resultFall("保存失败");
    }
}
