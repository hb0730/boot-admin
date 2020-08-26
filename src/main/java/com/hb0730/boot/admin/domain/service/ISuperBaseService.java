package com.hb0730.boot.admin.domain.service;

import com.hb0730.boot.admin.domain.model.query.BaseParams;
import com.hb0730.boot.admin.domain.model.vo.BaseVO;
import com.hb0730.boot.admin.domain.service.base.ISuperBaseSaveService;
import com.hb0730.boot.admin.domain.service.base.ISuperBaseUpdateService;
import com.hb0730.boot.admin.domain.service.base.ISuperQueryBaseService;

import java.io.Serializable;

/**
 * 基础 service
 *
 * @param <VO>     显示层对象类型
 * @param <PARAMS> 请求参数类型
 * @param <ID>     id类型
 * @author bing_huang
 * @since 3.0.0
 */
public interface ISuperBaseService<ID extends Serializable,
        PARAMS extends BaseParams,
        VO extends BaseVO> extends ISuperBaseUpdateService<ID, VO>,
        ISuperBaseSaveService<VO>,
        ISuperQueryBaseService<PARAMS, VO> {
}
