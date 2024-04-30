package com.hb0730.basic.service.mapstruct;

import com.hb0730.base.mapstruct.BaseMapstruct;
import com.hb0730.basic.domain.dto.BasRoleDto;
import com.hb0730.basic.domain.entity.BasRole;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/30
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BasRoleMapstruct extends BaseMapstruct<BasRoleDto, BasRole> {
}
