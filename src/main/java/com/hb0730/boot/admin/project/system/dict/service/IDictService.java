package com.hb0730.boot.admin.project.system.dict.service;

import com.hb0730.boot.admin.domain.service.ISuperBaseService;
import com.hb0730.boot.admin.project.system.dict.model.dto.DictDTO;
import com.hb0730.boot.admin.project.system.dict.model.entity.DictEntity;
import com.hb0730.boot.admin.project.system.dict.model.query.DictParams;
import com.hb0730.boot.admin.project.system.dict.model.vo.DictVO;

import java.util.List;

/**
 * 数据字典  服务类
 *
 * @author bing_huang
 * @since 数据字典
 */
public interface IDictService extends ISuperBaseService<Long, DictParams, DictDTO, DictEntity> {
    /**
     * 更新缓存
     */
    void updateCache();

    /**
     * 获取缓存
     *
     * @return 缓存值
     */
    List<DictVO> getCache();
}
