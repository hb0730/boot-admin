package com.hb0730.sys.system.convert;


import com.hb0730.base.mapstruct.BizMapstruct;
import com.hb0730.sys.system.model.entity.SysDict;
import com.hb0730.sys.system.model.request.dict.SysDictCreateRequest;
import com.hb0730.sys.system.model.vo.SysDictVO;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/11
 */
@org.mapstruct.Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface SysDictConvert extends BizMapstruct<SysDictVO, SysDict, SysDictCreateRequest, SysDictCreateRequest> {
}
