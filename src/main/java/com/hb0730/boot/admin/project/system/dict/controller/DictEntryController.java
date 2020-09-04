package com.hb0730.boot.admin.project.system.dict.controller;


import com.hb0730.boot.admin.domain.controller.AbstractBaseController;
import com.hb0730.boot.admin.project.system.dict.model.dto.DictEntryDTO;
import com.hb0730.boot.admin.project.system.dict.model.entity.DictEntryEntity;
import com.hb0730.boot.admin.project.system.dict.model.query.DictEntryParams;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据字典项  前端控制器
 *
 * @author bing_huang
 * @since 3.0.0
 */
@RestController
@RequestMapping("/api/v3/system/dict/entry")
public class DictEntryController extends AbstractBaseController<Long, DictEntryDTO, DictEntryParams, DictEntryEntity> {

}

