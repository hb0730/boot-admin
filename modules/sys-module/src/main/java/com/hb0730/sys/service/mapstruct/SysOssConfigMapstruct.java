package com.hb0730.sys.service.mapstruct;

import com.hb0730.base.mapstruct.BaseMapstruct;
import com.hb0730.sys.domain.dto.OssConfigDto;
import com.hb0730.sys.domain.entity.SysOssConfig;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/30
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysOssConfigMapstruct extends BaseMapstruct<OssConfigDto, SysOssConfig> {
}
