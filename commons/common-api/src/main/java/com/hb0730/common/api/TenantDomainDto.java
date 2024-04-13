package com.hb0730.common.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/23
 */
@Getter
@Setter
@Accessors(chain = true)
public class TenantDomainDto extends DomainDto {
    /**
     * 租户CODE
     */
    @Schema(description = "租户CODE")
    private String sysCode;
}
