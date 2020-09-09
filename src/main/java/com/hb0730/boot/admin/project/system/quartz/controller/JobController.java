package com.hb0730.boot.admin.project.system.quartz.controller;


import com.hb0730.boot.admin.annotation.ClassDescribe;
import com.hb0730.boot.admin.annotation.PreAuth;
import com.hb0730.boot.admin.domain.controller.AbstractBaseController;
import com.hb0730.boot.admin.domain.result.Result;
import com.hb0730.boot.admin.domain.result.Results;
import com.hb0730.boot.admin.project.system.quartz.model.dto.JobDTO;
import com.hb0730.boot.admin.project.system.quartz.model.entity.JobEntity;
import com.hb0730.boot.admin.project.system.quartz.model.query.JobParams;
import com.hb0730.boot.admin.project.system.quartz.service.IJobService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 定时任务(quartz)  前端控制器
 *
 * @author bing_huang
 * @since 3.0.0
 */
@RestController
@RequestMapping("/api/v3/system/job")
@ClassDescribe("定时任务")
@PreAuth("job")
public class JobController extends AbstractBaseController<Long, JobDTO, JobParams, JobEntity> {
    private final IJobService service;

    public JobController(IJobService service) {
        super(service);
        this.service = service;
    }

    /**
     * 执行
     *
     * @param id id
     * @return 是否成功
     */
    @GetMapping("/exec/{id}")
    public Result<String> execution(@PathVariable("id") Long id) {
        service.execution(id);
        return Results.resultSuccess("执行成功");
    }
}

