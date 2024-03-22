package com.hb0730.sys.service;

import com.hb0730.biz.dto.sys.quartz.QuartzJobDto;
import com.hb0730.commons.JR;
import com.hb0730.commons.JsfPage;
import com.hb0730.sys.bean.QuartzQuery;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/21
 */
public interface QuartzJobRcpService {
    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return 分页数据
     */
    default JR<JsfPage<QuartzJobDto>> page(QuartzQuery query) {
        return null;
    }

    /**
     * 查询
     *
     * @param query 查询条件
     * @return 数据
     */
    default JR<List<QuartzJobDto>> list(QuartzQuery query) {
        return null;
    }
}
