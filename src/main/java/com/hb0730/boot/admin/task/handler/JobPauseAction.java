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
 * job 恢复
 *
 * @author bing_huang
 */
@Component
public class JobPauseAction extends AbstractAction {
    public JobPauseAction(JobHelper helper, IJobMapper mapper) {
        super(helper, mapper);
    }

    @Override
    public void run(@Nullable Collection<? extends Serializable> ids) throws SchedulerException {
        List<JobInfo> jobInfos = getJobInfo(ids);
        for (JobInfo jobInfo : jobInfos) {
            getJobHelper().pauseJob(jobInfo.getId(), jobInfo.getGroup());
        }
    }

    @Override
    public boolean support(JobActionEnum actionEnum) {
        return JobActionEnum.PAUSE.equals(actionEnum);
    }

    @Override
    public JobActionEnum getActionEnum() {
        return JobActionEnum.PAUSE;
    }
}
