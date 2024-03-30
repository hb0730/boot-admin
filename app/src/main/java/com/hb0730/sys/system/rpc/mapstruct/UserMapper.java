package com.hb0730.sys.system.rpc.mapstruct;

import com.hb0730.base.mapstruct.BaseMapper;
import com.hb0730.rpc.sys.system.domain.UserDto;
import com.hb0730.sys.system.domain.SysUser;
import org.mapstruct.Mapper;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/23
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface UserMapper extends BaseMapper<UserDto, SysUser> {
}
