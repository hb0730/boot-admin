package com.hb0730.boot.admin.modules.sys.quartz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.modules.sys.quartz.model.entity.QuartzJob;
import com.hb0730.boot.admin.modules.sys.quartz.model.query.QuartzJobQuery;
import com.hb0730.boot.admin.modules.sys.quartz.model.vo.QuartzJobVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 定时任务在线管理 MAPPER
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/6/12
 */
public interface QuartzJobMapper extends BaseMapper<QuartzJob> {

    /**
     * 根据任务实现类全名取得匹配的任务
     *
     * @param jobClassName 任务实现类全名
     * @return 匹配的任务
     */
    @Select("select * from sys_quartz_job where job_class_name = #{jobClassName}")
    List<QuartzJob> findByJobClassName(@Param("jobClassName") String jobClassName);

    /**
     * 分页查询
     *
     * @param page  .
     * @param query .
     * @return .
     */
    List<QuartzJobVO> queryPage(Page<QuartzJobVO> page, @Param("query") QuartzJobQuery query);
}
