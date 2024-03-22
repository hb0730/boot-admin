package com.hb0730.sys.quartz.rpcservice;

import com.hb0730.base.R;
import com.hb0730.biz.dto.sys.quartz.QuartzJobDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/4
 */
@HttpExchange(url = "/sys/quartz/job")
public interface IQuartzRemoteService {

    /**
     * 验证cron表达式
     *
     * @param cronExpression cron表达式
     * @return 是否成功
     */
    @GetExchange(url = "/validate/cron")
    R<String> validateCronExpression(@RequestParam("cronExpression") String cronExpression);

    /**
     * 添加定时任务
     *
     * @param dto 定时任务
     * @return R
     */
    @PostExchange(value = "/add")
    R<String> add(@RequestBody QuartzJobDto dto);

    /**
     * 更新定时任务
     *
     * @param dto 定时任务
     * @return R
     */
    @PutExchange(value = "/edit")
    R<String> edit(@RequestBody QuartzJobDto dto);

    /**
     * 删除定时任务
     *
     * @param id 定时任务id
     * @return R
     */
    @DeleteExchange(value = "/delete")
    R<String> delete(@RequestParam("id") Integer id);

    /**
     * 暂停定时任务
     *
     * @param id 定时任务id
     * @return R
     */
    @PutExchange(value = "/pause")
    R<String> pauseJob(@RequestParam("id") Integer id);

    /**
     * 恢复定时任务
     *
     * @param id 定时任务id
     * @return R
     */
    @PutExchange(value = "/resume")
    R<String> resumeJob(@RequestParam("id") Integer id);

    /**
     * 执行定时任务
     *
     * @param id 定时任务id
     * @return R
     */
    @PutExchange(value = "/execute")
    R<String> executeJob(@RequestParam("id") Integer id);


}
