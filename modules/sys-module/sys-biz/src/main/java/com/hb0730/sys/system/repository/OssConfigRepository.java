package com.hb0730.sys.system.repository;

import com.hb0730.sys.system.domain.SysOssConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * oss配置
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/20
 */
@Repository
public interface OssConfigRepository extends JpaRepository<SysOssConfig, String>, JpaSpecificationExecutor<SysOssConfig> {

    /**
     * 根据系统编码获取oss配置
     *
     * @param sysCode 系统编码
     * @return oss配置
     */
    SysOssConfig findBySysCode(String sysCode);
}
