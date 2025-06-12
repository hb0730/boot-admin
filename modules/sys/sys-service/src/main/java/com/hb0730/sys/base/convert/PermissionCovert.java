package com.hb0730.sys.base.convert;

import com.hb0730.base.mapstruct.BaseMapstruct;
import com.hb0730.sys.base.model.dto.PermissionDTO;
import com.hb0730.sys.system.model.entity.SysPermission;
import org.mapstruct.Mapper;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/3
 */
@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface PermissionCovert extends BaseMapstruct<PermissionDTO, SysPermission> {
}
