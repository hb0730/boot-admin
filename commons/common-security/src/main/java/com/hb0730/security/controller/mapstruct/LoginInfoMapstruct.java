package com.hb0730.security.controller.mapstruct;

import com.hb0730.base.mapstruct.BaseMapstruct;
import com.hb0730.security.domain.dto.UserInfoDto;
import com.hb0730.security.domain.vo.LoginInfoVo;
import org.mapstruct.Mapper;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/25
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface LoginInfoMapstruct extends BaseMapstruct<LoginInfoVo, UserInfoDto> {

}