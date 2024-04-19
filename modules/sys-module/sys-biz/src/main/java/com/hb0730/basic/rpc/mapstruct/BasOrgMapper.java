package com.hb0730.basic.rpc.mapstruct;

import com.hb0730.base.mapstruct.BaseMapper;
import com.hb0730.basic.domain.BasOrg;
import com.hb0730.rpc.basic.domain.BasOrgDto;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/31
 */
@org.mapstruct.Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface BasOrgMapper extends BaseMapper<BasOrgDto, BasOrg> {
}
