package com.hb0730.sys.system.repository.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hb0730.mybatis.core.mapper.IMapper;
import com.hb0730.sys.system.model.entity.SysUser;
import com.hb0730.sys.system.model.vo.SysUserRoleVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/24
 */
@Repository
public interface SysUserMapper extends IMapper<SysUser> {

    /**
     * 查询用户角色
     *
     * @param page   分页
     * @param userId 用户id
     * @return 角色
     */
    List<SysUserRoleVO> findRoles(IPage<SysUserRoleVO> page, String userId);
}
