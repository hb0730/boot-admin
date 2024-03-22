package com.hb0730.sys.controller;

import com.hb0730.base.R;
import com.hb0730.biz.dto.sys.quartz.QuartzJobDto;
import com.hb0730.sys.service.IQuartzJobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 定时任务在线管理 前端控制器
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/4
 */
@RestController
@RequestMapping("/sys/quartz/job")
@RequiredArgsConstructor
@Slf4j
public class QuartzJobController {
    private final IQuartzJobService quartzJobService;

    /**
     * 验证cron表达式
     *
     * @param cronExpression cron表达式
     * @return 是否成功
     */
    @RequestMapping(value = "/validate/cron", method = RequestMethod.GET)
    public R<String> validateCronExpression(String cronExpression) {
        return quartzJobService.validateCronExpression(cronExpression);
    }

    /**
     * 添加定时任务
     *
     * @param dto 定时任务
     * @return 处理结果
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public R<String> add(@Valid @RequestBody QuartzJobDto dto) {
        return quartzJobService.addJob(dto);
    }

    /**
     * 更新定时任务
     *
     * @param dto 定时任务
     * @return 处理结果
     */
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public R<?> edit(@Valid @RequestBody QuartzJobDto dto) {
        return quartzJobService.editJob(dto);
    }

    /**
     * 删除定时任务
     *
     * @param id 定时任务id
     * @return 处理结果
     */
    @RequestMapping(value = "/delete", params = "id", method = RequestMethod.DELETE)
    public R<?> delete(Integer id) {
        return quartzJobService.deleteJob(id);
    }


    /**
     * 暂停定时任务
     *
     * @param id 定时任务id
     * @return 处理结果
     */
    @RequestMapping(value = "/pause", params = "id", method = RequestMethod.PUT)
    public R<?> pauseJob(Integer id) {
        return quartzJobService.pauseJob(id);
    }

    /**
     * 恢复定时任务
     *
     * @param id 定时任务id
     * @return 处理结果
     */
    @RequestMapping(value = "/resume", params = "id", method = RequestMethod.PUT)
    public R<?> resumeJob(Integer id) {
        return quartzJobService.resumeJob(id);
    }

    /**
     * 立即执行定时任务
     *
     * @return 处理结果
     */
    @RequestMapping(value = "/execute", method = RequestMethod.PUT)
    public R<?> executeJob(Integer id) {
        return quartzJobService.executeJob(id);
    }
}
