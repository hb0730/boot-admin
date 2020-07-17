package com.hb0730.boot.admin.domain.service;

import com.hb0730.boot.admin.domain.model.web.BaseParams;
import com.hb0730.boot.admin.domain.model.web.BusinessVO;
import com.hb0730.boot.admin.domain.service.base.IQueryBaseService;
import com.hb0730.boot.admin.domain.service.base.ISaveBaseService;
import com.hb0730.boot.admin.domain.service.base.ISuperBaseSaveService;
import com.hb0730.boot.admin.domain.service.base.ISuperBaseUpdateService;

import java.io.Serializable;

/**
 * @author bing_huang
 * @date 2020/07/17 7:48
 * @since V1.0
 */
interface ISuperBaseService<ID extends Serializable, P extends BaseParams, V extends BusinessVO>
        extends ISuperBaseUpdateService<ID, V>,
        ISuperBaseSaveService<V>, IQueryBaseService<P, V>, ISaveBaseService<V> {
}
