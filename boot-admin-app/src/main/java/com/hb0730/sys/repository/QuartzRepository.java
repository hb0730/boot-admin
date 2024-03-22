package com.hb0730.sys.repository;

import com.hb0730.biz.entity.quartz.QuartzJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 定时任务
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/21
 */
@Repository
public interface QuartzRepository extends JpaRepository<QuartzJob, Integer>, JpaSpecificationExecutor<QuartzJob> {

}
