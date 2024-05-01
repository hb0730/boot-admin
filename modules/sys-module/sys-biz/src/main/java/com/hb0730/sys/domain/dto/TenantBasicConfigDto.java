package com.hb0730.sys.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hb0730.mybatis.core.domain.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/30
 */
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TenantBasicConfigDto extends BaseDto {
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
