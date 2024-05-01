package com.hb0730.sys.service.mapstruct;

import com.hb0730.base.mapstruct.BaseMapstruct;
import com.hb0730.sys.domain.dto.PermissionDto;
import com.hb0730.sys.domain.entity.SysPermission;
import com.hb0730.sys.domain.entity.SysTenantPermission;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/29
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface SysPermissionMapstruct extends BaseMapstruct<PermissionDto, SysPermission> {

    List<PermissionDto> tenantToDtoList(List<SysTenantPermission> permissions);
}
