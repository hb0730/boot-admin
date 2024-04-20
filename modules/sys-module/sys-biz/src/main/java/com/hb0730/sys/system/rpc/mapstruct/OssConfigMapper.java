package com.hb0730.sys.system.rpc.mapstruct;

import com.hb0730.base.mapstruct.BaseMapper;
import com.hb0730.rpc.sys.system.domain.OssConfigDto;
import com.hb0730.sys.system.domain.SysOssConfig;
import org.mapstruct.Mapper;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/20
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface OssConfigMapper extends BaseMapper<OssConfigDto, SysOssConfig> {
}
