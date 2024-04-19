package com.hb0730.sys.system.rpc.mapstruct;

import com.hb0730.base.mapstruct.BaseMapper;
import com.hb0730.rpc.sys.system.domain.RoleDto;
import com.hb0730.sys.system.domain.SysRole;
import org.mapstruct.Mapper;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/25
 */

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface RoleMapper extends BaseMapper<RoleDto, SysRole> {
}
