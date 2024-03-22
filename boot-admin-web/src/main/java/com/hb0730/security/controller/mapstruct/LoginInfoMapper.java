package com.hb0730.security.controller.mapstruct;

import com.hb0730.base.mapstruct.BaseMapper;
import com.hb0730.security.model.dto.LoginInfoDto;
import com.hb0730.security.model.vo.LoginInfoVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/22
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface LoginInfoMapper extends BaseMapper<LoginInfoVo, LoginInfoDto> {

    @Override
    @Mappings({
            @org.mapstruct.Mapping(target = "id", source = "user.id"),
            @org.mapstruct.Mapping(target = "username", source = "user.username"),
            @org.mapstruct.Mapping(target = "nickname", source = "user.nickname"),
            @org.mapstruct.Mapping(target = "email", source = "user.email"),
            @org.mapstruct.Mapping(target = "phone", source = "user.phone"),
            @org.mapstruct.Mapping(target = "roles", source = "roles"),
            @org.mapstruct.Mapping(target = "permissions", source = "permissions"),
            @org.mapstruct.Mapping(target = "avatar", source = "user.avatar"),
    })
    LoginInfoVo toDto(LoginInfoDto entity);
}
