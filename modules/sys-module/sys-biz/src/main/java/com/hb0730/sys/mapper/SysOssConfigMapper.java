package com.hb0730.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hb0730.sys.domain.entity.SysOssConfig;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/30
 */
@Repository
public interface SysOssConfigMapper extends BaseMapper<SysOssConfig> {

    /**
     * 根据商户编码查询
     *
     * @param sysCode 商户编码
     * @return SysOssConfig
     */
    @Select("select * from sys_oss_config where sys_code = #{sysCode} limit 1")
    SysOssConfig findBySysCode(String sysCode);
}
