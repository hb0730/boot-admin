package com.hb0730.sys.system.convert;


import com.hb0730.base.mapstruct.BizMapstruct;
import com.hb0730.sys.system.model.entity.SysRole;
import com.hb0730.sys.system.model.request.role.SysRoleCreateRequest;
import com.hb0730.sys.system.model.request.role.SysRoleUpdateRequest;
import com.hb0730.sys.system.model.vo.SysRoleVO;

/**
 * 角色
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/8
 */
@org.mapstruct.Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface SysRoleConvert extends BizMapstruct<SysRoleVO, SysRole, SysRoleCreateRequest, SysRoleUpdateRequest> {
}
