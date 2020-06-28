package com.hb0730.boot.admin.task.quartz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static com.hb0730.boot.admin.task.quartz.QuartzConstant.DEFAULT_GROUP;


/**
 * @author bing_huang
 * @date 2020/05/27 9:09
 * @since V2.0
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JobModel {
    /**
     * 任务id
     */
    private String jobId;
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 任务id
     */
    private String jobGroupName = DEFAULT_GROUP;
    /**
     * 类名
     */
    private String beanName;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 参数
     */
    private String params;

    /**
     * 表达式
     */
    private String cron;
}
