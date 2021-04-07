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
 * 立即执行
 *
 * @author bing_huang
 */
@Component
public class JobRunAction extends AbstractAction {
    public JobRunAction(JobHelper helper, IJobMapper mapper) {
        super(helper, mapper);
    }

    @Override
    public void run(@Nullable Collection<? extends Serializable> ids) throws SchedulerException {
        List<JobInfo> jobInfos = getJobInfo(ids);
        for (JobInfo jobInfo : jobInfos) {
            getJobHelper().runJob(jobInfo.getId(), jobInfo.getGroup(), jobInfo);
        }
    }

    @Override
    public boolean support(JobActionEnum actionEnum) {
        return JobActionEnum.RUN.equals(actionEnum);
    }

    @Override
    public JobActionEnum getActionEnum() {
        return JobActionEnum.RUN;
    }
}
