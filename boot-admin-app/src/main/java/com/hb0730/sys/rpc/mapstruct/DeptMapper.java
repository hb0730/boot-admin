package com.hb0730.sys.rpc.mapstruct;

import com.hb0730.base.mapstruct.BaseMapper;
import com.hb0730.biz.dto.sys.system.DeptDto;
import com.hb0730.biz.entity.system.Dept;
import org.mapstruct.Mapper;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/18
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface DeptMapper extends BaseMapper<DeptDto, Dept> {
}
