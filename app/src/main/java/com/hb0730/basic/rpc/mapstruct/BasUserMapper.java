package com.hb0730.basic.rpc.mapstruct;

import com.hb0730.base.mapstruct.BaseMapper;
import com.hb0730.basic.domain.BasUser;
import com.hb0730.rpc.basic.domain.BasUserDto;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
@org.mapstruct.Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface BasUserMapper extends BaseMapper<BasUserDto, BasUser> {
}
