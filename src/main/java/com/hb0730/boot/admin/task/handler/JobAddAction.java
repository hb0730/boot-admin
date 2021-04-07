package com.hb0730.boot.admin.task.handler;

import com.hb0730.boot.admin.commons.enums.JobActionEnum;
import com.hb0730.boot.admin.project.system.quartz.mapper.IJobMapper;
import com.hb0730.boot.admin.task.domain.JobInfo;
import org.quartz.SchedulerException;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 新增
 *
 * @author bing_huang
 */
@Component
public class JobAddAction extends AbstractAction {

    public JobAddAction(JobHelper helper, IJobMapper mapper) {
        super(helper, mapper);
    }

    @Override
    public void run(@Nullable Collection<? extends Serializable> ids) throws SchedulerException {
        List<JobInfo> jobInfos = getJobInfo(ids);
        for (JobInfo jobInfo : jobInfos) {
            getJobHelper().addJob(jobInfo);
        }
    }

    @Override
    public boolean support(@NonNull JobActionEnum actionEnum) {
        return JobActionEnum.ADD_NEW.equals(actionEnum);
    }

    @Override
    public JobActionEnum getActionEnum() {
        return JobActionEnum.ADD_NEW;
    }
}
