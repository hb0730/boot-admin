package com.hb0730.rpc.basic.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
@Data
@EqualsAndHashCode
public class RoleSmallDto implements Serializable {
    @Schema(description = "角色ID")
    private Long id;
    @Schema(description = "角色名称")
    private String name;
}
