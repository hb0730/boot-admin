package com.hb0730.basic.rpc.mapstruct;

import com.hb0730.base.mapstruct.BaseMapper;
import com.hb0730.rpc.basic.domain.BasNoticeDto;
import com.hb0730.sys.system.domain.SysNotice;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/7
 */
@org.mapstruct.Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface BasNoticeMapper extends BaseMapper<BasNoticeDto, SysNotice> {

}
