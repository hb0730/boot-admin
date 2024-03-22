package com.hb0730.sys.rpc.mapstruct;

import com.hb0730.base.mapstruct.BaseMapper;
import com.hb0730.biz.dto.sys.system.UserDto;
import com.hb0730.biz.entity.system.User;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/15
 */
@org.mapstruct.Mapper(componentModel = "spring", uses = {DeptMapper.class}, unmappedTargetPolicy =
        org.mapstruct.ReportingPolicy.IGNORE)
public interface UserMapper extends BaseMapper<UserDto, User> {
}
