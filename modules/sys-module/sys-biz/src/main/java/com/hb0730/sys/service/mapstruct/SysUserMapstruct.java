package com.hb0730.sys.service.mapstruct;

import com.hb0730.base.mapstruct.BaseMapstruct;
import com.hb0730.sys.domain.dto.UserDto;
import com.hb0730.sys.domain.dto.UserSaveDto;
import com.hb0730.sys.domain.entity.SysUser;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/30
 */
@org.mapstruct.Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface SysUserMapstruct extends BaseMapstruct<UserDto, SysUser> {

    /**
     * dto转entity
     *
     * @param dto dto
     * @return entity
     */
    SysUser saveDtoToEntity(UserSaveDto dto);
}
