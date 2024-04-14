package com.hb0730.sys.system.rpc;

import com.hb0730.base.conf.rpc.server.BaseServerRpcService;
import com.hb0730.common.api.JR;
import com.hb0730.common.api.JsfPage;
import com.hb0730.rpc.sys.system.domain.QuartzJobDto;
import com.hb0730.rpc.sys.system.domain.query.QuartzJobQuery;
import com.hb0730.rpc.sys.system.service.QuartzJobRpcService;
import com.hb0730.sys.system.domain.SysQuartzJob;
import com.hb0730.sys.system.rpc.mapstruct.QuartzJobMapper;
import com.hb0730.sys.system.service.IQuartzJobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/14
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class QuartzJobRpcServiceImpl extends BaseServerRpcService<QuartzJobRpcService> implements QuartzJobRpcService {
    private final IQuartzJobService quartzJobService;
    private final QuartzJobMapper quartzJobMapper;

    @Override
    public JR<JsfPage<QuartzJobDto>> page(QuartzJobQuery query) {
        Page<SysQuartzJob> page = quartzJobService.page(query);

        List<QuartzJobDto> res = quartzJobMapper.toDtoList(page.getContent());
        return JR.okData(JsfPage.of(page, res));
    }

    @Override
    public JR<QuartzJobDto> getById(String id) {
        SysQuartzJob job = quartzJobService.getById(id);
        return JR.okData(quartzJobMapper.toDto(job));
    }

    @Override
    public JR<String> save(QuartzJobDto quartzJob) {
        SysQuartzJob job = quartzJobMapper.toEntity(quartzJob);
        quartzJobService.save(job);
        return JR.okData(job.getId());
    }

    @Override
    public JR<String> updateById(QuartzJobDto quartzJob) {
        SysQuartzJob job = quartzJobMapper.toEntity(quartzJob);
        quartzJobService.updateById(job);
        return JR.ok();
    }

    @Override
    public JR<String> deleteByIds(List<String> ids) {
        quartzJobService.deleteByIds(ids);
        return JR.ok();
    }
}
