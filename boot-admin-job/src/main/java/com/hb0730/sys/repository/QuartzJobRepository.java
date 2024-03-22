package com.hb0730.sys.repository;

import com.hb0730.biz.entity.quartz.QuartzJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/4
 */
@Repository
public interface QuartzJobRepository extends JpaRepository<QuartzJob, Integer>, JpaSpecificationExecutor<QuartzJob> {


    /**
     * 根据任务实现类全名取得匹配的任务
     *
     * @param jobClassName 任务实现类全名
     * @return 匹配的任务
     */
    List<QuartzJob> findByJobClassName(String jobClassName);


    /**
     * 更改job的状态
     *
     * @param id      id
     * @param enabled job状态
     */
    @Modifying
    @Query("update QuartzJob q set q.enabled = ?2 where q.id = ?1")
    void updateEnabledById(Integer id, Boolean enabled);

}
