package com.hb0730.common.mybatis.tenant.core;

import com.hb0730.common.api.domain.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/28
 */
@Getter
@Setter
@Accessors(chain = true)
public class BaseTenantDto extends BaseDto {

    @Schema(description = "租户ID")
    private String sysCode;
}
