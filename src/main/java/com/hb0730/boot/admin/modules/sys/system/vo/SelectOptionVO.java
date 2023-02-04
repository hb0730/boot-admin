package com.hb0730.boot.admin.modules.sys.system.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 枚举
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/1/11
 */
@Data
@EqualsAndHashCode
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SelectOptionVO implements Serializable {
    /**
     * 名称
     */
    @Schema(description = "名称")
    private String name;
    /**
     * 值
     */
    @Schema(description = "值")
    private String value;
}
