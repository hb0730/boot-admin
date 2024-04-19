package com.hb0730.sys.tenant.rpc.mapstruct;

import com.hb0730.base.mapstruct.BaseMapper;
import com.hb0730.rpc.sys.tenant.domain.TenantOrgDto;
import com.hb0730.sys.tenant.domain.TenantOrg;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/29
 */
@org.mapstruct.Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface TenantOrgMapper extends BaseMapper<TenantOrgDto, TenantOrg> {
}
