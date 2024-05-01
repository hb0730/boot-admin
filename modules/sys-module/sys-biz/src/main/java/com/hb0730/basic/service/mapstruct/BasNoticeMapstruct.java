package com.hb0730.basic.service.mapstruct;

import com.hb0730.base.mapstruct.BaseMapstruct;
import com.hb0730.basic.domain.dto.BasNoticeDto;
import com.hb0730.sys.domain.entity.SysNotice;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/29
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BasNoticeMapstruct extends BaseMapstruct<BasNoticeDto, SysNotice> {
}
