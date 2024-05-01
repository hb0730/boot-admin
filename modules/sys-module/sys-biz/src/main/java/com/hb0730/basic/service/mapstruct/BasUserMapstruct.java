package com.hb0730.basic.service.mapstruct;

import com.hb0730.base.mapstruct.BaseMapstruct;
import com.hb0730.basic.domain.dto.BasUserDto;
import com.hb0730.basic.domain.dto.BasUserSaveDto;
import com.hb0730.basic.domain.entity.BasUser;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/30
 */
@org.mapstruct.Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface BasUserMapstruct extends BaseMapstruct<BasUserDto, BasUser> {

    BasUser toEntity(BasUserSaveDto dto);
}
