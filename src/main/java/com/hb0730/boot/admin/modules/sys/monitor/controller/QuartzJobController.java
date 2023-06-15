package com.hb0730.boot.admin.modules.sys.monitor.controller;

import cn.hutool.core.bean.BeanUtil;
import com.hb0730.boot.admin.base.R;
import com.hb0730.boot.admin.data.domain.BasePage;
import com.hb0730.boot.admin.modules.sys.monitor.model.vo.QuartzJobVO;
import com.hb0730.boot.admin.modules.sys.monitor.model.entity.QuartzJob;
import com.hb0730.boot.admin.modules.sys.monitor.model.query.QuartzJobQuery;
import com.hb0730.boot.admin.modules.sys.monitor.service.QuartzJobService;
import com.hb0730.boot.admin.security.util.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronExpression;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Quartz定时任务Controller
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/6/12
 */
@RestController
@RequestMapping("/monitor/quartz/job")
@Tag(name = "定时任务")
@RequiredArgsConstructor
@Slf4j
public class QuartzJobController {
    private final QuartzJobService quartzJobService;

    /**
     * 表达式是否正确
     *
     * @param request .
     * @param cron    .
     * @return .
     */
    @GetMapping("/check/cron")
    @Operation(summary = "表达式是否正确")
    public R<Boolean> checkCron(HttpServletRequest request, String cron) {
        boolean success = CronExpression.isValidExpression(cron);
        return success ? R.OK() : R.NG(200,"表达式错误");
    }

    @GetMapping("/query/page")
    @Operation(summary = "分页查询")
    @PreAuthorize("hasAuthority('sys:quartz:job:query')")
    public R<BasePage<QuartzJobVO>> queryPage(HttpServletRequest request, @ParameterObject QuartzJobQuery query) {
        return R.OK(quartzJobService.queryPage(query));
    }

    @GetMapping("/query/list")
    @Operation(summary = "列表查询")
    public R<List<QuartzJobVO>> queryList(HttpServletRequest request, @ParameterObject QuartzJobQuery query) {
        return R.OK(quartzJobService.queryList(query));
    }

    @PostMapping("/save")
    @Operation(summary = "保存")
    @PreAuthorize("hasAuthority('sys:quartz:job:save')")
    public R<QuartzJobVO> save(HttpServletRequest request, @RequestBody QuartzJobVO vo) {
        String cronExpression = vo.getCronExpression();
        boolean success = CronExpression.isValidExpression(cronExpression);
        if (!success) {
            return R.NG("表达式错误");
        }
        String username = SecurityUtil.getCurrentUsername();
        QuartzJob quartzJob = BeanUtil.toBean(vo, QuartzJob.class);
        quartzJob.setCreated(LocalDateTime.now());
        quartzJob.setCreatedBy(username);
        quartzJobService.saveAndScheduleJob(quartzJob);
        vo = BeanUtil.toBean(quartzJob, QuartzJobVO.class);
        return R.OK(vo);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "更新")
    @PreAuthorize("hasAuthority('sys:quartz:job:update')")
    public R<QuartzJobVO> updateById(HttpServletRequest request, @PathVariable String id, @RequestBody QuartzJobVO vo) {
        try {
            QuartzJob job = quartzJobService.getById(id);
            if (null == job) {
                return R.NG("任务不存在");
            }
            String cronExpression = vo.getCronExpression();
            boolean success = CronExpression.isValidExpression(cronExpression);
            if (!success) {
                return R.NG("表达式错误");
            }
            String username = SecurityUtil.getCurrentUsername();
            QuartzJob quartzJob = BeanUtil.toBean(vo, QuartzJob.class);
            quartzJob.setModified(LocalDateTime.now());
            quartzJob.setModifiedBy(username);
            quartzJobService.editAndScheduleJob(quartzJob);
            vo = BeanUtil.toBean(quartzJob, QuartzJobVO.class);
            return R.OK(vo);
        } catch (Exception e) {
            log.warn("更新定时任务失败", e);
            return R.NG("更新定时任务失败");
        }
    }

    @PutMapping("/pause/{id}")
    @PreAuthorize("hasAuthority('sys:quartz:job:pause')")
    @Operation(summary = "暂停")
    public R<String> pause(HttpServletRequest request, @PathVariable String id) {
        QuartzJob quartzJob = quartzJobService.getById(id);
        if (null == quartzJob) {
            return R.NG("任务不存在");
        }
        try {
            quartzJobService.pauseJob(quartzJob);
            return R.OK();
        } catch (Exception e) {
            log.warn("暂停定时任务失败", e);
            return R.NG("暂停定时任务失败");
        }
    }

    @PutMapping("/resume/{id}")
    @PreAuthorize("hasAuthority('sys:quartz:job:resume')")
    @Operation(summary = "恢复")
    public R<String> resume(HttpServletRequest request, @PathVariable String id) {
        QuartzJob quartzJob = quartzJobService.getById(id);
        if (null == quartzJob) {
            return R.NG("任务不存在");
        }
        try {
            quartzJobService.resumeJob(quartzJob);
            return R.OK();
        } catch (Exception e) {
            log.warn("恢复定时任务失败", e);
            return R.NG("恢复定时任务失败");
        }
    }

    @PutMapping("/execute/{id}")
    @PreAuthorize("hasAuthority('sys:quartz:job:execute')")
    @Operation(summary = "执行")
    public R<String> execute(HttpServletRequest request, @PathVariable String id) {
        QuartzJob quartzJob = quartzJobService.getById(id);
        if (null == quartzJob) {
            return R.NG("任务不存在");
        }
        try {
            quartzJobService.executeJob(quartzJob);
            return R.OK();
        } catch (Exception e) {
            log.warn("执行定时任务失败", e);
            return R.NG("执行定时任务失败");
        }
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除")
    @PreAuthorize("hasAuthority('sys:quartz:job:delete')")
    public R<String> delete(HttpServletRequest request, @RequestParam String id) {
        QuartzJob quartzJob = quartzJobService.getById(id);
        if (null == quartzJob) {
            return R.NG("任务不存在");
        }
        quartzJobService.deleteAndStopJob(quartzJob);
        return R.OK();
    }
}
