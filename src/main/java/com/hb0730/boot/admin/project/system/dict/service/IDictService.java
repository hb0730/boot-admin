package com.hb0730.boot.admin.project.system.dict.service;

import com.hb0730.boot.admin.domain.service.IBaseService;
import com.hb0730.boot.admin.project.system.dict.model.dto.DictDTO;
import com.hb0730.boot.admin.project.system.dict.model.entity.DictEntity;
import com.hb0730.boot.admin.project.system.dict.model.query.DictParams;

/**
 * 数据字典  服务类
 *
 * @author bing_huang
 * @since 数据字典
 */
public interface IDictService extends IBaseService<Long, DictParams, DictDTO, DictEntity> {

}
