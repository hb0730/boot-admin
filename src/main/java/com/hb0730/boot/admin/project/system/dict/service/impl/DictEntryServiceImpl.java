package com.hb0730.boot.admin.project.system.dict.service.impl;

import com.hb0730.boot.admin.domain.service.impl.SuperBaseServiceImpl;
import com.hb0730.boot.admin.project.system.dict.mapper.IDictEntryMapper;
import com.hb0730.boot.admin.project.system.dict.model.dto.DictEntryDTO;
import com.hb0730.boot.admin.project.system.dict.model.entity.DictEntryEntity;
import com.hb0730.boot.admin.project.system.dict.model.query.DictEntryParams;
import com.hb0730.boot.admin.project.system.dict.service.IDictEntryService;
import org.springframework.stereotype.Service;

/**
 * 数据字典项  服务实现类
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Service
public class DictEntryServiceImpl extends SuperBaseServiceImpl<Long, DictEntryParams, DictEntryDTO, DictEntryEntity, IDictEntryMapper> implements IDictEntryService {

}
