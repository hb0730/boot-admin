package com.hb0730.sys.rpc.mapstruct;

import com.hb0730.base.mapstruct.BaseMapstruct;
import com.hb0730.rpc.sys.domain.NoticeDto;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/5/1
 */
@org.mapstruct.Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface NoticeRpcMapstruct extends BaseMapstruct<NoticeDto, com.hb0730.sys.domain.dto.NoticeDto> {
}
