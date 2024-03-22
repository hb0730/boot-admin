package com.hb0730.sys.rpc.mapstruct;

import com.hb0730.base.mapstruct.BaseMapper;
import com.hb0730.biz.dto.sys.system.UserLoginDto;
import com.hb0730.biz.entity.system.User;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/23
 */
@org.mapstruct.Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface UserLoginMapper extends BaseMapper<UserLoginDto, User> {
}
