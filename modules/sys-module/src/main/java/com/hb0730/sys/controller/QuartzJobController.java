package com.hb0730.sys.controller;

import com.hb0730.base.R;
import com.hb0730.common.api.JsfPage;
import com.hb0730.sys.domain.dto.QuartzJobDto;
import com.hb0730.sys.domain.query.QuartzJobQuery;
import com.hb0730.sys.service.SysQuartzJobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/30
 */
@RestController
@RequestMapping("/sys/quartz/job")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "管理端：定时任务")
public class QuartzJobController {
    private final SysQuartzJobService quartzJobService;

    /**
     * 验证cron表达式
     *
     * @param cronExpression cron表达式
     * @return 是否成功
     */
    @GetMapping("/validateCron")
    @Operation(summary = "验证cron表达式")
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "cronExpression", description = "cron表达式", required = true)
    })
    public R<String> validateCron(@RequestParam String cronExpression) {
        return R.OK();
    }

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return 分页数据
     */
    @GetMapping
    @Operation(summary = "分页查询")
    @PreAuthorize("hasAuthority('sys:quartz:job:page')")
    public R<JsfPage<QuartzJobDto>> page(QuartzJobQuery query) {
        JsfPage<QuartzJobDto> res = quartzJobService.page(query);
        return R.OK(res);
    }

    /**
     * 查询
     *
     * @param id 主键
     * @return 数据
     */
    @GetMapping("/{id}")
    @Operation(summary = "查询")
    @PreAuthorize("hasAuthority('sys:quartz:job:info')")
    public R<QuartzJobDto> info(@PathVariable String id) {
        QuartzJobDto res = quartzJobService.getById(id);
        return R.OK(res);
    }

    /**
     * 保存
     *
     * @param quartzJob 数据
     * @return 是否成功
     */
    @PostMapping
    @Operation(summary = "保存")
    @PreAuthorize("hasAuthority('sys:quartz:job:save')")
    public R<String> save(@Validated @RequestBody QuartzJobDto quartzJob) {
        quartzJobService.save(quartzJob);
        return R.OK();
    }

    /**
     * 更新
     *
     * @param quartzJob 数据
     * @return 是否成功
     */
    @PutMapping
    @Operation(summary = "更新")
    @PreAuthorize("hasAuthority('sys:quartz:job:update')")
    public R<String> update(@Validated @RequestBody QuartzJobDto quartzJob) {
        quartzJobService.update(quartzJob);
        return R.OK();
    }

    /**
     * 删除
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping
    @Operation(summary = "删除")
    @PreAuthorize("hasAuthority('sys:quartz:job:delete')")
    public R<String> delete(@RequestParam String id) {
        quartzJobService.deleteByIds(Collections.singletonList(id));
        return R.OK();
    }

    /**
     * 批量删除
     *
     * @param ids id
     * @return 是否成功
     */
    @DeleteMapping("/batch")
    @Operation(summary = "批量删除")
    @PreAuthorize("hasAuthority('sys:quartz:job:delete')")
    public R<String> deleteBatch(@RequestBody List<String> ids) {
        return R.OK();
    }

    /**
     * 暂停
     *
     * @param id 主键
     * @return 是否成功
     */
    @PutMapping("/pause")
    @Operation(summary = "暂停")
    @PreAuthorize("hasAuthority('sys:quartz:job:pause')")
    public R<String> pause(@RequestParam String id) {
        return R.OK();
    }

    /**
     * 恢复
     *
     * @param id 主键
     * @return 是否成功
     */
    @PutMapping("/resume")
    @Operation(summary = "恢复")
    @PreAuthorize("hasAuthority('sys:quartz:job:resume')")
    public R<String> resume(@RequestParam String id) {
        return R.OK();
    }

    /**
     * 立即执行
     *
     * @param id 主键
     * @return 是否成功
     */
    @PutMapping("/run")
    @Operation(summary = "立即执行")
    @PreAuthorize("hasAuthority('sys:quartz:job:run')")
    public R<String> run(@RequestParam String id) {
        return R.OK();
    }
}
