package com.hb0730.sys.system.domain;

import com.hb0730.base.jpa.core.domain.BaseEntity;
import com.hb0730.base.jpa.core.id.IdGenerator;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 租户oss配置
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/19
 */
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "sys_oss_config")
@Entity
public class SysOssConfig extends BaseEntity {
    @Id
    @IdGenerator
    private String id;
    /**
     * oss名称
     */
    @NotBlank(message = "oss名称不能为空")
    private String ossName;

    /**
     * oss类型
     */
    @NotBlank(message = "oss类型")
    private String ossType;
    /**
     * 访问key
     */
    @NotBlank(message = "访问key不能为空")
    private String accessKey;
    /**
     * 秘钥
     */
    @NotBlank(message = "秘钥不能为空")
    private String secretKey;
    /**
     * 桶名称
     */
    @NotBlank(message = "桶名称不能为空")
    private String bucketName;
    /**
     * 端点
     */
    @NotBlank(message = "端点不能为空")
    private String endpoint;
    /**
     * 域名
     */
    private String domain;
    /**
     * 区域
     */
    private String region;
    /**
     * 商户编码
     */
    private String sysCode;

}
