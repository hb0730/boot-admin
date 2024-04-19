package com.hb0730.sys.system.rpc.mapstruct;

import com.hb0730.base.mapstruct.BaseMapper;
import com.hb0730.rpc.sys.system.domain.PermissionDto;
import com.hb0730.sys.system.domain.SysPermission;
import org.mapstruct.Mapper;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/25
 */

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface PermissionMapper extends BaseMapper<PermissionDto, SysPermission> {
}
