package com.hb0730.sys.rpc;

import com.hb0730.biz.dto.sys.quartz.QuartzJobDto;
import com.hb0730.biz.entity.quartz.QuartzJob;
import com.hb0730.commons.JR;
import com.hb0730.commons.JsfPage;
import com.hb0730.conf.rpc.rpc.BaseRpcService;
import com.hb0730.sys.bean.QuartzQuery;
import com.hb0730.sys.rpc.mapstruct.QuartzJobMapper;
import com.hb0730.sys.service.QuartzJobRcpService;
import com.hb0730.sys.service.impl.QuartzService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/21
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class QuartzJobRpcServiceImpl extends BaseRpcService<QuartzJobRcpService> implements QuartzJobRcpService {
    private final QuartzJobMapper quartzJobMapper;

    private final QuartzService quartzService;

    @Override
    public JR<JsfPage<QuartzJobDto>> page(QuartzQuery query) {
        Page<QuartzJob> page = quartzService.page(query);
        List<QuartzJobDto> dataList = quartzJobMapper.toDtoList(page.getContent());
        JsfPage<QuartzJobDto> res = JsfPage.of(page, dataList);
        return JR.okData(res);
    }

    @Override
    public JR<List<QuartzJobDto>> list(QuartzQuery query) {
        List<QuartzJob> list = quartzService.list(query);
        List<QuartzJobDto> dataList = quartzJobMapper.toDtoList(list);
        return JR.okData(dataList);
    }
}
