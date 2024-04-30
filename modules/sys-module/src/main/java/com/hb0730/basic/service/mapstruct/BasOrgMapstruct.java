package com.hb0730.basic.service.mapstruct;

import com.hb0730.base.mapstruct.BaseMapstruct;
import com.hb0730.basic.domain.dto.BasOrgDto;
import com.hb0730.basic.domain.entity.BasOrg;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/30
 */
@org.mapstruct.Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface BasOrgMapstruct extends BaseMapstruct<BasOrgDto, BasOrg> {
}
