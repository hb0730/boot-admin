package com.hb0730.sys.tenant.rpc.mapstruct;

import com.hb0730.base.mapstruct.BaseMapper;
import com.hb0730.rpc.sys.tenant.domain.TenantPermissionDto;
import com.hb0730.sys.tenant.domain.TenantPermission;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/27
 */
@org.mapstruct.Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface TenantPermissionMapper extends BaseMapper<TenantPermissionDto, TenantPermission> {
}
