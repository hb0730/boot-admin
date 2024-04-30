package com.hb0730.basic.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/28
 */
@Data
@EqualsAndHashCode
@ToString
public class OrgSmallDto implements Serializable {
    /**
     * 机构ID
     */
    @Schema(description = "机构ID")
    private String id;
    /**
     * 机构名称
     */
    @Schema(description = "机构名称")
    private String name;

    /**
     * 机构路径
     */
    @Schema(description = "机构路径")
    private String path;
}