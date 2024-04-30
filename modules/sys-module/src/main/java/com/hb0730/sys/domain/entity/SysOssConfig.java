package com.hb0730.sys.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hb0730.mybatis.core.domain.BaseEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/30
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@TableName("sys_oss_config")
public class SysOssConfig extends BaseEntity {
    @TableId(type = IdType.ASSIGN_ID)
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
