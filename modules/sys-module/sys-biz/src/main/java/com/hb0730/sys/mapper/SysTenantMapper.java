package com.hb0730.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hb0730.sys.domain.dto.TenantDto;
import com.hb0730.sys.domain.entity.SysTenant;
import com.hb0730.sys.domain.query.TenantQuery;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/29
 */
@Repository
public interface SysTenantMapper extends BaseMapper<SysTenant> {

    /**
     * 编码是否存在
     *
     * @param code 编码
     * @param id   需要排除的id
     * @return 是否存在
     */
    @Select("select count(1) from `bas_organization` where sys_code = #{code} and id != #{id} and is_system=true")
    boolean existsCodeByIdNot(String code, String id);

    /**
     * 编码是否存在
     *
     * @param code 编码
     * @return 是否存在
     */
    @Select("select count(1) from `bas_organization` where sys_code = #{code} and is_system=true")
    boolean existsCode(String code);


    /**
     * 查询
     *
     * @param query 查询条件
     * @return 数据
     */
    List<TenantDto> list(IPage<TenantDto> page, @Param("query") TenantQuery query);

}
