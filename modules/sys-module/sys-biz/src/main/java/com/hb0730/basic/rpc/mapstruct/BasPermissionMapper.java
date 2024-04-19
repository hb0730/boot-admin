package com.hb0730.basic.rpc.mapstruct;

import com.hb0730.base.mapstruct.BaseMapper;
import com.hb0730.basic.domain.BasPermission;
import com.hb0730.rpc.basic.domain.BasPermissionDto;
import com.hb0730.rpc.sys.system.domain.PermissionDto;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
@org.mapstruct.Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface BasPermissionMapper extends BaseMapper<BasPermissionDto, BasPermission> {


    /**
     * 转换
     *
     * @param permissions .
     * @return .
     */
    List<PermissionDto> toPermissionDtoList(List<BasPermission> permissions);
}
