package com.hb0730.sys.service;

import com.hb0730.biz.entity.quartz.QuartzJob;
import com.hb0730.sys.bean.QuartzQuery;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/21
 */
public interface IQuartzService {

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return 分页数据
     */
    Page<QuartzJob> page(QuartzQuery query);

    /**
     * 查询
     *
     * @param query 查询条件
     * @return 数据
     */
    List<QuartzJob> list(QuartzQuery query);

    /*其他方法在JOB Service*/
}
