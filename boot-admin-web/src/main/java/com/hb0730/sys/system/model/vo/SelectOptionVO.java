package com.hb0730.sys.system.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/15
 */
@Getter
@Setter
public class SelectOptionVO implements Serializable {
    /**
     * label
     */
    @Schema(description = "label")
    private String label;
    /**
     * value
     */
    @Schema(description = "value")
    private String value;
}
