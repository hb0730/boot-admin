package com.hb0730.rpc.sys.system.domain;

import com.hb0730.common.api.TenantDomainDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * oss配置
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/20
 */
@Getter
@Setter
@Accessors(chain = true)
public class OssConfigDto extends TenantDomainDto {

    @Schema(description = "id")
    private String id;
    /**
     * oss名称
     */
    @Schema(description = "oss名称")
    @NotBlank(message = "oss名称不能为空")
    private String ossName;
    /**
     * 访问key
     */
    @Schema(description = "访问key")
    @NotBlank(message = "访问key不能为空")
    private String accessKey;
    /**
     * 秘钥
     */
    @Schema(description = "秘钥")
    @NotBlank(message = "秘钥不能为空")
    private String secretKey;
    /**
     * 桶名称
     */
    @Schema(description = "桶名称")
    @NotBlank(message = "桶名称不能为空")
    private String bucketName;
    /**
     * 端点
     */
    @Schema(description = "endpoint")
    @NotBlank(message = "端点不能为空")
    private String endpoint;
    /**
     * 域名
     */
    @Schema(description = "域名(CDN)")
    private String domain;
    /**
     * 区域
     */
    @Schema(description = "区域")
    private String region;
}
