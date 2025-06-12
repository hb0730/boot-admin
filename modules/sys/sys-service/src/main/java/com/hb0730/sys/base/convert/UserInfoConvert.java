package com.hb0730.sys.base.convert;


import com.hb0730.base.mapstruct.BaseMapstruct;
import com.hb0730.security.UserInfo;
import com.hb0730.sys.base.model.vo.UserInfoVO;
import com.hb0730.sys.system.model.entity.SysUser;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/9
 */
@org.mapstruct.Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface UserInfoConvert extends BaseMapstruct<UserInfo, SysUser> {

    /**
     * do2vo
     *
     * @param userInfo 用户信息
     * @return vo
     */
    UserInfoVO do2vo(UserInfo userInfo);
}
