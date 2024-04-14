package com.hb0730.sys.system.repository;

import com.hb0730.sys.system.domain.SysQuartzJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 定时任务
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/14
 */
@Repository
public interface QuartzJobRepository extends JpaRepository<SysQuartzJob, String>, JpaSpecificationExecutor<SysQuartzJob> {
}
