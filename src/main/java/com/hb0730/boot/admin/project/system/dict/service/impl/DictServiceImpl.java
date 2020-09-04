package com.hb0730.boot.admin.project.system.dict.service.impl;

import com.hb0730.boot.admin.domain.service.impl.SuperBaseServiceImpl;
import com.hb0730.boot.admin.project.system.dict.mapper.IDictMapper;
import com.hb0730.boot.admin.project.system.dict.model.dto.DictDTO;
import com.hb0730.boot.admin.project.system.dict.model.entity.DictEntity;
import com.hb0730.boot.admin.project.system.dict.model.query.DictParams;
import com.hb0730.boot.admin.project.system.dict.service.IDictService;
import org.springframework.stereotype.Service;

/**
 * 数据字典  服务实现类
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Service
public class DictServiceImpl extends SuperBaseServiceImpl<Long, DictParams, DictDTO, DictEntity, IDictMapper> implements IDictService {

}
