package com.hb0730.basic.service.mapstruct;

import com.hb0730.base.mapstruct.BaseMapstruct;
import com.hb0730.basic.domain.dto.BasPermissionDto;
import com.hb0730.basic.domain.entity.BasPermission;
import com.hb0730.sys.domain.dto.PermissionDto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/29
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface BasPermissionMapstruct extends BaseMapstruct<BasPermissionDto, BasPermission> {
    /**
     * 转换
     *
     * @param permissions .
     * @return .
     */
    List<PermissionDto> toPermissionDtoList(List<BasPermission> permissions);
}
