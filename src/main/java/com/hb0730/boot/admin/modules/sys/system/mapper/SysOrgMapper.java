package com.hb0730.boot.admin.modules.sys.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.modules.sys.system.model.entity.SysOrganization;
import com.hb0730.boot.admin.modules.sys.system.model.query.OrganizationQuery;
import com.hb0730.boot.admin.modules.sys.system.model.vo.OrganizationVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 机构 Mapper
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/6/3
 */
public interface SysOrgMapper extends BaseMapper<SysOrganization> {
    /**
     * 分页查询
     *
     * @param page  .
     * @param query .
     * @return .
     */
    List<OrganizationVO> queryPage(Page<OrganizationVO> page, @Param("query") OrganizationQuery query);
}
