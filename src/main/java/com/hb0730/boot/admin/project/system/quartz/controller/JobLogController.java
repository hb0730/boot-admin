package com.hb0730.boot.admin.project.system.quartz.controller;


import com.hb0730.boot.admin.domain.controller.AbstractBaseController;
import com.hb0730.boot.admin.project.system.quartz.model.dto.JobLogDTO;
import com.hb0730.boot.admin.project.system.quartz.model.entity.JobLogEntity;
import com.hb0730.boot.admin.project.system.quartz.model.query.JobLogParams;
import com.hb0730.boot.admin.project.system.quartz.service.IJobLogService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 任务日志  前端控制器
 *
 * @author bing_huang
 * @since 3.0.0
 */
@RestController
@RequestMapping("/api/v3/system/job/log")
public class JobLogController extends AbstractBaseController<Long, JobLogDTO, JobLogParams, JobLogEntity> {
    private final IJobLogService service;

    public JobLogController(IJobLogService service) {
        super(service);
        this.service = service;
    }
}

