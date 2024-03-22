package com.hb0730.sys.service.mapstruct;

import com.hb0730.base.mapstruct.BaseMapper;
import com.hb0730.biz.dto.sys.quartz.QuartzJobDto;
import com.hb0730.biz.entity.quartz.QuartzJob;
import org.mapstruct.Mapper;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/21
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface QuartzJobMapper extends BaseMapper<QuartzJobDto, QuartzJob> {
}
