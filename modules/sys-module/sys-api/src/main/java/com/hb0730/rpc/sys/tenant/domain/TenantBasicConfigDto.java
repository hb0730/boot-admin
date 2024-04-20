package com.hb0730.rpc.sys.tenant.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hb0730.common.api.DomainDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 租户基础配置
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
@Getter
@Setter
@Accessors(chain = true)
public class TenantBasicConfigDto extends DomainDto {
    @Schema(description = "ID")
    @NotBlank(message = "ID不能为空")
    private String id;
    /**
     * 截止使用时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Schema(description = "截止使用时间")
    @NotBlank(message = "截止使用时间不能为空")
    private Date usedEndTime;
}
