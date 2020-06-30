package com.hb0730.boot.admin.task.quartz;

import com.hb0730.boot.admin.task.utils.JobInvokeUtil;
import com.hb0730.boot.admin.task.utils.LoggerInfo;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.util.CollectionUtils;

import java.util.Objects;

/**
 * job
 *
 * @author bing_huang
 * @date 2020/05/27 8:14
 * @since V2.0
 */
public class Job extends QuartzJobBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzJobBean.class);

    @SneakyThrows
    @Override
    protected void executeInternal(@NonNull JobExecutionContext jobExecutionContext) {
        LoggerInfo.before();
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        try {
            if (CollectionUtils.isEmpty(jobDataMap)) {
                return;
            }
            Object beanName = jobDataMap.get(QuartzConstant.ParamsName.CLASS_NAME);
            Object methodName = jobDataMap.get(QuartzConstant.ParamsName.METHOD_NAME);
            Object params = jobDataMap.get(QuartzConstant.ParamsName.PARAMS_NAME);
            LOGGER.info("quartz>>>>>>>>>>>>>>>>>>>>开始执行 {}.{}#{}", beanName, methodName, params);
            if (Objects.isNull(beanName) || Objects.isNull(methodName)) {
                return;
            }
            JobInvokeUtil.invokeMethod(beanName.toString(), methodName.toString(), Objects.isNull(params) ? null : params.toString());
            LoggerInfo.after(jobDataMap.get(QuartzConstant.ParamsName.TASK_ID).toString(), null);
        } catch (Exception e) {
            LOGGER.error("quartz>>>>>>>>>>>>>>>>>失败，{}", e.getMessage());
            LoggerInfo.after(jobDataMap.get(QuartzConstant.ParamsName.TASK_ID).toString(), e);

        }
    }
}
