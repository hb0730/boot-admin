package com.hb0730.sys.rpc.mapstruct;

import com.hb0730.base.mapstruct.BaseMapper;
import com.hb0730.biz.dto.sys.system.PermissionDto;
import com.hb0730.biz.entity.system.Permission;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/27
 */
@org.mapstruct.Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface PermissionMapper extends BaseMapper<PermissionDto, Permission> {
}