package com.hb0730.boot.admin.task.handler;

import com.hb0730.boot.admin.commons.enums.JobActionEnum;
import com.hb0730.boot.admin.project.system.quartz.mapper.IJobMapper;
import com.hb0730.boot.admin.task.domain.JobInfo;
import org.quartz.SchedulerException;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 更新 job
 *
 * @author bing_huang
 */
@Component
public class JobUpdateAction extends AbstractAction {
    private final JobHelper jobHelper;

    public JobUpdateAction(JobHelper helper, IJobMapper mapper) {
        super(helper, mapper);
        this.jobHelper = helper;
    }

    @Override
    public void run(@Nullable Collection<? extends Serializable> ids) throws SchedulerException {
        List<JobInfo> jobInfos = getJobInfo(ids);
        for (JobInfo jobInfo : jobInfos) {
            // 先移除
            if (jobHelper.checkExists(jobInfo.getId(), jobInfo.getGroup())) {
                jobHelper.deleteJob(jobInfo.getId(), jobInfo.getGroup());
            }
            jobHelper.addJob(jobInfo);

        }
    }

    @Override
    public boolean support(JobActionEnum actionEnum) {
        return JobActionEnum.UPDATE.equals(actionEnum);
    }

    @Override
    public JobActionEnum getActionEnum() {
        return JobActionEnum.UPDATE;
    }
}
