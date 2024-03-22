package com.hb0730.sys.rpc.mapstruct;

import com.hb0730.base.mapstruct.BaseMapper;
import com.hb0730.biz.dto.sys.system.RoleDto;
import com.hb0730.biz.entity.system.Role;
import org.mapstruct.ReportingPolicy;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/28
 */
@org.mapstruct.Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface RoleMapper extends BaseMapper<RoleDto, Role> {
}
