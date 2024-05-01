package com.hb0730.sys.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/29
 */
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode
public class ProductSmallDto implements Serializable {
    /**
     * id
     */
    @Schema(description = "id")
    private String id;
    /**
     * 名称
     */
    @Schema(description = "名称")
    private String name;
    /**
     * 负责人
     */
    @Schema(description = "负责人")
    private String principal;
    /**
     * 联系方式
     */
    @Schema(description = "联系方式")
    private String contact;
}
